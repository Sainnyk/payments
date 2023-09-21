package com.formacion.wiremock.service;

import com.formacion.wiremock.DTO.UserDTO;
import com.formacion.wiremock.model.User;
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
