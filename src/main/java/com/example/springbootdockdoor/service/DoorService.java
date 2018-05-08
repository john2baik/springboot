package com.example.springbootdockdoor.service;

import com.example.springbootdockdoor.model.Door;

import java.util.List;

public interface DoorService {

    List<Door> getAllDoors();

    Door create(Door door);

    String delete(String id);
}
