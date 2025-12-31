package journal.jash.Journal_APP.Controller;

import java.time.LocalDateTime;
import java.util.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import journal.jash.Journal_APP.Entity.User;

import journal.jash.Journal_APP.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserEntryController {
 
@Autowired
private UserService UserService;

@PostMapping
public void createUser(@RequestBody User user){
    UserService.saveEntry(user);
   
}

@GetMapping
public List<User> getAllUser(){
   List<User> user=UserService.getAllUsers();
   return user;
}

@PutMapping("{username}")
public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String username){
    User userinDB=UserService.userfindbyusername(username);
    if(userinDB!=null){
        userinDB.setUsername(user.getUsername());
        userinDB.setPassword(user.getPassword());
        UserService.saveEntry(userinDB);
        return ResponseEntity.ok().build();
    }else{
        return ResponseEntity.notFound().build();
    }

}


}
