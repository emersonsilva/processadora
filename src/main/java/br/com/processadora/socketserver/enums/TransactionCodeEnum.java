package br.com.processadora.socketserver.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public enum TransactionCodeEnum {

    @JsonProperty("00")
    APROVADA("00"),

    @JsonProperty("14")
    CONTA_INVALIDA("14"),

    @JsonProperty("51")
    SALDO_INSUFICIENTE("51"),

    @JsonProperty("96")
    ERRO_DE_PROCESSAMENTO("96");

    private String number;

    private static Map<String, TransactionCodeEnum> mapByNumber = new HashMap<>();

    static {
        for (TransactionCodeEnum transactionCodeEnum : TransactionCodeEnum.values()) {
            mapByNumber.put(transactionCodeEnum.number, transactionCodeEnum);
        }
    }

    TransactionCodeEnum(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public static TransactionCodeEnum getByNumber(String number) {
        return mapByNumber.get(number);
    }
}
