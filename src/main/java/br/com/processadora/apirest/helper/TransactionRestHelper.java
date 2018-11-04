package br.com.processadora.apirest.helper;

import br.com.processadora.apirest.model.TransactionRest;
import br.com.processadora.socketserver.model.Transaction;

import java.util.List;

public interface TransactionRestHelper {

    List<TransactionRest> parse(List<Transaction> transactions);

}
