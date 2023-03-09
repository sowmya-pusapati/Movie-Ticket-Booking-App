package com.example.BookMyShow.EntryDTOs;

import com.example.BookMyShow.Enums.ShowType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowEntryDto {
    private LocalDate showDate;
    private LocalTime showTime;
    private ShowType showType;
    private int theaterId;
    private int movieId;

    private int classicSeatPrice;
    private int premiumSeatPrice;

}
