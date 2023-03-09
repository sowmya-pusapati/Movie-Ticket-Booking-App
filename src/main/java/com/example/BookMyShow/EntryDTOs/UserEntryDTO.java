package com.example.BookMyShow.EntryDTOs;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NonNull;
@Data
public class UserEntryDTO {
    private int id;
    private String name;

    private String email;

    private String mobileNumber;
    private String address;
}
