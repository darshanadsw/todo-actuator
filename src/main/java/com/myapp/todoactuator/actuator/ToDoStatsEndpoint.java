package com.myapp.todoactuator.actuator;

import com.myapp.todoactuator.domain.ToDo;
import com.myapp.todoactuator.repositories.ToDoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Endpoint(id = "todo-stats")
public class ToDoStatsEndpoint {

    private final ToDoRepository toDoRepository;

    @ReadOperation
    public Stats stats(){
        return new Stats(this.toDoRepository.count(),toDoRepository.countByCompleted(true));
    }

    @ReadOperation
    public ToDo getToDo(@Selector String id){
        return toDoRepository.findById(id).orElse(null);
    }

    @WriteOperation
    public Operation completedToDo(@Selector String id){
        ToDo toDo = toDoRepository.findById(id).orElse(null);
        if (toDo != null ){
            toDo.setCompleted(true);
            toDoRepository.save(toDo);
            return new Operation("COMPLETED", true);
        }

        return new Operation("COMPLETED", false);
    }

    @DeleteOperation
    public Operation removeToDo(@Selector String id){
        try{
            toDoRepository.deleteById(id);
            return new Operation("DELETED",true);
        }catch (Exception e){
            return new Operation("DELETED", false);
        }
    }


    @AllArgsConstructor
    @Data
    public class Stats {
        private Long count;
        private Long completed;
    }

    @AllArgsConstructor
    @Data
    public class Operation {
        private String name;
        private boolean successful;
    }
}
