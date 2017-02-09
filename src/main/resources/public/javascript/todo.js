/**
 * Created by chen4709 on 2/5/17.
 */

window.onload = function() {
    console.log("The page is loaded now!");

    var element = document.getElementById('getAll');
    element.addEventListener("click", getAllTodos, true);

    var element = document.getElementById('Submit');
    element.addEventListener("click", filtering, true);


}



function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown menu if the user clicks outside of it
window.onclick = function(event) {

    var element = document.getElementById('SortByOwner');
    element.addEventListener("click", sortByOwner, true);
    var element = document.getElementById('SortByStatus');
    element.addEventListener("click", sortByStatus, true);
    var element = document.getElementById('SortByBody');
    element.addEventListener("click", sortByBody, true);
    var element = document.getElementById('SortByCategory');
    element.addEventListener("click", sortByCategory, true);




    // dropdown menu features
    if (!event.target.matches('.dropbtn')) {

        var dropdowns = document.getElementsByClassName("dropdown-content");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}


var filtering = function(){
    var result;
    var tempLink;
    var limit = document.getElementById('Limit').value;
    var status = document.getElementById('Status').value;
    var owner = document.getElementById('Owner').value;
    var category = document.getElementById('Category').value;
    var body = document.getElementById('Body').value;


    if(status != "" ){
        if(result == null){
            tempLink = "api/todos?status=";
            result = tempLink + status;
        }else{
            result += "&status=" + status;
        }
    }

    if(body != "" ){
        if(result == null){
            tempLink = "api/todos?body=";
            result = tempLink + body;
        }else{
            result += "&body=" + body;
        }
    }

    if(category != "" ){
        if(result == null){
            tempLink = "api/todos?category=";
            result = tempLink + category;
        }else{
            result += "&category=" + category;
        }
    }

    if(owner != "" ){
        if(result == null){
            tempLink = "api/todos?owner=";
            result = tempLink + owner;
        }else{
            result += "&owner=" + owner;
        }
    }

    // is not going to limit if a limit number is greater than available list of todos (because they are already filtered)
    if(limit != ""){
        if(result == null){
            tempLink = "/api/todos?limit=";
            result = tempLink + limit;
        }else{
            result += "&limit=" + limit;
        }
   }

   if(limit == ""&& status=="" && body=="" && category=="" && owner==""){
        result = "/api/todos";
   }

    var HttpThingy = new HttpClient();
    HttpThingy.get(result, function(returned_json){
        document.getElementById('jsonDump').innerHTML = returned_json;
    });

}






var getAllTodos = function() {
    var link = "/api/todos";
    var HttpThingy = new HttpClient();
    HttpThingy.get(link, function(returned_json){
        document.getElementById('jsonDump').innerHTML = returned_json;
    });
    return link;
}



var sortByOwner = function() {
    var link = "/api/todos?orderBy=owner";
    var HttpThingy = new HttpClient();
    HttpThingy.get(link, function(returned_json){
        document.getElementById('jsonDump').innerHTML = returned_json;
    });
    return link;
}

var sortByStatus = function() {
    var link = "/api/todos?orderBy=status";
    var HttpThingy = new HttpClient();
    HttpThingy.get(link, function(returned_json){
        document.getElementById('jsonDump').innerHTML = returned_json;
    });
    return link;
}

var sortByCategory = function() {
    var link = "/api/todos?orderBy=category";
    var HttpThingy = new HttpClient();
    HttpThingy.get(link, function(returned_json){
        document.getElementById('jsonDump').innerHTML = returned_json;
    });
    return link;
}

var sortByBody = function() {
    var HttpThingy = new HttpClient();
    HttpThingy.get("/api/todos?orderBy=body", function(returned_json){
        document.getElementById('jsonDump').innerHTML = returned_json;
    });
}

function HttpClient() {
    // We'll take a URL string, and a callback function.
    this.get = function(aUrl, aCallback){
        var anHttpRequest = new XMLHttpRequest();

        // Set a callback to be called when the ready state of our request changes.
        anHttpRequest.onreadystatechange = function(){

            /**
             * Only call our 'aCallback' function if the ready state is 'DONE' and
             * the request status is 200 ('OK')
             *
             * See https://httpstatuses.com/ for HTTP status codes
             * See https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/readyState
             *  for XMLHttpRequest ready state documentation.
             *
             */
            if (anHttpRequest.readyState == 4 && anHttpRequest.status == 200)
                aCallback(anHttpRequest.responseText);
        }

        anHttpRequest.open("GET", aUrl, true);
        anHttpRequest.send(null);
    }
}
