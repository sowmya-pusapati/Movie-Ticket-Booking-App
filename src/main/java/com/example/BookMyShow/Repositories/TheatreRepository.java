package com.example.BookMyShow.Repositories;

import com.example.BookMyShow.Entities.TheatreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<TheatreEntity,Integer> {
}
