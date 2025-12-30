package journal.jash.Journal_APP.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

// @GetMapping("/id/{id}")
// public JournalApp getrecordsbyID(@PathVariable Integer id){
//     return journals.get(id);

// }


// @DeleteMapping("/id/{id}")
// public String deletebyid(@PathVariable Integer id){
//     if(journals.containsKey(id)){
//         journals.remove(id);
//     return "Record Deleted";
//     }else{
//         return "Record not found";
//     }
// }





}
