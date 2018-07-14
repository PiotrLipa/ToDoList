package toDoList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class ToDoList {


    @Id
    private String name;
    @OneToMany
    private List<Tasks> tasksList;


    public List<Tasks> getTasksList() {
        return tasksList;
    }

    public void setTasksList(List<Tasks> tasksList) {
        this.tasksList = tasksList;
    }

    public ToDoList(String name) {
        this.name = name;
    }

    public ToDoList() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
