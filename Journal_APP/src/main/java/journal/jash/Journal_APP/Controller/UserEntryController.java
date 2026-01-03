package journal.jash.Journal_APP.Controller;

import java.time.LocalDateTime;
import java.util.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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






@PutMapping
public ResponseEntity<?> updateUser(@RequestBody User user){
   
    Authentication authentication=  SecurityContextHolder.getContext().getAuthentication();
    String username =authentication.getName();
    User userinDB=UserService.userfindbyusername(username);

    if(userinDB!=null){
       if(user.getUsername()!=null && !user.getUsername().trim().isEmpty()) userinDB.setUsername(user.getUsername());
      if(user.getPassword()!=null && !user.getPassword().trim().isEmpty()) userinDB.setPassword(user.getPassword());
        UserService.saveEntry(userinDB);
        return ResponseEntity.ok().build();
    }else{
        return ResponseEntity.notFound().build();
    }

}

@DeleteMapping
public void deleteUser(){
    
    Authentication authentication=  SecurityContextHolder.getContext().getAuthentication();
    String username =authentication.getName();
    User userinDB=UserService.userfindbyusername(username);     
    if(userinDB!=null){
        UserService.deletebyid(userinDB.getId());
    }

}
}
