package com.formacion.wiremock.repository;

import com.formacion.wiremock.DTO.PaymentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDTO, Long> {
    List<PaymentDTO> findByDebtor(String debtor);
}
