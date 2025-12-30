package journal.jash.Journal_APP.Controller;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;



@Document
public class JournalApp {
  
 @Id   
private ObjectId id;
private String notes;
private String ref;
private Date date;
//private LocalDateTime ukTime = LocalDateTime.now(ZoneId.of("Europe/London"));  other countries if required;

public ObjectId getId() {
    return id;
}
 public void setId(ObjectId id) {
    this.id = id;
 }
public String getNotes() {
    return notes;
}
public void setNotes(String notes) {
    this.notes = notes;
}
public String getRef() {
    return ref;
}
public void setRef(String ref) {
    this.ref = ref;
}
public Date getDate() {
    return date;
}
public void setDate(Date date) {
    this.date = date;
} 

}
