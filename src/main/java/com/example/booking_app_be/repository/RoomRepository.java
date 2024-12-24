package com.example.booking_app_be.repository;

import com.example.booking_app_be.entity.Hotel;
import com.example.booking_app_be.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByHotel(Hotel hotel);

}