package com.formacion.wiremock.service;

import com.formacion.wiremock.DTO.PaymentDTO;
import com.formacion.wiremock.model.Payment;
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
