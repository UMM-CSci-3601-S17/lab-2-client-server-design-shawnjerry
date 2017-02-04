package umm3601.todo;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class TodoController {

    private Todo[] todos;

    public TodoController() throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader ("src/main/data/todos.json");
        todos = gson.fromJson(reader, Todo[].class);
    }

    // List todos
    public Todo[] listTodos(Map<String, String[]> queryParams) {
        Todo[] filteredTodos = todos;

        //Limit to a number of todos
        if(queryParams.containsKey("limit")) {
            int limit = Integer.parseInt(queryParams.get("limit")[0]);
            filteredTodos = limitTodosByNum(filteredTodos, limit);
        }

        return filteredTodos;
    }

    // Limit Todos by number
    public Todo[] limitTodosByNum(Todo[] filteredTodos, int limit) {
        return Arrays.stream(filteredTodos, 0, limit).toArray(Todo[]::new);
    }

    // Get a single todo
    public Todo getTodo(String id) {
        return Arrays.stream(todos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
    }
}
