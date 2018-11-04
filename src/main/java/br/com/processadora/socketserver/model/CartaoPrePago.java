package br.com.processadora.socketserver.model;

import br.com.processadora.apirest.model.TransactionRest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document(collection = "cartoesPrePagos")
public class CartaoPrePago implements Serializable {

    @Id
    private String id;
    private String cardnumber;
    private String availableAmount;
    private List<TransactionRest> transactions;

    public CartaoPrePago() {
    }

    public CartaoPrePago(String cardnumber, String availableAmount) {
        this.cardnumber = cardnumber;
        this.availableAmount = availableAmount;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public void setAvailableAmount(String availableAmount) {
        this.availableAmount = availableAmount;
    }

    public String getAvailableAmount() {
        return availableAmount;
    }

    public List<TransactionRest> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionRest> transactions) {
        this.transactions = transactions;
    }
}