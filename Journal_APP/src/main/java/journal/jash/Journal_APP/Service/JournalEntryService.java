package journal.jash.Journal_APP.Service;

import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import journal.jash.Journal_APP.Entity.JournalEntries;
import journal.jash.Journal_APP.Entity.User;
import journal.jash.Journal_APP.repository.JournalEntryRepository;

@Component
public class JournalEntryService {
    
@Autowired
private JournalEntryRepository JournalEntryRepository;

@Autowired
private UserService userService;

@Transactional //creates a transactional method context to run all methods at once.
public void saveEntry(JournalEntries JournalApp, String username){
  User user =userService.userfindbyusername(username); 
 JournalEntries saved= JournalEntryRepository.save(JournalApp);
 user.getJournalEntries().add(saved);
 userService.saveEntry(user);
}

public void saveEntry(JournalEntries JournalApp){
 JournalEntryRepository.save(JournalApp);
 
 
}


public List<JournalEntries> getAllEntries(){
  return JournalEntryRepository.findAll();
}

public Optional<JournalEntries> getEntryByID(ObjectId id){
    return JournalEntryRepository.findById(id);
}

public void deletebyid(ObjectId id, String username){
  User user =userService.userfindbyusername(username); 
  user.getJournalEntries().removeIf(x-> x.getId().equals(id));
  userService.saveEntry(user);
  JournalEntryRepository.deleteById(id);


}


}
