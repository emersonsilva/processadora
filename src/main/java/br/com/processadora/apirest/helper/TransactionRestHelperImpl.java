package br.com.processadora.apirest.helper;

import br.com.processadora.apirest.model.TransactionRest;
import br.com.processadora.socketserver.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionRestHelperImpl implements TransactionRestHelper {

    @Override
    public List<TransactionRest> parse(List<Transaction> transactions) {
        List<TransactionRest> transactionsRest = new ArrayList<>();
        for (Transaction transaction : transactions) {
            TransactionRest transaction1 = new TransactionRest();
            transaction1.setAmount(transaction.getAmount());
            transaction1.setDate(transaction.getDate());
            transactionsRest.add(transaction1);
        }
        return transactionsRest;
    }
}
