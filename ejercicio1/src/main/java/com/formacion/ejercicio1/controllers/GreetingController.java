package com.formacion.ejercicio1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;


@RestController//Identifica clase como controller, indica que los métodos devolverán el objeto como una respuesta HTTP
@RequestMapping("/greeting")//asocia una ruta específica
public class GreetingController {

   @GetMapping("/{name}")
    public ResponseEntity<String> getName(@PathVariable String name){
        String greeting = "Hello, "+name;
        return new ResponseEntity<>(greeting, HttpStatus.OK);//El operador de inferencia permite omitir el tipo de dato
   }

   // @GetMapping("/{name}")
  //  public String getName(@PathVariable String name){
   //     String greeting = "Hello, "+name;
    //    return greeting;
  //  }
}
