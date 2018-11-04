package br.com.processadora.socketserver.dao;

import br.com.processadora.socketserver.model.Transaction;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

public class TransactionDaoImpl extends Dao implements TransactionDao {

    private DBCollection table;
    private final String KEY_CARDNUMBER = "cardnumber";
    private final String COLLECTION_TRANSACTIONS = "transactions";
    private final String KEY_ACTION = "action";
    private final String KEY_AMOUNT = "amount";
    private final String KEY_CODE = "code";
    private final String KEY_AUTHORIZATION_CODE = "authorizationCode";
    private final String KEY_TRANSACTION_DATE = "date";


    public TransactionDaoImpl() {
        table = getDb().getCollection(COLLECTION_TRANSACTIONS);
    }

    @Override
    public void salvar(Transaction transaction) {
        BasicDBObject document = new BasicDBObject();
        document.put(KEY_ACTION, transaction.getAction().name());
        document.put(KEY_AMOUNT, transaction.getAmount());
        document.put(KEY_CARDNUMBER, transaction.getCardnumber());
        document.put(KEY_CODE, transaction.getCode().name());
        document.put(KEY_AUTHORIZATION_CODE, transaction.getAuthorizationCode());
        document.put(KEY_TRANSACTION_DATE, transaction.getDate());
        table.insert(document);
    }
}
