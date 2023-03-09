package com.example.BookMyShow.EntryDTOs;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TicketEntryDto {
    private int showId;
    List<String> requestedSeats=new ArrayList<>();
    private int userId;
}
