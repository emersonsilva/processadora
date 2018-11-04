package br.com.processadora.socketserver.helper;

import br.com.processadora.socketserver.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Random;

public class TransactionHelperImpl implements TransactionHelper {

    @Override
    public Transaction parse(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Transaction transaction = objectMapper.readValue(json, Transaction.class);
        return transaction;
    }

    @Override
    public Integer getAuthorizationCode() {
        Random gerador = new Random();
        Random gerador2 = new Random();
        Random gerador3 = new Random();
        String code = retornarCodePositivo(gerador.nextInt());
        String code2 = retornarCodePositivo(gerador2.nextInt());
        String code3 = retornarCodePositivo(gerador3.nextInt());
        String codeStr = (code + code2 + code3 + code + code2 + code3).substring(5, 11);
        Integer codeValue = Integer.parseInt(codeStr);
        return codeValue;
    }

    @Override
    public String getJson(Transaction transaction) {
        return "{" +
                "\"action\":\"" + transaction.getAction().getDescricao() + "\"" +
                ", \"code\":\"" + transaction.getCode().getNumber() + "\"" +
                ", \"authorization_code\":\"" + transaction.getAuthorizationCode() + "\"" +
                '}';
    }

    private static String retornarCodePositivo(Integer code) {
        return code < 0 ? String.valueOf(code * -1) : String.valueOf(code);
    }


}
