package br.com.processadora.socketserver.helper;

import br.com.processadora.socketserver.model.Transaction;

import java.io.IOException;

public interface TransactionHelper {

    Integer getAuthorizationCode();

    String getJson(Transaction transaction);

    Transaction parse(String json) throws IOException;

}
