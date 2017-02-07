package umm3601.todo;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class GetTodoByID {

    @Test
    public void getStokesClayton() throws IOException {
        TodoController todoController = new TodoController();
        Todo todo = todoController.getTodo("588959856601f6a77b6a2862");
        assertEquals("Incorrect name", "Fry", todo.owner);
    }

    @Test
    public void getBoltonMonroe() throws IOException {
        TodoController todoController = new TodoController();
        Todo todo = todoController.getTodo("58895985e2df5ee807c2853e");
        assertEquals("Incorrect owner", "Workman", todo.owner);
    }
}
