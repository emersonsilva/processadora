package br.com.processadora.apirest.repository;

import br.com.processadora.socketserver.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> findTop10ByCardnumberOrderByDateDesc(String cardnumber);

}
