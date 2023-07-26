package com.formacion.wiremock;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.iban4j.Iban;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Method;

@Component
public class IbanDeserializer extends JsonDeserializer<Iban> {

    @Override
    public Iban deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String ibanString = p.getText().trim();
        return createIban(ibanString);
    }

    private Iban createIban(String ibanString) {
        try {
            Method method = Iban.class.getDeclaredMethod("valueOf", String.class);
            method.setAccessible(true);
            return (Iban) method.invoke(null, ibanString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}










