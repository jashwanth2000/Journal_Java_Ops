package journal.jash.Journal_APP.Controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import journal.jash.Journal_APP.Entity.User;
import journal.jash.Journal_APP.Service.UserService;


@RestController
@RequestMapping("/public")
public class PublicController {

@Autowired
private UserService UserService;

private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


@GetMapping("/healthcheck")
public String healthcheck(){
    return "Health is good";
}

@PostMapping("/create-user")
public void createUser(@RequestBody User user){
   
   user.setPassword(passwordEncoder.encode(user.getPassword()));
   user.setRoles(Arrays.asList("USER"));
    UserService.saveEntry(user);
   
}


}
