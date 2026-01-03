package journal.jash.Journal_APP.Service;

import java.util.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import journal.jash.Journal_APP.Entity.User;
import journal.jash.Journal_APP.repository.UserEntryRepository;

@Component
public class UserService {
    
@Autowired
private UserEntryRepository UserEntryRepository;

private static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

public void saveEntry(User user){
  user.setPassword((user.getPassword()));
    UserEntryRepository.save(user);
}


public List<User> getAllUsers(){
  return UserEntryRepository.findAll();
}

public Optional<User> getEntryByID(ObjectId id){
    return UserEntryRepository.findById(id);
}

public void deletebyid(ObjectId id){
UserEntryRepository.deleteById(id);

}

public User userfindbyusername(String username){
  return UserEntryRepository.findByUsername(username);
}


}
