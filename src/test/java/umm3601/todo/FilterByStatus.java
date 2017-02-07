package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class FilterByStatus {

    @Test
    public void filterTodosByStatus() throws IOException {
        TodoController todoController = new TodoController();
        Todo[] allTodos = todoController.listTodos(new HashMap<>());
        Todo[] CompleteTodos = todoController.filterByStatus(allTodos, true);
        assertEquals("Incorrect number of todos with status complete", 143, CompleteTodos.length);
        Todo[] IncompleteTodos = todoController.filterByStatus(allTodos, false);
        assertEquals("correct number of todos with status incomplete",  157, IncompleteTodos.length);
    }

    @Test
    public void listTodosWithStatusFilter() throws IOException {
        TodoController todoController = new TodoController();
        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("status", new String[] {"complete"});
        Todo[] CompleteTodos = todoController.listTodos(queryParams);
        assertEquals("Incorrect number of todos with status complete", 143, CompleteTodos.length);
        queryParams.put("status", new String[] {"incomplete"});
        Todo[] IncompleteTodos = todoController.listTodos(queryParams);
        assertEquals("Incorrect number of todos with status incomplete", 157, IncompleteTodos.length);
    }
}
