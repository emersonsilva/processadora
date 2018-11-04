package br.com.processadora.socketclient;

import br.com.processadora.socketserver.enums.KeyPropertiesEnum;
import br.com.processadora.socketserver.enums.TransactionCodeEnum;
import br.com.processadora.socketserver.util.PropertiesUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class SocketClientComTransacaoErroProcessamento {

    private static Logger LOGGER = Logger.getLogger(SocketClientComTransacaoErroProcessamento.class.getName());

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Socket socket = new Socket(PropertiesUtil.getProp(KeyPropertiesEnum.CONFIGURACAO_IP_SERVIDOR_SOCKET.getKey()),
                Integer.parseInt(PropertiesUtil.getProp(KeyPropertiesEnum.CONFIGURACAO_PORTA_SERVIDOR_SOCKET.getKey())));

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        String card = "0000000000000001";
        String json = "{ \"ATRU\": \"withdraw\", \"cardnumber\":\"" + card + "\", \"amount\": \"0,01\" }";

        oos.writeObject(json);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        String message = (String) ois.readObject();

        if (message.contains(TransactionCodeEnum.ERRO_DE_PROCESSAMENTO.getNumber())) {
            LOGGER.info(message + " OK --> Código: " + TransactionCodeEnum.ERRO_DE_PROCESSAMENTO.getNumber() + " -> " + TransactionCodeEnum.ERRO_DE_PROCESSAMENTO.name());
        } else {
            LOGGER.info(message + "NÃO OK " + TransactionCodeEnum.ERRO_DE_PROCESSAMENTO.name());
        }

        ois.close();
        oos.close();

    }

}