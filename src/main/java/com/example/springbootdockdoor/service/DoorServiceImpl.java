package com.example.springbootdockdoor.service;

import com.example.springbootdockdoor.model.Door;
import com.google.cloud.spanner.*;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DoorServiceImpl implements DoorService {



    public List<Door> getAllDoors(){
        List<Door> doors = new ArrayList<>();
        SpannerOptions options = SpannerOptions.newBuilder().build();
        Spanner spanner = options.getService();


        // Creates a database client
        DatabaseClient dbClient = spanner.getDatabaseClient(DatabaseId.of(
                "dock-doors", "test-spanner", "test-database"));
        ResultSet resultSet = dbClient.singleUse().executeQuery(Statement.of("SELECT * FROM Door"));

        System.out.println("\n\nResults:");
        // Prints the results
        while (resultSet.next()) {
            doors.add(new Door(resultSet.getString(0), resultSet.getString(1)));
        }
        return doors;

    }

    public Door create(Door door){
        String id = door.getId();
        String name = door.getName();
        System.out.println(id + " : " + name);
        SpannerOptions options = SpannerOptions.newBuilder().build();
        Spanner spanner = options.getService();


        // Creates a database client
        DatabaseClient dbClient = spanner.getDatabaseClient(DatabaseId.of(
                "dock-doors", "test-spanner", "test-database"));

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
        System.out.println(id + "= ID");
        SpannerOptions options = SpannerOptions.newBuilder().build();
        Spanner spanner = options.getService();

        // Creates a database client
        DatabaseClient dbClient = spanner.getDatabaseClient(DatabaseId.of(
                "dock-doors", "test-spanner", "test-database"));

        List<Mutation> mutation = new ArrayList<>();

        mutation.add(
                Mutation.delete("Door", KeySet.singleKey(Key.newBuilder().append(id).build())));


        dbClient.write(mutation);
        return "Success";
    }

}
