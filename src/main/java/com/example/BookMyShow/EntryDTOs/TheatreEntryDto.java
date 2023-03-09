package com.example.BookMyShow.EntryDTOs;

import lombok.Data;

@Data
public class TheatreEntryDto {
    private String name;
    private String location;

    private int premiumSeatsCount;
    private int classicSeatsCount;
}
