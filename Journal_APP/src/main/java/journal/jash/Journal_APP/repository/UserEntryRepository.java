package journal.jash.Journal_APP.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import journal.jash.Journal_APP.Entity.User;

@Component
public interface UserEntryRepository extends MongoRepository<User,ObjectId>  {

public User findByUsername(String username);
   
}
