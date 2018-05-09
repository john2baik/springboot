package com.example.springbootdockdoor.controller;

import com.example.springbootdockdoor.model.Door;
import com.example.springbootdockdoor.service.DoorService;
import com.example.springbootdockdoor.service.SpannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin
public class HomeController {

    @Autowired
    DoorService doorService;
    SpannerService spannerService;

    Logger LOGGER = Logger.getLogger(HomeController.class.getName());

    @RequestMapping(value = "/")
    public String index(){
        return "index.html";
    }

    @GetMapping("/api/getAllDoors")
    public List<Door> list(){
        System.out.println("Getting all Doors");
        return doorService.getAllDoors();
    }

    @GetMapping("/api/getDoor")
    public Door getDoor(@RequestParam(value = "id", required = false) String id) {
        System.out.println("Getting door with ID: " + id);
        return doorService.getDoor(id);
    }

    @RequestMapping(value = "/api/addDoor", method = RequestMethod.POST)
    public Door create(@RequestBody Door door){
        if(door != null)
        System.out.println(door.toString());
        else System.out.println("Door is null");
        return doorService.create(door);
    }

    //@DeleteMapping("/api/deleteDoor")
    @DeleteMapping(value = "api/deleteDoor")
    public String delete(@RequestParam(value="id", required = false) String id){
        System.out.println("ID is : " + id);
        return doorService.delete(id);
    }





}
