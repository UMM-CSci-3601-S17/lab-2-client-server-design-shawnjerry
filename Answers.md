What is the purpose of .gitignore? 
    To ignore files that we don't want to commit.

Explain what a route is. 
So the route is a function (get) which allows us to provide a specific result for a specific request.

What is the purpose of umm3601.Server class? 
So this class should run a server. 

What is the purpose of the umm3601.user.UserController class?
This class gives an access to data  from "user".

Explain what happens when a user accesses each of the following URLs: 
    
  a.The page users 
  Gets us to the page with the button "get all users" and shows a message "json will go here". By clicking the button it shows the list of users below.
  
  
  b.The page api/users 
  Gets us directly to the list of users. So we dont need to click any button.
  
  c.The page api/users?age=25 
  GEts us to the list of users who are age of 25.
  
  d.The page api/users/588935f5de613130e931ffd5 
  gets us to a specific user with id "588935f5de613130e931ffd5 "

What happens when the user accesses the page "kittens"? Modify the server code so accessing the page "kittens" results in the text "Meow". Describe what you did and how it worked. 
    It shows "Not found 404". We edited the server.java by adding a function call get(), so now when we type /kittens we get a response as "Meow".

What are the contents of the public folder? What is the purpose of each of the HTML files there? 
    It consists of HTML, CSS and JavaScript files that generates the webpages.
    The "index.html" generates the main page of the server. The about.html is another page which is linked by the button "About". The users.html contains the structure for "users" page.

Describe what happens when you filter users by age in the client? What is read from the web page, and what request is sent to the server? What is received, and how/where is it displayed? 
    It shows all the users of that age. In order to sort out the users we type "/api/users?age=25" (that is what is read from web page). So the array of users and a specified int (age) - that the request. And as a response from the server we get users whose age is equal to this int(age). And is displayed as a line of users.   

Where is the client-side JavaScript defined? Name the file(s) in which it is used. 
In src/main/resources/public/javascript. The file name is users.js
