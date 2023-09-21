package com.formacion.wiremock.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.formacion.wiremock.helper.IbanConverter;
import com.formacion.wiremock.helper.IbanDeserializer;
import com.formacion.wiremock.helper.IbanSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iban4j.Iban;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonDeserialize(using = IbanDeserializer.class)
    @JsonSerialize(using = IbanSerializer.class)
    @Convert(converter = IbanConverter.class)
    private Iban debtor;

    @JsonDeserialize(using = IbanDeserializer.class)
    @JsonSerialize(using = IbanSerializer.class)
    @Convert(converter = IbanConverter.class)
    private Iban creditor;

    private BigDecimal amount;


}


