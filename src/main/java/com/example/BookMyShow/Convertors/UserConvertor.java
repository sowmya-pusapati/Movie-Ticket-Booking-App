package com.example.BookMyShow.Convertors;

import com.example.BookMyShow.Entities.UserEntity;
import com.example.BookMyShow.EntryDTOs.UserEntryDTO;

public class UserConvertor {
    public static UserEntity convertUserDtoToEntity(UserEntryDTO userEntryDTO)
    {
        return UserEntity.builder().name(userEntryDTO.getName()).email(userEntryDTO.getEmail()).address(userEntryDTO.getAddress())
                .mobileNumber(userEntryDTO.getMobileNumber()).build();
    }
}
