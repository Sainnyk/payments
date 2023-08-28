package com.formacion.ejercicio1.repository;

import com.formacion.ejercicio1.DTO.PaymentDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDTO, Long> {
    List<PaymentDTO> findByDebtor(String debtor);
}
