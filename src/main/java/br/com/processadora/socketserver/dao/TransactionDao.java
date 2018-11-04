package br.com.processadora.socketserver.dao;

import br.com.processadora.socketserver.model.Transaction;

public interface TransactionDao {

    void salvar(Transaction transaction);

}
