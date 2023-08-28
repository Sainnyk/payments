package com.formacion.ejercicio1.controllers;

import com.formacion.ejercicio1.DTO.PaymentDTO;
import com.formacion.ejercicio1.Ejercicio1Application;
import com.formacion.ejercicio1.repository.PaymentRepository;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = Ejercicio1Application.class)
@Testcontainers
@TestPropertySource(properties = {
        "spring.test.database.replace=none",
        "spring.flyway.enabled=true",
        "spring.flyway.url=${spring.datasource.url}", // Usa la misma URL del contenedor
        "spring.flyway.user=${spring.datasource.username}", // Usuario del contenedor
        "spring.flyway.password=${spring.datasource.password}",// Contraseña del contenedor
        "spring.flyway.locations=classpath:db/migration"
})
class PaymentControllerTest {

    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("dbtest")
            .withUsername("user")
            .withPassword("password");

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private Flyway flyway;

    @BeforeAll
    static void beforeAll() {
        postgresContainer.start();
    }

    @BeforeEach
    void beforeEach() {
        flyway.migrate();
    }

    @AfterAll
    static void afterAll() {
        postgresContainer.stop();
    }


    @Test
    void getPaymentsByName() {

            List<PaymentDTO> payments = paymentRepository.findAll();
            assertThat(payments)
                    .isNotNull()
                    .isNotEmpty();
            System.out.println(payments);

    }
}