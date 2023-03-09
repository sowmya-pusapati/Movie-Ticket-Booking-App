package com.example.BookMyShow.Convertors;

import com.example.BookMyShow.Entities.ShowEntity;
import com.example.BookMyShow.EntryDTOs.ShowEntryDto;

public class ShowConvertor {
    public static ShowEntity convertDtoToEntity(ShowEntryDto showEntryDto)
    {
        return ShowEntity.builder().showDate(showEntryDto.getShowDate()).showTime(showEntryDto.getShowTime())
                .showType(showEntryDto.getShowType()).build();
    }
}
