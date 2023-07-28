package com.formacion.wiremock;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.iban4j.Iban;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Method;

@Component
public class IbanDeserializer extends JsonDeserializer<Iban> {

    @Override
    public Iban deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        System.out.println("Deserializer: "+jsonParser.getText());
        String iban = jsonParser.getText();
        Iban ibanObject = iban != null ? Iban.valueOf(iban) : null;
        System.out.println("Deserializer: "+ ibanObject.toFormattedString());
        return ibanObject;
    }
}










