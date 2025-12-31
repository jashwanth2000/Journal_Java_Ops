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

import journal.jash.Journal_APP.Entity.JournalEntries;
import journal.jash.Journal_APP.Entity.User;
import journal.jash.Journal_APP.Service.JournalEntryService;
import journal.jash.Journal_APP.Service.UserService;

@RestController
@RequestMapping("/journal")
public class journalV2 {
    
@Autowired
private JournalEntryService journalEntryService;

@Autowired
private UserService userService;

@PostMapping("/createRecords/{username}")
public ResponseEntity<String> createRecord(@RequestBody JournalEntries myentry, @PathVariable String username) {
    try {
        
        myentry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myentry,username);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Success: record added");
    } catch (Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed: record not added");
    }
}

@GetMapping("/getAllEntries/{username}")
public ResponseEntity<List<JournalEntries>> getAllRecordsOfUser(@PathVariable String username){
    User user =userService.userfindbyusername(username);
    System.out.println("found"+username);
  try{ List<JournalEntries> entries =user.getJournalEntries();
        if(entries.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(entries);
  }catch(Exception e){
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

}


@GetMapping("/id/{id}")
public ResponseEntity<JournalEntries> getrecordsbyID(@PathVariable ObjectId id){
  try{
       Optional<JournalEntries> journalApp=journalEntryService.getEntryByID(id);
       if(journalApp.isPresent()){
        return ResponseEntity.ok(journalApp.get());
       }
       else{
        return  ResponseEntity.notFound().build();
        //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }
     

}


@DeleteMapping("/id/{username}/{id}")
public ResponseEntity<Void> deletebyid(@PathVariable ObjectId id,@PathVariable String username){
    try{
        journalEntryService.deletebyid(id,username);
        return ResponseEntity.ok().build();
    }catch(Exception e){                    
       return ResponseEntity.status(HttpStatus.NOT_FOUND).build();    
    }
}

@PutMapping("/update/{id}")
public ResponseEntity<String> updateRecord(
        @RequestBody JournalEntries updatedEntry, @PathVariable ObjectId id) {
        

                JournalEntries oldEntry = journalEntryService.getEntryByID(id).orElse(null);

                if (oldEntry == null) {
                return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Record not found");
                }

    if (updatedEntry.getNotes() != null && !updatedEntry.getNotes().trim().isEmpty()) {
        oldEntry.setNotes(updatedEntry.getNotes());
    }

    if (updatedEntry.getRef() != null && !updatedEntry.getRef().trim().isEmpty()) {
        oldEntry.setRef(updatedEntry.getRef());
    }

    oldEntry.setDate(LocalDateTime.now());

    journalEntryService.saveEntry(oldEntry);

    return ResponseEntity
            .ok("Success: record updated");


}


}
