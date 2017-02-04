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

        //filters by "status"
        if(queryParams.containsKey("status")){
            String status = queryParams.get("status")[0];
            String True = "complete";
            String False = "incomplete";
            if(status.equals(True)){
               filteredTodos = sortByStatus(filteredTodos, true);
            }else if(status.equals(False)){
               filteredTodos = sortByStatus(filteredTodos, false);
            }
        }

        // filter by "contains"
        if(queryParams.containsKey("contains")) {
            String contains =queryParams.get("contains")[0];

        }

        return filteredTodos;
    }

    // Limit Todos by number
    public Todo[] limitTodosByNum(Todo[] filteredTodos, int limit) {
        return Arrays.stream(filteredTodos, 0, limit).toArray(Todo[]::new);
    }

    // Sort by status
    public Todo[] sortByStatus(Todo[] filteredTodos, boolean status){
        return Arrays.stream(filteredTodos).filter(x -> x.status == status).toArray(Todo[]::new);
    }

    // Sort by contains
//    public Todo[] sortByContains(Todo[] filteredTodos, String contains) {
//        Todo[] todoCon;
//
//        return
//    }

    // find contains in one todo's body
    public boolean checkBody(Todo todo, String contains) {
        CharSequence cs = contains;
        boolean bo;
        bo = todo.body.contains(cs);
        return bo;
    }

    // Get a single todo
    public Todo getTodo(String id) {
        return Arrays.stream(todos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
    }
}
