package com.formacion.ejercicio1.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(GreetingController.class)//Indica que se está probando la clase GreetingController. Se carga el contexto web mínimo
class GreetingControllerTest {


    @Autowired//Inyeccion automatica
    private MockMvc mock;//Permite realizar solicitudes simuladas

    @Test//Metodo de prueba
    void getName() throws Exception{//perform lanza una excepción generica que debe ser recogida

        String name = "Jose";

        mock.perform(get("/greeting/"+ name))//request simulado a esa ruta de GreetingController
                .andExpect(status().isOk())//Verifica que la respuesta sea un 200
                .andExpect(content().string("Hello, "+name));//Verifica que el contenido de la respuesta
                                                //coincida con la cadena esperadas
    }
}