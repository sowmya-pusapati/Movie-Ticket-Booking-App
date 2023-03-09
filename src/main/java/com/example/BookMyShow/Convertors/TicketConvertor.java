package com.example.BookMyShow.Convertors;

import com.example.BookMyShow.Entities.TicketEntity;
import com.example.BookMyShow.EntryDTOs.TicketEntryDto;

public class TicketConvertor {
    public static TicketEntity convertDtoToEntity(TicketEntryDto ticketEntryDto)
    {
        TicketEntity ticketEntity=new TicketEntity();
        return ticketEntity;

    }
}
