package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class FilterTodosByOwner {

    @Test
    public void filterTodosByOwner() throws IOException {
        TodoController todoController = new TodoController();
        Todo[] allTodos = todoController.listTodos(new HashMap<>());
        Todo[] BlancheTodos = todoController.filterByOwner(allTodos, "Blanche");
        assertEquals("Incorrect number of todos with owner Blanche", 43, BlancheTodos.length);
        Todo[] BarryTodos = todoController.filterByOwner(allTodos, "Barry");
        assertEquals("correct number of todos with owner Barry", 51, BarryTodos.length);
    }

    @Test
    public void listTodosWithOwnerFilter() throws IOException {
        TodoController todoController = new TodoController();
        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("owner", new String[] {"Blanche"});
        Todo[] BlancheTodos = todoController.listTodos(queryParams);
        assertEquals("Incorrect number of todos with owner Blanche", 43, BlancheTodos.length);
        queryParams.put("owner", new String[] {"Barry"});
        Todo[] BarryTodos = todoController.listTodos(queryParams);
        assertEquals("Incorrect number of todos with owner Barry", 51, BarryTodos.length);
    }
}
