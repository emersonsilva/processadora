package br.com.processadora.socketserver.dao;

import br.com.processadora.socketserver.model.CartaoPrePago;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import java.math.BigDecimal;

public class CartaoPrePagoDaoImpl extends Dao implements CartaoPrePagoDao {

    private DBCollection table;
    private CartaoPrePago cartaoPrePago;
    private final String KEY_CARDNUMBER = "cardnumber";
    private final String COLLECTION_CARTOES = "cartoesPrePagos";
    private final String KEY_AVAILABLE_AMOUNT = "availableAmount";

    public CartaoPrePagoDaoImpl() {
        table = getDb().getCollection(COLLECTION_CARTOES);
    }

    @Override
    public BigDecimal getSaldo(String cardnumber) {
        BasicDBObject document = new BasicDBObject();
        cartaoPrePago = new CartaoPrePago();
        document.put(KEY_CARDNUMBER, cardnumber);
        DBCursor cursor = table.find(document);
        if (cursor.hasNext()) {
            cartaoPrePago = new CartaoPrePago();
            cartaoPrePago.setAvailableAmount((String) cursor.next().get(KEY_AVAILABLE_AMOUNT));
        }
        BigDecimal availableAmountBigDecimal = new BigDecimal(cartaoPrePago.getAvailableAmount().replaceAll(",", "."));
        return availableAmountBigDecimal;
    }

    @Override
    public void atualizarSaldo(String amount, String cardnumber) {
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append(KEY_AVAILABLE_AMOUNT, amount.replace(".", ",")));
        BasicDBObject searchQuery = new BasicDBObject().append(KEY_CARDNUMBER, cardnumber);
        table.update(searchQuery, newDocument);
    }

    @Override
    public boolean hasConta(String cardnumber) {
        BasicDBObject document = new BasicDBObject();
        document.put(KEY_CARDNUMBER, cardnumber);
        DBCursor cursor = table.find(document);
        return cursor.hasNext();
    }

    @Override
    public void salvar(CartaoPrePago cartaoPrePago) {
        BasicDBObject document = new BasicDBObject();
        document.put(KEY_CARDNUMBER, cartaoPrePago.getCardnumber());
        document.put(KEY_AVAILABLE_AMOUNT, cartaoPrePago.getAvailableAmount());
        table.insert(document);
    }

    @Override
    public Long getQuantidade() {
        return table.count();
    }
}
