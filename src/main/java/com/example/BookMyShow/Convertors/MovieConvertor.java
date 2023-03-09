package com.example.BookMyShow.Convertors;

import com.example.BookMyShow.Entities.MovieEntity;
import com.example.BookMyShow.EntryDTOs.MovieEntryDto;

public class MovieConvertor {

    public static MovieEntity  convertDtoToEntity(MovieEntryDto movieEntryDto)
    {
        return MovieEntity.builder().movieName(movieEntryDto.getMovieName()).language(movieEntryDto.getLanguage())
                .genre(movieEntryDto.getGenre()).ratings(movieEntryDto.getRatings())
                .duration(movieEntryDto.getDuration()).build();
    }
}
