package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class FilterByCategory {

    @Test
    public void filterTodosByCategory() throws IOException {
        TodoController todoController = new TodoController();
        Todo[] allTodos = todoController.listTodos(new HashMap<>());
        Todo[] videoGamesTodos = todoController.filterByCategory(allTodos, "video games");
        assertEquals("Incorrect number of todos with category video games", 71, videoGamesTodos.length);
        Todo[] homeworkTodos = todoController.filterByCategory(allTodos, "homework");
        assertEquals("correct number of todos with category homework", 79, homeworkTodos.length);
    }

    @Test
    public void listTodosWithCategoryFilter() throws IOException {
        TodoController todoController = new TodoController();
        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("category", new String[] {"video games"});
        Todo[] videoGamesTodos = todoController.listTodos(queryParams);
        assertEquals("Incorrect number of todos with category video games", 71, videoGamesTodos.length);
        queryParams.put("category", new String[] {"homework"});
        Todo[] homeworkTodos = todoController.listTodos(queryParams);
        assertEquals("Incorrect number of todos with category homework", 79, homeworkTodos.length);
    }
}
