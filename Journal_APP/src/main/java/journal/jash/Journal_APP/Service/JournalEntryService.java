package journal.jash.Journal_APP.Service;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import journal.jash.Journal_APP.Controller.JournalApp;
import journal.jash.Journal_APP.repository.JournalEntryRepository;

@Component
public class JournalEntryService {
    
@Autowired
private JournalEntryRepository JournalEntryRepository;

public void saveEntry(JournalApp JournalApp){
    JournalEntryRepository.save(JournalApp);
}


public List<JournalApp> getAllEntries(){
  return JournalEntryRepository.findAll();
}
}
