package com.vaibhav.controller;

import com.vaibhav.model.Customer;
import com.vaibhav.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        try {
            String hashCode = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashCode);
            Customer savedCustomer =  customerRepository.save(customer);
            if (savedCustomer.getId()>0){
                return ResponseEntity.status(HttpStatus.CREATED).
                        body("Given user details successfully created");
            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                        body("user registration failed");
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An exception occurred: "+e.getMessage());
        }
    }
}
