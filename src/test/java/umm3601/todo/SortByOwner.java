package umm3601.todo;

import org.junit.Test;
import java.io.IOException;
import java.util.HashMap;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;



public class SortByOwner {
    @Test
    public void sortByOwner() throws IOException {
        TodoController todoController = new TodoController();
        Todo[] allTodos = todoController.listTodos(new HashMap<>());
        Todo[] SortedByOwnerTodos = todoController.sortByOwner(allTodos);
        assertTrue(todoController.isSorted(SortedByOwnerTodos));

    }
}
