package com.example.BookMyShow.Services;

import com.example.BookMyShow.Convertors.TicketConvertor;
import com.example.BookMyShow.Entities.ShowEntity;
import com.example.BookMyShow.Entities.ShowSeatEntity;
import com.example.BookMyShow.Entities.TicketEntity;
import com.example.BookMyShow.Entities.UserEntity;
import com.example.BookMyShow.EntryDTOs.TicketEntryDto;
import com.example.BookMyShow.Repositories.ShowRepository;
import com.example.BookMyShow.Repositories.TicketRepository;
import com.example.BookMyShow.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    UserRepository userRepository;

    public String addTicket(TicketEntryDto ticketEntryDto) throws Exception
    {
        TicketEntity ticketEntity= TicketConvertor.convertDtoToEntity(ticketEntryDto);
        int showId=ticketEntryDto.getShowId();
        boolean isValidRequest=checkValidityRequestedSeats(ticketEntryDto);
        if(isValidRequest==false)
        {
            throw new Exception("seat not available");
        }

        //calculating the amount
        ShowEntity showEntity=showRepository.findById(showId).get();
        List<ShowSeatEntity> showSeatEntityList=showEntity.getShowSeatEntityList();
        List<String> reqSeats=ticketEntryDto.getRequestedSeats();
        int totalAmount=0;
        for(ShowSeatEntity showSeatEntity:showSeatEntityList)
        {
            if(reqSeats.contains(showSeatEntity.getSeatNo()))
            {
                totalAmount+=showSeatEntity.getPrice();
                showSeatEntity.setBooked(true);
                showSeatEntity.setBookedOn(new Date());
            }
        }

        ticketEntity.setTotalAmount(totalAmount);
        ticketEntity.setMoviename(showEntity.getMovieEntity().getMovieName());
        ticketEntity.setShowDate(showEntity.getShowDate());
        ticketEntity.setShowTime(showEntity.getShowTime());
        ticketEntity.setTheatreName(showEntity.getTheatreEntity().getName());

        String allotedSeats=getRequestedSeats(reqSeats);
        ticketEntity.setBookedSeats(allotedSeats);

        UserEntity userEntity=userRepository.findById(ticketEntryDto.getUserId()).get();
        ticketEntity.setUserEntity(userEntity);
        ticketEntity.setShowEntity(showEntity);

        List<TicketEntity> ticketEntityList=showEntity.getListOfTickets();
        ticketEntityList.add(ticketEntity);
        showEntity.setListOfTickets(ticketEntityList);
        showRepository.save(showEntity);

        List<TicketEntity> ticketEntityList1=userEntity.getTicketEntityList();
        ticketEntityList1.add(ticketEntity);
        userEntity.setTicketEntityList(ticketEntityList1);
        userRepository.save(userEntity);


      return "Ticket booked Successfully";



    }

    private String getRequestedSeats(List<String> reqSeats)
    {
        String result="";
        for(String seat:reqSeats)
        {
            result+=seat+ ",";
        }
       return result;
    }
    private boolean checkValidityRequestedSeats(TicketEntryDto ticketEntryDto)
    {
        int showId=ticketEntryDto.getShowId();
        List<String> reqSeats=ticketEntryDto.getRequestedSeats();
        ShowEntity showEntity=showRepository.findById(showId).get();
        List<ShowSeatEntity> showSeatEntityList=showEntity.getShowSeatEntityList();
        for(ShowSeatEntity showSeatEntity:showSeatEntityList)
        {
            String seatNo=showSeatEntity.getSeatNo();
            if(reqSeats.contains(seatNo))
            {
                if(showSeatEntity.isBooked()==true)
                {
                    return false;
                }
            }
        }
        return true;
    }
}
