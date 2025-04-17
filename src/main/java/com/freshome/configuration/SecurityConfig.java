package com.freshome.configuration;

import com.freshome.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            UserService userService
    )
            throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers(
                                        "/customer-panel.html",
                                        "/v1/customer/update/change_password",
                                        "/v1/order/create",
                                        "/v1/location/save",
                                        "/v1/customer/find_credit",
                                        "/v1/order/update/choose_expert",
                                        "/v1/review/create",
                                        "/v1/order/find/all/by_customer/**")
                                .hasRole("CUSTOMER")

                                .requestMatchers(
                                        "/admin-panel.html",
                                        "/v1/customer/search",
                                        "/v1/expert/search",
                                        "/v1/expert/update/approve/**",
                                        "/v1/expert/find/all/pending_approval",
                                        "/v1/service_category/create",
                                        "/v1/sub_service/create",
                                        "/v1/moderator/update/**",
                                        "/v1/order/find/all/history_for_customer/**",
                                        "/v1/order/find/all/history_for_expert/**",
                                        "/v1/order/search",
                                        "/v1/order/find/report_for_customer/**",
                                        "/v1/order/find/all/reports",
                                        "/v1/order/find/all/filter_reports",
                                        "/v1/offer/find/report_for_expert/**",
                                        "/v1/offer/find/all/reports",
                                        "/v1/offer/find/all/filter_report")
                                .hasRole("ADMIN")

                                .requestMatchers(
                                        "/expert-panel.html",
                                        "/v1/expert/update/add_sub_service",
                                        "/v1/expert/update/remove_sub_service",
                                        "/v1/expert/update/change_password",
                                        "/v1/offer/create",
                                        "/v1/expert/find_credit",
                                        "/v1/order/update/start/**",
                                        "/v1/order/update/execute/**",
                                        "/v1/order/find/all/by_expert/**")
                                .hasRole("EXPERT")

                                .requestMatchers(
                                        "/v1/credit/update/**")
                                .hasAnyRole("CUSTOMER", "EXPERT")

//                        .requestMatchers(
//                                "/v1/expert/update/add_sub_service",
//                                "/v1/expert/update/remove_sub_service")
//                        .hasAnyRole("ADMIN", "EXPERT")

                                .requestMatchers("/v1/customer/signup").permitAll()
                                .requestMatchers("/v1/expert/signup").permitAll()
//                                .requestMatchers("/v1/**").authenticated()
                                .anyRequest().permitAll()
                )
                .userDetailsService(userService)
                .httpBasic(Customizer.withDefaults());
//                .formLogin(Customizer.withDefaults());
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
