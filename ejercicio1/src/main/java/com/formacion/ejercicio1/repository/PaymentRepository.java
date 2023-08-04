package com.formacion.ejercicio1.repository;

import com.formacion.ejercicio1.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
