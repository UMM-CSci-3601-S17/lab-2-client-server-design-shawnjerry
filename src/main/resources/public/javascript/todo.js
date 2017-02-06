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
    var link;
    var limit = document.getElementById('Limit').value;




    if(limit != ""){
       var link="/api/todos?limit=";
   }


    var HttpThingy = new HttpClient();
    HttpThingy.get(url+limit, function(returned_json){
        document.getElementById('jsonDump').innerHTML = returned_json;
    });
}

var getAllTodos = function() {
    var HttpThingy = new HttpClient();
    HttpThingy.get("/api/todos", function(returned_json){
        document.getElementById('jsonDump').innerHTML = returned_json;
    });
}



var sortByOwner = function() {
    var HttpThingy = new HttpClient();
    HttpThingy.get("/api/todos?orderBy=owner", function(returned_json){
        document.getElementById('jsonDump').innerHTML = returned_json;
    });
}

var sortByStatus = function() {
    var HttpThingy = new HttpClient();
    HttpThingy.get("/api/todos?orderBy=status", function(returned_json){
        document.getElementById('jsonDump').innerHTML = returned_json;
    });
}

var sortByCategory = function() {
    var HttpThingy = new HttpClient();
    HttpThingy.get("/api/todos?orderBy=category", function(returned_json){
        document.getElementById('jsonDump').innerHTML = returned_json;
    });
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
