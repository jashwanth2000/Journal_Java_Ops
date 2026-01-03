package journal.jash.Journal_APP.Entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import java.time.LocalDateTime;



@Document(collection="journal_entries")

public class JournalEntries {
  
 @Id   
private ObjectId id;

@NonNull
private String ref;
private String notes;

private LocalDateTime date;
//private LocalDateTime ukTime = LocalDateTime.now(ZoneId.of("Europe/London"));  other countries if required;

public ObjectId getId() {
    return id;
}

public void setId(ObjectId id) {
    this.id = id;
}

public String getRef() {
    return ref;
}

public void setRef(String ref) {
    this.ref = ref;
}

public String getNotes() {
    return notes;
}

public void setNotes(String notes) {
    this.notes = notes;
}

public LocalDateTime getDate() {
    return date;
}

public void setDate(LocalDateTime date) {
    this.date = date;
}

}
