import javafx.concurrent.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import toDoList.Application;
import toDoList.Controller;
import toDoList.Tasks;
import toDoList.TasksNoSql;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)

public class TestReturnAllTasks {

    @Autowired
    Controller controller;

    @Autowired
    TasksNoSql taskNoSql;

    @Test
    public void testAllTasksWhenDataBaseIsEmpty(){
        Assert.assertTrue(controller.returnAllTasks().size()==0);

    }
    @Test
    public void testAllTasksWhenDataBaseHaveTasks(){

        List<Tasks> listTasks = new ArrayList<>();
        taskNoSql.save(new Tasks("zakupy"));
        taskNoSql.save(new Tasks("zakupy"));
        Assert.assertEquals(2, controller.returnAllTasks().size());


    }
    @Before
    public void setUp(){
        taskNoSql.deleteAll();
    }
}
