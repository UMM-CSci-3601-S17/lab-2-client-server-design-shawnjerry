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
               filteredTodos = filterByStatus(filteredTodos, true);
            }else if(status.equals(False)){
               filteredTodos = filterByStatus(filteredTodos, false);
            }
        }

        // filter by "contains"
        if(queryParams.containsKey("contains")) {
            String contains =queryParams.get("contains")[0];
            filteredTodos = filterByContains(filteredTodos, contains);

        }

        //filter by owner
        if(queryParams.containsKey("owner")){
            String owner = queryParams.get("owner")[0];
            filteredTodos = filterByOwner(filteredTodos, owner);
        }
        //filter by category
        if(queryParams.containsKey("category")) {
            String category = queryParams.get("category")[0];
            filteredTodos = filterByCategory(filteredTodos, category);
        }
        //sorting
        if(queryParams.containsKey("orderBy")) {
            String orderBy = queryParams.get("orderBy")[0];
            if(orderBy.equals("owner")){
                filteredTodos = sortByOwner(filteredTodos);
            }
            if(orderBy.equals("status")){
                filteredTodos = sortByStatus(filteredTodos);
            }
            if(orderBy.equals("body")){
                filteredTodos = sortByBody(filteredTodos);
            }
            if(orderBy.equals("category")){
                filteredTodos = sortByCategory(filteredTodos);
            }


            //filteredTodos = sortByCategory(filteredTodos, orderBy);
        }


            return filteredTodos;
    }

    // Limit Todos by number
    public Todo[] limitTodosByNum(Todo[] filteredTodos, int limit) {
       return Arrays.stream(filteredTodos, 0, limit).toArray(Todo[]::new);

    }

    //filter by owner method
    public Todo[] filterByOwner(Todo[] filteredTodos, String owner){

        return Arrays.stream(filteredTodos).filter(x -> x.owner.equals(owner)).toArray(Todo[]::new);
    }

    // filter by category

    public Todo[] filterByCategory(Todo[] filteredTodos, String category){
        return Arrays.stream(filteredTodos).filter(x -> x.category.equals(category)).toArray(Todo[]::new);
    }

    // filter by status
    public Todo[] filterByStatus(Todo[] filteredTodos, boolean status){
        return Arrays.stream(filteredTodos).filter(x -> x.status == status).toArray(Todo[]::new);
    }

    // filter by contains
    public Todo[] filterByContains(Todo[] filteredTodos, String contains) {
        return Arrays.stream(filteredTodos).filter(x -> x.body.contains(contains)).toArray(Todo[]::new);
    }
    //sort by owner
    public Todo[] sortByOwner(Todo[] filteredTodos){
      Todo[] result = new Todo[filteredTodos.length];
      result= filteredTodos.clone();

     Arrays.sort(result, (Todo a, Todo b)-> a.owner.compareTo(b.owner));
      return result;
    }

    public Todo[] sortByCategory(Todo[] filteredTodos){
        Todo[] result = new Todo[filteredTodos.length];
        result= filteredTodos.clone();

        Arrays.sort(result, (Todo a, Todo b)-> a.category.compareTo(b.category));
        return result;
    }

    public Todo[] sortByBody(Todo[] filteredTodos){
        Todo[] result = new Todo[filteredTodos.length];
        result= filteredTodos.clone();

        Arrays.sort(result, (Todo a, Todo b)-> a.body.compareTo(b.body));
        return result;
    }
    public Todo[] sortByStatus(Todo[] filteredTodos){
        Todo[] result = new Todo[filteredTodos.length];
        result= filteredTodos.clone();

        Arrays.sort(result, (Todo a, Todo b)-> String.valueOf(a.status).compareTo(String.valueOf(b.status)));
        return result;
    }


    // Get a single todo
    public Todo getTodo(String id) {
        return Arrays.stream(todos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
    }
}
