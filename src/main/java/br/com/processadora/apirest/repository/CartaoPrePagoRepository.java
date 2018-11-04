package br.com.processadora.apirest.repository;

import br.com.processadora.socketserver.model.CartaoPrePago;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartaoPrePagoRepository extends MongoRepository<CartaoPrePago, String> {

    CartaoPrePago findByCardnumber(String cardnumber);

}
