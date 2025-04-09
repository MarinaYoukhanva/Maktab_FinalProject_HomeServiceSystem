package com.freshome.service.impl;

import com.freshome.dto.ChangePasswordDTO;
import com.freshome.dto.credit.CreditResponseDTO;
import com.freshome.entity.Credit;
import com.freshome.entity.Customer;
import com.freshome.dto.credit.CreditCreateDTO;
import com.freshome.dto.customer.CustomerCreateDTO;
import com.freshome.dto.customer.CustomerResponseDTO;
import com.freshome.dto.customer.CustomerUpdateDTO;
import com.freshome.entity.entityMapper.CreditMapper;
import com.freshome.entity.entityMapper.CustomerMapper;
import com.freshome.exception.ChangePasswordException;
import com.freshome.exception.ExistenceException;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.CustomerRepository;
import com.freshome.service.CreditService;
import com.freshome.service.CustomerService;
import com.freshome.specification.CustomerSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
//@Transactional
//@Validated
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final CreditService creditService;

    @Override
    @Transactional
    public CustomerResponseDTO createCustomer(CustomerCreateDTO customerCreateDTO) {

        log.info("attempt to create customer");

        if (customerRepository.existsByEmail(customerCreateDTO.getEmail()))
            throw new ExistenceException("email");
        if (customerRepository.existsByPhoneNumber(customerCreateDTO.getPhoneNumber()))
            throw new ExistenceException("phoneNumber");

        Customer customer = CustomerMapper.customerFromDto(customerCreateDTO);
        Credit credit = creditService.createReturnCredit(new CreditCreateDTO(0L));
        customer.setCredit(credit);

        Customer savedCustomer = customerRepository.save(customer);
        log.info("customer with id {} saved successfully", savedCustomer.getId());

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
    public List<Customer> findAll(){
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

        if (!customerRepository.existsById(id))
            throw new NotFoundException(Customer.class, id);

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
    public void changePassword(Long customerId, ChangePasswordDTO dto) {
        log.info("attempt to change password with id: {} ", customerId);

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException(Customer.class, customerId));
        if (dto.oldPassword().equals(dto.newPassword())
                || !passwordEncoder.matches(dto.oldPassword(), customer.getPassword()))
            throw new ChangePasswordException();

        customer.setPassword(passwordEncoder.encode(dto.newPassword()));
        customerRepository.save(customer);
        log.info("password changed successfully for customer with id{}", customerId);
    }

    @Override
    public CreditResponseDTO findCreditForCustomer(Long customerId){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException(Customer.class, customerId));
        return CreditMapper.dtoFromCredit(
                customer.getCredit()
        );
    }


    private void updateFields (Customer customer, CustomerUpdateDTO updateDTO){
        if (StringUtils.hasText(updateDTO.getFirstname()))
            customer.setFirstname(updateDTO.getFirstname());

        if (StringUtils.hasText(updateDTO.getLastname()))
            customer.setLastname(updateDTO.getLastname());

        if (StringUtils.hasText(updateDTO.getEmail())){
            if (customerRepository.existsByEmail(updateDTO.getEmail()))
                throw new ExistenceException("email");
            customer.setEmail(updateDTO.getEmail());
        }

        if (updateDTO.getStatus() != null)
            customer.setStatus(updateDTO.getStatus());

        if (StringUtils.hasText(updateDTO.getPhoneNumber()))
            customer.setPhoneNumber(updateDTO.getPhoneNumber());
    }
}
