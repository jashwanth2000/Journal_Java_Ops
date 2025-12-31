package journal.jash.Journal_APP.Entity;

import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;

@Document(collection="users")
@Data
public class User {
  
    @Id
    private ObjectId id;
    @Indexed(unique=true) //indexing wont happen automatically by this line in application.properties you need to enable
    @NonNull
    private String username;
    @NonNull
    private String password;
    @DBRef
    private List<JournalEntries> journalEntries =new ArrayList<>() ;
}
