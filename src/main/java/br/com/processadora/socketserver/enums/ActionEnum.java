
package br.com.processadora.socketserver.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public enum ActionEnum {

    @JsonProperty("withdraw")
    WITHDRAW("withdraw");

    private String descricao;

    private static Map<String, br.com.processadora.socketserver.enums.ActionEnum> mapByDescricao = new HashMap<>();

    static {
        for (br.com.processadora.socketserver.enums.ActionEnum actionEnum : br.com.processadora.socketserver.enums.ActionEnum.values()) {
            mapByDescricao.put(actionEnum.descricao, actionEnum);
        }
    }

    ActionEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static br.com.processadora.socketserver.enums.ActionEnum getByDescricao(String descricao) {
        return mapByDescricao.get(descricao);
    }
}
