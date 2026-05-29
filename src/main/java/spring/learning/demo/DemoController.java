package spring.learning.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todo")
public class DemoController{
    // /api/todo
    @GetMapping
    String Todo(){
        return "Todo";
    }

    //path parameter - /api/todo/1
    @GetMapping("/{id}")
    String getTodoById(@PathVariable long id){
        return "Id : " + id;
    }

    //Request Parameter - api/todo/reqparm?id=10 (This is like queryparam in fastAPI)
    @GetMapping("/reqparm")
    String getTodoByRequest(@RequestParam String id){
        return "Req Param : " + id;
    }

    //Request Parameter with rename, we can use it as id internally but while giving in api we need to use todoId  - api/todo/reqparm1?todoId=10 
     @GetMapping("/reqparm1")
    String getTodoByRequest1(@RequestParam("todoId") long id){
        return "Req Param : " + id;
    }

    // api/todo/reqParm2?userId=admin&password=admin123  - if we use req parm for pass we have to give it in url. to hide it we use reqBody, we use reqbody to post data
    @GetMapping("/reqParm2")
    String createUser(@RequestParam String userId, @RequestParam String password){
        return "Username : " + userId + " Password : " + password;
    }

    // requestBody  - to post the data
    // http://localhost:8080/api/todo/reqBody  - use thunder client to post the data  
    @PostMapping("/reqBody")
    String post(@RequestBody String body){
        return body;
  }

  //http://localhost:8080/api/todo/10 - in browser if wr use this it will call the get methos as we are having the same end point in get. But in thunder client put method we can use this to update
        @PutMapping("/{id}")
        String Updateapi(@PathVariable long id){
            return "Update Id :" +id;
            
    }
    //http://localhost:8080/api/todo/delete/10
    @DeleteMapping("/delete/{id}")
        String deleteApi(@PathVariable long id){
            return "Delete Id :" +id;
            
    }
}