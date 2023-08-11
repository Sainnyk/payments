package com.formacion.ejercicio1.DTO;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.formacion.ejercicio1.IbanDeserializer;
import com.formacion.ejercicio1.IbanSerializer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.iban4j.Iban;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

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
