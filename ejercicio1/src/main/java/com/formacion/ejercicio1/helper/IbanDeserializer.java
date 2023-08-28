package com.formacion.ejercicio1.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.iban4j.Iban;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class IbanDeserializer extends JsonDeserializer<Iban> {

    @Override
    public Iban deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        String iban = jsonParser.getText();
        Iban ibanObject = iban != null ? Iban.valueOf(iban) : null;
        return ibanObject;
    }
}










