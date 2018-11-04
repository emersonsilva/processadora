package br.com.processadora.socketserver.validation;

import br.com.processadora.socketserver.model.Transaction;

public interface TransactionValidation {

    Transaction createTransaction(String json);

}
