package com.ronald.project.api;

import com.ronald.project.model.document.Customer;
import com.ronald.project.model.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@EnableCaching
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /*@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "example";

    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplateReactive;*/

    @GetMapping
    public Flux<Customer> showCustomer() {
        return customerService.getCustomer();
    }

    @PostMapping
    public Mono<Customer> registerCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    /*@GetMapping("/{msg}")
    public String postMessage(@PathVariable("msg") final String msg) {
        kafkaTemplate.send(TOPIC, msg);
        return "Success";
    }

    @GetMapping("/find/{id}")
    @Cacheable(key ="#id", value = "customer")
    public Mono<Customer> findCustomer(@PathVariable String id) {
        return customerService.findCustomer(id);
    }

    @PostMapping("/check/kafka")
    public Mono<Customer> publishCustomer(@RequestBody Customer customer) {
        kafkaTemplateReactive.send(TOPIC, customer);
        return customerService.saveCustomer(customer);
    }*/
}