package parcial.parcial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import parcial.parcial.model.User;

@Repository
public interface RepoUser extends MongoRepository<User, String>{}
