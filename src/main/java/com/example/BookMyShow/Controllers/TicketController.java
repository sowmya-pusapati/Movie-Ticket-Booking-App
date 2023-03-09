package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.EntryDTOs.TicketEntryDto;
import com.example.BookMyShow.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;
    @PostMapping("/book")
    public ResponseEntity<String> bookTicket(@RequestBody TicketEntryDto ticketEntryDto)
    {
        try{
            String response=ticketService.addTicket(ticketEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            String result="Request Declined";
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
    }
}
