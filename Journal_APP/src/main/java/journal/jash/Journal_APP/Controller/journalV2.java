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

import journal.jash.Journal_APP.Service.JournalEntryService;

@RestController
@RequestMapping("/journal")
public class journalV2 {
    
@Autowired
private JournalEntryService journalEntryService;

@PostMapping("/createRecords")
public ResponseEntity<String> createRecord(@RequestBody JournalApp myentry) {
    try {
        myentry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myentry);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Success: record added");
    } catch (Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed: record not added");
    }
}

@GetMapping("/getAllEntries")
public List<JournalApp> getallrecords(){
   return journalEntryService.getAllEntries();
}


@GetMapping("/id/{id}")
public JournalApp getrecordsbyID(@PathVariable ObjectId id){
  return journalEntryService.getEntryByID(id).orElse(null);

}


@DeleteMapping("/id/{id}")
public void deletebyid(@PathVariable ObjectId id){
    journalEntryService.deletebyid(id);

}

@PutMapping("/update/{id}")
public ResponseEntity<String> updateRecord(
        @RequestBody JournalApp updatedEntry,
    @PathVariable ObjectId id) {
        

                JournalApp oldEntry = journalEntryService.getEntryByID(id).orElse(null);

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
