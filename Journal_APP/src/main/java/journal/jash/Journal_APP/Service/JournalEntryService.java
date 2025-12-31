package journal.jash.Journal_APP.Service;

import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import journal.jash.Journal_APP.Entity.JournalEntries;
import journal.jash.Journal_APP.repository.JournalEntryRepository;

@Component
public class JournalEntryService {
    
@Autowired
private JournalEntryRepository JournalEntryRepository;

public void saveEntry(JournalEntries JournalApp){
    JournalEntryRepository.save(JournalApp);
}


public List<JournalEntries> getAllEntries(){
  return JournalEntryRepository.findAll();
}

public Optional<JournalEntries> getEntryByID(ObjectId id){
    return JournalEntryRepository.findById(id);
}

public void deletebyid(ObjectId id){
JournalEntryRepository.deleteById(id);

}


}
