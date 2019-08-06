package com.myapp.todoactuator.repositories;

import com.myapp.todoactuator.domain.ToDo;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<ToDo,String> {

    Long countByCompleted(boolean completed);

}
