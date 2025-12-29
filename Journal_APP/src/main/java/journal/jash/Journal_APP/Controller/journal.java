package journal.jash.Journal_APP.Controller;

import java.util.HashMap;
import java.util.LinkedList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journal")
public class journal {
    
HashMap<Integer,JournalApp> journals = new HashMap<>();


@PostMapping("/createRecords")
public String createRecord(@RequestBody JournalApp myentry){
    if(myentry.getId()>=0){
    journals.put(myentry.getId(), myentry);
    return "Record added";}
    else{
        return "ID should be greater than 0";
    }
}

@GetMapping("/getAllRecords")
public LinkedList<JournalApp> getallrecords(){
    LinkedList<JournalApp> ll = new LinkedList<>();
    for(JournalApp temp : journals.values()){
        ll.add(temp);
    }
    return ll;
}

@GetMapping("/id/{id}")
public JournalApp getrecordsbyID(@PathVariable Integer id){
    return journals.get(id);

}


@DeleteMapping("/id/{id}")
public String deletebyid(@PathVariable Integer id){
    if(journals.containsKey(id)){
        journals.remove(id);
    return "Record Deleted";
    }else{
        return "Record not found";
    }
}




}

