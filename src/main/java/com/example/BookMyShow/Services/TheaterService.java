package com.example.BookMyShow.Services;

import com.example.BookMyShow.Convertors.TheatreConvertor;
import com.example.BookMyShow.Entities.TheatreEntity;
import com.example.BookMyShow.Entities.TheatreSeatEntity;
import com.example.BookMyShow.EntryDTOs.TheatreEntryDto;
import com.example.BookMyShow.Enums.SeatType;
import com.example.BookMyShow.Repositories.TheatreRepository;
import com.example.BookMyShow.Repositories.TheatreSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

  //  @Autowired
   // TheatreSeatRepository theatreSeatRepository;
    @Autowired
    TheatreRepository theatreRepository;

    public String addTheater(TheatreEntryDto theatreEntryDto) throws Exception
    {
        TheatreEntity theatreEntity= TheatreConvertor.convertDtoToEntity(theatreEntryDto);
        List<TheatreSeatEntity> theatreSeatEntityList=createTheatreSeats(theatreEntryDto,theatreEntity);

        theatreEntity.setTheatreSeatEntityList(theatreSeatEntityList);
        theatreRepository.save(theatreEntity);
        return "Theater Added Successfully";
    }
    private List<TheatreSeatEntity> createTheatreSeats(TheatreEntryDto theatreEntryDto,TheatreEntity theatreEntity)
    {
        int noClassicSeats=theatreEntryDto.getClassicSeatsCount();
        int noPremiumSeats=theatreEntryDto.getPremiumSeatsCount();
        List<TheatreSeatEntity> theatreSeatEntityList=new ArrayList<>();

        for(int count=1;count<=noClassicSeats;count++)
        {
            TheatreSeatEntity theatreSeatEntity=TheatreSeatEntity.builder()
                    .seatType(SeatType.CLASSIC).seatNo(count+"C")
                    .theatreEntity(theatreEntity).build();

             theatreSeatEntityList.add(theatreSeatEntity);
        }

        for(int count=1;count<=noPremiumSeats;count++)
        {
            TheatreSeatEntity theatreSeatEntity=TheatreSeatEntity.builder()
                    .seatType(SeatType.PREMIUM).seatNo(count+"P")
                    .theatreEntity(theatreEntity).build();

            theatreSeatEntityList.add(theatreSeatEntity);
        }


        return theatreSeatEntityList;

    }
}
