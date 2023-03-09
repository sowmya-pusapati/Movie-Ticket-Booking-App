package com.example.BookMyShow.EntryDTOs;

import com.example.BookMyShow.Enums.Genre;
import com.example.BookMyShow.Enums.Language;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class MovieEntryDto {
    private String movieName;
    private double ratings;
    private int duration;

    private Language language;

    private Genre genre;
}
