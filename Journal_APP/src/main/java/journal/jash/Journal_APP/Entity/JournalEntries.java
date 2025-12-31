package journal.jash.Journal_APP.Entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;



@Document(collection="journal_entries")
@Data
public class JournalEntries {
  
 @Id   
private ObjectId id;

@NonNull
private String ref;
private String notes;

private LocalDateTime date;
//private LocalDateTime ukTime = LocalDateTime.now(ZoneId.of("Europe/London"));  other countries if required;

}
