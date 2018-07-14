package toDoList;

import org.springframework.data.repository.CrudRepository;



public interface TasksNoSql extends CrudRepository<Tasks,Long> {




}

