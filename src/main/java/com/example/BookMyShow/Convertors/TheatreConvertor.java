package com.example.BookMyShow.Convertors;

import com.example.BookMyShow.Entities.TheatreEntity;
import com.example.BookMyShow.EntryDTOs.TheatreEntryDto;

public class TheatreConvertor {
    public static TheatreEntity convertDtoToEntity(TheatreEntryDto theatreEntryDto)
    {
        return TheatreEntity.builder().name(theatreEntryDto.getName()).location(theatreEntryDto.getLocation()).build();
    }
}
