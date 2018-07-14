package toDoList;

import javax.persistence.*;

@Entity
public class Tasks {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String description;



    public Tasks(String description) {
        this.description = description;
    }

    public Tasks() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
