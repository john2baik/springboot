package com.example.springbootdockdoor.service;

import com.example.springbootdockdoor.model.Door;
import com.google.cloud.spanner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DoorServiceImpl implements DoorService {

    @Autowired
    SpannerService spannerService;


    public List<Door> getAllDoors(){
        List<Door> doors = new ArrayList<>();
        DatabaseClient dbClient = spannerService.getDatabaseClient();
        ResultSet resultSet = dbClient.singleUse().executeQuery(Statement.of("SELECT * FROM Door"));

        while (resultSet.next()) {
            doors.add(new Door(resultSet.getString(0), resultSet.getString(1)));
        }

        List<Door> doorList = sort(doors);

        return doorList;

    }

    public Door create(Door door){
        String id = door.getId();
        String name = door.getName();
        System.out.println(id + " : " + name);
        DatabaseClient dbClient = spannerService.getDatabaseClient();
       List<Mutation> mutation = new ArrayList<>();

       mutation.add(
               Mutation.newInsertBuilder("Door")
               .set("id")
               .to(id)
               .set("name")
               .to(name)
               .build()
       );

       dbClient.write(mutation);
       return door;

    }

    public String delete( String id){
        DatabaseClient dbClient = spannerService.getDatabaseClient();
        try{
            dbClient.writeAtLeastOnce(Arrays.asList(Mutation.delete("Door", Key.of(id))));
            return "Success";
        } catch (Exception e){
            return "Error on delete";
        }

    }

    public Door getDoor(String id){
        System.out.println("ID for getDoor is : " + id);
        DatabaseClient dbClient = spannerService.getDatabaseClient();
        ResultSet resultSet = dbClient.singleUse().executeQuery(Statement.of("SELECT * FROM Door WHERE id = '" + id + "'"));
        Door door = new Door();
        while(resultSet.next()){
            door = new Door(resultSet.getString("id"), resultSet.getString("name"));
        }
        return door;
    }

    public List<Door> sort(List<Door> doors){
        Collections.sort(doors, new Comparator<Door>() {
            @Override
            public int compare(Door o1, Door o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return doors;
    }

}
