package parcial.parcial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import parcial.parcial.model.Product;

@Repository
public interface RepoProduct extends MongoRepository<Product, String> {}
