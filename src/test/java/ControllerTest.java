import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import toDoList.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class ControllerTest {

    // @Autowired
    //Controller controller;
    @Autowired
    DataBaseNoSql dataBaseNoSql;
    @Autowired
    Controller controller;
    @Autowired
    TasksNoSql tasksNoSql;

    @Test
    public void createListTableTest() {
        //given
        List<ToDoList> list = new ArrayList<>();
        dataBaseNoSql.findAll().forEach(list::add);
        Assert.assertTrue(list.size() == 0);
        //when
        controller.createList("taskList");
        dataBaseNoSql.findAll().forEach(list::add);
        //than
        Assert.assertTrue(list.size() == 1);
        list.clear();
        controller.createList("taskList");
        controller.createList("shoppingList");
        dataBaseNoSql.findAll().forEach(list::add);
        Assert.assertEquals(2, list.size());

    }

    @Test(expected = NullPointerException.class)
    public void createListTableTestShouldThrowException() {
        controller.createList(null);
        controller.createList("");

    }

    @Test
    public void getAllListTest() {
        //given
        Assert.assertTrue(controller.getAllList().size() == 0);

        //when

        controller.createList("taskList");
        controller.createList("shoppinList");

        //than

        Assert.assertTrue(controller.getAllList().size() == 2);


    }

    @Test
    public void addTaskTest(){

        //given
        List<ToDoList>allListToDo= new ArrayList<>();
        allListToDo=controller.getAllList();
        List<Tasks> tasksList =new ArrayList<>();
        tasksNoSql.findAll().forEach(tasksList::add);

        Assert.assertTrue(allListToDo.size()==0);
        Assert.assertTrue(tasksList.size()==0);

        allListToDo.clear();
        tasksList.clear();

        controller.createList("zadania");
        allListToDo=controller.getAllList();
        Assert.assertTrue(allListToDo.size()==1);

        controller.addTask("zadania","posprzatac");
        tasksNoSql.findAll().forEach(tasksList::add);
        Assert.assertEquals(1,tasksList.size());





    }

}
