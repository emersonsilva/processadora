package br.com.processadora.socketserver.enums;

public enum KeyPropertiesEnum {

    CONFIGURACAO_IP_MONGO("cfg.mongo.ip"),
    CONFIGURACAO_IP_SERVIDOR_SOCKET("cfg.ip"),
    CONFIGURACAO_PORTA_MONGO("cfg.mongo.porta"),
    CONFIGURACAO_DATABASE_MONGO("cfg.mongo.db"),
    CONFIGURACAO_PORTA_SERVIDOR_SOCKET("cfg.porta"),
    MSM_AGUARDE_CONEXAO("msm.servidor.aguarde.conexao"),
    MSM_CLIENTE_CONECTADO("msm.servidor.cliente.conectado"),
    MSM_CONFIGURACAO_AUTOMATICA("msm.servidor.configuracao.automatica");

    private String key;

    KeyPropertiesEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
