package br.com.processadora.socketserver.dao;

import br.com.processadora.socketserver.util.PropertiesUtil;
import com.mongodb.DB;
import com.mongodb.Mongo;

import static br.com.processadora.socketserver.enums.KeyPropertiesEnum.*;

public class Dao {

    private Mongo mongo;
    private DB db;

    public Dao() {
        this.mongo = new Mongo(PropertiesUtil.getProp(CONFIGURACAO_IP_MONGO.getKey()), Integer.parseInt(PropertiesUtil.getProp(CONFIGURACAO_PORTA_MONGO.getKey())));
        this.db = mongo.getDB(PropertiesUtil.getProp(CONFIGURACAO_DATABASE_MONGO.getKey()));
    }

    public DB getDb() {
        return db;
    }

}
