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
    public Todo[] ListTodos(Map<String, String[]> queryParams) {
        Todo[] filteredTodos = todos;

        // Specific _id
//        if(queryParams.containsKey("_id")) {
//            String id = queryParams.get("_id")[0];
//            filteredTodos = pickOutId(filteredTodos, id);
//        }

        // Limit to a number of todos

        return filteredTodos;
    }

    // Get a single todo
    public Todo getTodo(String id) {
        return Arrays.stream(todos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
    }
}
