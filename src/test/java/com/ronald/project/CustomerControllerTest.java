package com.ronald.project;

import com.ronald.project.model.document.Customer;
import com.ronald.project.model.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest
public class CustomerControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    CustomerService customerService;

    @Test
    void TestMonoPostCustomer() {
        Customer customerdata = new Customer("1", "Sandra", 90.34);
        Mono<Customer> customerMono = Mono.just(customerdata);

        Mockito.when(customerService.saveCustomer(customerdata)).thenReturn(customerMono);

        webTestClient.post()
                .uri("/customer")
                .contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void TestFluxCustomer(){
        Flux<Customer> customerFlux = Flux.just(new Customer("1", "Alberto", 390.8),
                new Customer("2", "Carla", 243.4));

        Mockito.when(customerService.getCustomer()).thenReturn(customerFlux);

        webTestClient.get()
                .uri("/customer")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$[0].id").isEqualTo("1")
                .jsonPath("$[0].name").isEqualTo("Alberto")
                .jsonPath("$[0].balance").isEqualTo(390.8);

    }
}
