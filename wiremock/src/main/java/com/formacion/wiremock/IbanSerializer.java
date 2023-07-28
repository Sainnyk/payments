package com.formacion.wiremock;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.iban4j.Iban;

import java.io.IOException;

public class IbanSerializer extends JsonSerializer<Iban>  {

@Override
public void serialize(Iban value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
        gen.writeString(value.toString());
        }
   }
}