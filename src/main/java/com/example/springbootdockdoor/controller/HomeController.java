package com.example.springbootdockdoor.controller;

import com.example.springbootdockdoor.model.Door;
import com.example.springbootdockdoor.service.DoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class HomeController {

    @Autowired
    DoorService doorService;

    @RequestMapping(value = "/")
    public String index(){
        return "index.html";
    }

    @GetMapping("api/getAllDoors")
    public List<Door> list(){
        return doorService.getAllDoors();

    }

    @PostMapping("/api/addDoor")
    public Door create(@RequestBody Door door){
        System.out.println("Hit post route");
        System.out.println("ID: " + door.getId() + " Name: " + door.getName());

        return doorService.create(door);
    }

    //@DeleteMapping("api/deleteDoor/{id}")
    @RequestMapping(value = "api/deleteDoor/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String id){
        System.out.println("ID is : " + id);
        return doorService.delete(id);
    }

}
