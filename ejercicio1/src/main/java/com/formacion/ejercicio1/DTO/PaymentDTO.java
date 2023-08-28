package com.formacion.ejercicio1.DTO;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class PaymentDTO {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private int payment_id;

        private String dbtr_nm;
        private String debtor;
        private String dbtr_ctry;

        private String cdtr_nm;
        private String creditor;

        private BigDecimal amount;
        private Timestamp cre_dt_tm;



    }
