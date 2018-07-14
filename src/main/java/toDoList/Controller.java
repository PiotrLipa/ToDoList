package toDoList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@RestController  //adnotacja dzięk której klasa opsługuje żądania REST, w ten sposób tworzy kontroler, który zwraca dane teskstowe a nie kod html
public class Controller {


    @Autowired
    DataBaseNoSql dataBaseNoSql;
    @Autowired
    TasksNoSql tasksNoSql;


    @RequestMapping("/createList") //pozwala określić adres, pod jakim ma być dostępny kontroler
    public String createList(@RequestParam(value = "listName") String listName) {
        if(listName==null||listName.length()==0){
            throw new NullPointerException();
        }
        ToDoList toDoList = new ToDoList(listName);
        dataBaseNoSql.save(toDoList);
        return "lista zrobiona: " + listName;

    }

    @RequestMapping("/allList")

    public List<ToDoList> getAllList() {
        List<ToDoList> lists = new ArrayList<>();
        dataBaseNoSql.findAll().forEach(lists::add);
        return lists;
    }

    @RequestMapping(value = "/addTask/{id}", method = RequestMethod.POST)
    public String addTask(@PathVariable("id") String listName, @RequestParam(value = "task") String taskDescription) {
//@RequestParam -pozwala pobrać wartość parametru przekazywanego w adresie url np. “?userId=5”, do zmiennej
        //@PathVariable — obsługuje zmienną przekazaną w postaci fragmentu adresu url np. user/5 (user/{userId})

        ToDoList toDoList = dataBaseNoSql.findById(listName).get();
        Tasks tasks = tasksNoSql.save(new Tasks(taskDescription));
        toDoList.getTasksList().add(tasks);
        dataBaseNoSql.save(toDoList);

        return "Zadanie dodane do listy: " + listName;


    }

    @RequestMapping("/remove/{id}")
    public String removeTask(@PathVariable("id") long id) {
        List<Tasks> list = new ArrayList<>();
        tasksNoSql.findAll().forEach(list::add);
        list.remove(tasksNoSql.findById(id).get());
        tasksNoSql.deleteById(id);

        /*ToDoList toDoList = dataBaseNoSql.findById(listName).get();
        toDoList.getTasksList().remove(tasksNoSql.findById(id));
        dataBaseNoSql.save(toDoList);
        tasksNoSql.deleteById(id);
        */

        return "zadanie zostało usunięte";


    }

    @RequestMapping("/updateTask/{id}")
    public String updateTask(@PathVariable("id") long id, @RequestParam(value = "taskToEdit") String taskToEdit) {
        tasksNoSql.findById(id).map(tasks ->
        {
            tasks.setDescription(taskToEdit);
            return tasksNoSql.save(tasks);
        }).orElseThrow(()->new EntityNotFoundException("id="+id));

        return "zadanie zostało zmienione";

    }
    @RequestMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable("id") Long id){
        tasksNoSql.deleteById(id);
        return "skasowane zadania";
    }
    @RequestMapping("/allTasks")
    public List<Tasks> returnAllTasks(){
        List<Tasks> lists = new ArrayList<>();
        tasksNoSql.findAll().forEach(lists::add);
        return lists;
    }

}
