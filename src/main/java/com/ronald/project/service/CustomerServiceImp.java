package com.ronald.project.service;

import com.ronald.project.model.document.Customer;
import com.ronald.project.model.repository.CustomerRepository;
import com.ronald.project.model.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Flux<Customer> getCustomer() {
        return this.customerRepository.findAll();
    }

    @Override
    public Mono<Customer> saveCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    /*@Override
    public Mono<Customer> findCustomer(String id) {
        System.out.println("Cacheable data");
        return this.customerRepository.findById(id);
    }*/
}