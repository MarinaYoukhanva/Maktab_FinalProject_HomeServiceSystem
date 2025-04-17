package com.freshome.service.impl;

import com.freshome.dto.ChangePasswordDTO;
import com.freshome.dto.credit.CreditCreateDTO;
import com.freshome.dto.credit.CreditResponseDTO;
import com.freshome.dto.customer.CustomerCreateDTO;
import com.freshome.dto.customer.CustomerResponseDTO;
import com.freshome.dto.customer.CustomerUpdateDTO;
import com.freshome.entity.*;
import com.freshome.entity.entityMapper.CreditMapper;
import com.freshome.entity.entityMapper.CustomerMapper;
import com.freshome.exception.ExistenceException;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.CustomerRepository;
import com.freshome.service.CreditService;
import com.freshome.service.CustomerService;
import com.freshome.service.RoleService;
import com.freshome.service.UserService;
import com.freshome.service.specification.CustomerSpecification;
import com.freshome.service.verification.CustomerVerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CreditService creditService;
    private final UserService userService;
    private final RoleService roleService;
    private final CustomerVerificationService customerVerificationService;

    @Override
    @Transactional
    public CustomerResponseDTO createCustomer(CustomerCreateDTO customerCreateDTO) {
        log.info("attempt to create customer");

        Role role = roleService.findByName(Role.CUSTOMER);
        Customer customer = CustomerMapper.customerFromDto(customerCreateDTO);
        customer.getUser().getRoles().add(role);

        if (customerRepository.existsByPhoneNumber(customerCreateDTO.getPhoneNumber()))
            throw new ExistenceException("phoneNumber");

        userService.save(customer.getUser());
        Credit credit = creditService.createReturnCredit(new CreditCreateDTO(0.0));
        customer.setCredit(credit);

        Customer savedCustomer = customerRepository.save(customer);
        log.info("customer with id {} saved successfully", savedCustomer.getId());

        customerVerificationService.sendVerificationEmail(customer.getUser());
        return CustomerMapper.dtoFromCustomer(savedCustomer);
    }

    @Override
    public CustomerResponseDTO findCustomerById(Long id) {
        return CustomerMapper.dtoFromCustomer(
                customerRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(Customer.class, id))
        );
    }

    @Override
//    @Transactional
    public Optional<Customer> findOptionalCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> findByUsername(String username){
        return customerRepository.findByUser_Username(username);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<CustomerResponseDTO> findAllCustomers() {
        return customerRepository.findAll()
                .stream().map(CustomerMapper::dtoFromCustomer)
                .toList();
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        log.info("attempt to delete customer with id: {}", id);

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Customer.class, id));
        userService.removeRoleAndDeleteUserIfEmptyRoles(customer.getUser(), Role.CUSTOMER);

        customerRepository.deleteById(id);
        log.info("customer with id {} deleted successfully", id);
    }

    @Override
    @Transactional
    public CustomerResponseDTO updateCustomer(CustomerUpdateDTO updateDTO) {
        log.info("attempt to update customer with id: {} ", updateDTO.getId());

        Customer customer = customerRepository.findById(updateDTO.getId())
                .orElseThrow(() -> new NotFoundException(Customer.class, updateDTO.getId()));

        updateFields(customer, updateDTO);
        userService.update(customer.getUser(), updateDTO);

        Customer updatedCustomer = customerRepository.save(customer);
        log.info("customer with id {} updated successfully", updatedCustomer.getId());

        return CustomerMapper.dtoFromCustomer(updatedCustomer);
    }

    @Override
    public List<CustomerResponseDTO> searchCustomer(
            List<String> fields, List<String> values
    ) {
        return customerRepository.findAll(
                        CustomerSpecification.searchCustomer(fields, values))
                .stream().map(CustomerMapper::dtoFromCustomer)
                .toList();
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordDTO dto, String username) {
        log.info("attempt to change password with username: {} ", username);

        User user = userService.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(Expert.class, username));
        userService.changePassword(
                user, dto.oldPassword(), dto.newPassword());
        log.info("password changed successfully for customer with username: {}", username);
    }

    @Override
    public CreditResponseDTO findCreditForCustomer(String username) {
        Customer customer = customerRepository.findByUser_Username(username)
                .orElseThrow(() -> new NotFoundException(Customer.class, username));
        return CreditMapper.dtoFromCredit(
                customer.getCredit()
        );
    }


    private void updateFields(Customer customer, CustomerUpdateDTO updateDTO) {
        if (updateDTO.getStatus() != null)
            customer.setStatus(updateDTO.getStatus());

        if (StringUtils.hasText(updateDTO.getPhoneNumber())) {
            if (customerRepository.existsByPhoneNumber(updateDTO.getPhoneNumber()))
                throw new ExistenceException(updateDTO.getPhoneNumber());
            customer.setPhoneNumber(updateDTO.getPhoneNumber());
        }
    }
}
