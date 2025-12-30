package journal.jash.Journal_APP.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import journal.jash.Journal_APP.Controller.JournalApp;

@Component
public interface JournalEntryRepository extends MongoRepository<JournalApp,ObjectId>  {


    //MongoRepository<classname, primary key type>
}
