package br.com.processadora.socketserver.model;

import br.com.processadora.socketserver.enums.ActionEnum;
import br.com.processadora.socketserver.enums.TransactionCodeEnum;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "transactions")
public class Transaction {

    @JsonEnumDefaultValue
    private ActionEnum action;
    private String amount;
    private String cardnumber;
    @JsonEnumDefaultValue
    private TransactionCodeEnum code;
    private Integer authorizationCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime date;

    public Transaction() {
    }

    public Transaction(ActionEnum action, String amount, String cardnumber, TransactionCodeEnum code, LocalDateTime date) {
        this.action = action;
        this.amount = amount;
        this.cardnumber = cardnumber;
        this.code = code;
        this.date = date;
    }

    public ActionEnum getAction() {
        return action;
    }

    public void setAction(ActionEnum action) {
        this.action = action;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public TransactionCodeEnum getCode() {
        return code;
    }

    public void setCode(TransactionCodeEnum code) {
        this.code = code;
    }

    public Integer getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(Integer authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
