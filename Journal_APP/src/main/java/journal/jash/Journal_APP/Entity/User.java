package journal.jash.Journal_APP.Entity;

import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;



@Document(collection="users")

public class User {
  
    @Id
    private ObjectId id;
    @Indexed(unique=true) //indexing wont happen automatically by this line ,in application.properties you need to enable it.
    @NonNull
    private String username;
    @NonNull
    private String password;
    @DBRef
    private List<JournalEntries> journalEntries =new ArrayList<>() ;
    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<JournalEntries> getJournalEntries() {
        return journalEntries;
    }
    public void setJournalEntries(List<JournalEntries> journalEntries) {
        this.journalEntries = journalEntries;
    }
}
