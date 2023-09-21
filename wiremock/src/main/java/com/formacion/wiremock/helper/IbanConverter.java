package com.formacion.wiremock.helper;

import jakarta.persistence.AttributeConverter;
import org.iban4j.Iban;

public class IbanConverter implements AttributeConverter<Iban,String> {
    @Override
    public String convertToDatabaseColumn(Iban iban) {
        return iban.toString();
    }

    @Override
    public Iban convertToEntityAttribute(String ibanString) {
        return Iban.valueOf(ibanString);
    }
}
