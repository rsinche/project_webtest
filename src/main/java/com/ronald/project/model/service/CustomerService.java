package com.ronald.project.model.service;

import com.ronald.project.model.document.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Flux<Customer> getCustomer();
    Mono<Customer> saveCustomer(Customer customer);
    //Mono<Customer> findCustomer(String id);
}
