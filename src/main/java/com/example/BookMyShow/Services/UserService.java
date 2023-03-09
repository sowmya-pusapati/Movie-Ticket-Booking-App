package com.example.BookMyShow.Services;

import com.example.BookMyShow.Convertors.UserConvertor;
import com.example.BookMyShow.Entities.UserEntity;
import com.example.BookMyShow.EntryDTOs.UserEntryDTO;
import com.example.BookMyShow.Repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String createUser(UserEntryDTO userEntryDTO) throws Exception,NullPointerException
    {
        UserEntity userEntity= UserConvertor.convertUserDtoToEntity(userEntryDTO);

        userRepository.save(userEntity);
        return "User Added Successfully";

    }

}
