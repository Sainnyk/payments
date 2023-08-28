package com.formacion.ejercicio1.service;

import com.formacion.ejercicio1.DTO.PaymentDTO;
import com.formacion.ejercicio1.model.Payment;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentMapper {

    public List<PaymentDTO> toDtoList(List<Payment> payments){
        List<PaymentDTO> dtoList = new ArrayList<>();
        for(Payment payment : payments){
            PaymentDTO paymentDTO = new PaymentDTO();
            BeanUtils.copyProperties(payment,paymentDTO);
            dtoList.add(paymentDTO);
        }
        return dtoList;
    }

    public PaymentDTO toDto(Payment payment){
            PaymentDTO paymentDTO = new PaymentDTO();
            BeanUtils.copyProperties(payment,paymentDTO);

        return paymentDTO;
    }
}
