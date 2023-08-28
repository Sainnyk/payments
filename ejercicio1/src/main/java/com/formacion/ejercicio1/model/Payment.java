package com.formacion.ejercicio1.model;

import java.math.BigDecimal;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.formacion.ejercicio1.helper.IbanConverter;
import com.formacion.ejercicio1.helper.IbanDeserializer;
import com.formacion.ejercicio1.helper.IbanSerializer;
import jakarta.persistence.*;
import lombok.*;
import org.iban4j.Iban;

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


