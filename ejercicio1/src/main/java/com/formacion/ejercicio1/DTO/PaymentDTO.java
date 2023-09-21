package com.formacion.ejercicio1.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
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
        @JsonProperty
        private Long id;
        @JsonProperty
        private int payment_id;
        @JsonProperty
        private String dbtr_nm;
        @JsonProperty
        private String debtor;
        @JsonProperty
        private String dbtr_ctry;
        @JsonProperty
        private String cdtr_nm;
        @JsonProperty
        private String creditor;
        @JsonProperty
        private BigDecimal amount;
        @JsonProperty
        private Timestamp cre_dt_tm;


        public PaymentDTO(Long id, int payment_id, String dbtr_nm, String debtor, String dbtr_ctry, String cdtr_nm, String creditor, BigDecimal amount, long cre_dt_tm) {
                this.id = id;
                this.payment_id = payment_id;
                this.dbtr_nm = dbtr_nm;
                this.debtor = debtor;
                this.dbtr_ctry = dbtr_ctry;
                this.cdtr_nm = cdtr_nm;
                this.creditor = creditor;
                this.amount = amount;
                this.cre_dt_tm = new Timestamp(cre_dt_tm);
        }
}
