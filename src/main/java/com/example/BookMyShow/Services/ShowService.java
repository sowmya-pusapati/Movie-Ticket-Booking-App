package com.example.BookMyShow.Services;

import com.example.BookMyShow.Convertors.ShowConvertor;
import com.example.BookMyShow.Entities.*;
import com.example.BookMyShow.EntryDTOs.ShowEntryDto;
import com.example.BookMyShow.Enums.SeatType;
import com.example.BookMyShow.Repositories.MovieRepository;
import com.example.BookMyShow.Repositories.ShowRepository;
import com.example.BookMyShow.Repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheatreRepository theatreRepository;
    @Autowired
    ShowRepository showRepository;

    public String addShow(ShowEntryDto showEntryDto) throws Exception
    {
        ShowEntity showEntity= ShowConvertor.convertDtoToEntity(showEntryDto);
        int movieId=showEntryDto.getMovieId();
        int theatreId=showEntryDto.getTheaterId();

        MovieEntity movieEntity= movieRepository.findById(movieId).get();
        TheatreEntity theatreEntity=theatreRepository.findById(theatreId).get();

        showEntity.setMovieEntity(movieEntity);
        showEntity.setTheatreEntity(theatreEntity);

        List<ShowSeatEntity> showSeatEntityList=createShowSeats(showEntryDto,showEntity);
        showEntity.setShowSeatEntityList(showSeatEntityList);

        showEntity=showRepository.save(showEntity);

        List<ShowEntity> showEntityList=movieEntity.getShowEntityList();
        showEntityList.add(showEntity);
        movieEntity.setShowEntityList(showEntityList);
        movieRepository.save(movieEntity);

        List<ShowEntity> showEntityList1=theatreEntity.getListOfShows();
        showEntityList1.add(showEntity);
        theatreEntity.setListOfShows(showEntityList1);
        theatreRepository.save(theatreEntity);

        return "Show Added";
    }

    private  List<ShowSeatEntity> createShowSeats(ShowEntryDto showEntryDto,ShowEntity showEntity)
    {
        List<ShowSeatEntity> showSeatEntityList=new ArrayList<>();
        TheatreEntity theatreEntity=showEntity.getTheatreEntity();
        List<TheatreSeatEntity> theatreSeatEntityList=theatreEntity.getTheatreSeatEntityList();
        for(TheatreSeatEntity theatreSeatEntity:theatreSeatEntityList)
        {
            ShowSeatEntity showSeatEntity=new ShowSeatEntity();
            showSeatEntity.setSeatNo(theatreSeatEntity.getSeatNo());
            showSeatEntity.setSeatType(theatreSeatEntity.getSeatType());
            if(theatreSeatEntity.getSeatType().equals(SeatType.CLASSIC))
            {
                showSeatEntity.setPrice(showEntryDto.getClassicSeatPrice());
            }
            else {
                showSeatEntity.setPrice(showEntryDto.getPremiumSeatPrice());
            }
            showSeatEntity.setBooked(false);
            showSeatEntity.setShowEntity(showEntity);

           showSeatEntityList.add(showSeatEntity);

        }
        return showSeatEntityList;
    }
}
