package com.formacion.ejercicio1.service;

import com.formacion.ejercicio1.DTO.PaymentDTO;
import com.formacion.ejercicio1.DTO.UserDTO;
import com.formacion.ejercicio1.model.Payment;
import com.formacion.ejercicio1.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public List<UserDTO> toDtoList(List<User> users){
        List<UserDTO> dtoList = new ArrayList<>();
        for(User user : users){
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user,userDTO);
            dtoList.add(userDTO);
        }
        return dtoList;
    }

    public UserDTO toDto(User user){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);

        return userDTO;
    }
}
