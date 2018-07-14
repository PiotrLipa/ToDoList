
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

import javax.persistence.EntityNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)

public class TestUpdateTask {

    @Autowired
    TasksNoSql taskNoSql;

    @Autowired
    Controller controller;

    @Test (expected = EntityNotFoundException.class)
    public void testMissigTaskInDataBase(){
        controller.updateTask(0, "newUpdate");

    }
    @Test
    public void testChangeDescriptionOfTask(){
        Tasks tasksInDataBase= taskNoSql.save(new Tasks("ChangeDiscription"));
        controller.updateTask(tasksInDataBase.getId(), "NewDiscription");
        Assert.assertEquals("NewDiscription", taskNoSql.findById(tasksInDataBase.getId()).get().getDescription());


    }
    @Test
    public void testNullInPlaceDescriptionOfTask(){
        Tasks tasksInDataBase= taskNoSql.save(new Tasks("NullPlaceDescription"));
        controller.updateTask(tasksInDataBase.getId(), "NullDiscrition");
        Assert.assertNull(null, null);

    }
    @Test
    public void testEmptyValueInDescriptionOfTask(){
        Tasks tasksInDataBase= taskNoSql.save(new Tasks("EmptyValuesInDescription"));
        controller.updateTask(tasksInDataBase.getId(), "EmtyValues");
        Assert.assertEquals("", "");

    }
    @Before
    public void setUp(){
        taskNoSql.deleteAll();
    }
}
