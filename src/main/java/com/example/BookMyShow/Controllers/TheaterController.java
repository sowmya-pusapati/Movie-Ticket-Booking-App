package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.EntryDTOs.TheatreEntryDto;
import com.example.BookMyShow.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theatre")
public class TheaterController {
 @Autowired
    TheaterService theaterService;
 @PostMapping("/add")
    public ResponseEntity<String> addTheater(@RequestBody TheatreEntryDto theatreEntryDto)
 {
     try{
         String response=theaterService.addTheater(theatreEntryDto);
         return  new ResponseEntity<>(response, HttpStatus.CREATED);
     }
     catch(Exception e)
     {
         String message=" Theatre not added";
         return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
     }
 }


}
