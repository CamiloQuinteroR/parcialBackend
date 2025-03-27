package parcial.parcial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import parcial.parcial.model.Payment;

@Repository
public interface  RepoPayment extends MongoRepository<Payment, String>{}




