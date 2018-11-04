package br.com.processadora.socketclient;

import br.com.processadora.socketserver.enums.KeyPropertiesEnum;
import br.com.processadora.socketserver.util.PropertiesUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.Instant;
import java.util.Random;
import java.util.logging.Logger;

public class SocketClientMassivo {

    private static Logger LOGGER = Logger.getLogger(SocketClientMassivo.class.getName());

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Integer quantidadeTransacoes = 2;

        for (int i = 1; i <= quantidadeTransacoes; i++) {
            Socket socket = new Socket(PropertiesUtil.getProp(KeyPropertiesEnum.CONFIGURACAO_IP_SERVIDOR_SOCKET.getKey()),
                    Integer.parseInt(PropertiesUtil.getProp(KeyPropertiesEnum.CONFIGURACAO_PORTA_SERVIDOR_SOCKET.getKey())));

            oos = new ObjectOutputStream(socket.getOutputStream());

            LOGGER.info("\nEnvio de mensagem para Socket Server");

            Random gerador = new Random();
            String reais = Integer.toString(gerador.nextInt()).substring(1, 3);
            String centavos = Integer.toString(gerador.nextInt()).substring(1, 3);

            String card = String.format("%016d", i);
            String mensagemEnvio = "{ \"action\": \"withdraw\", \"cardnumber\":\"" + card + "\", \"amount\": \"" + reais + "," + centavos + "\" }";

            Instant envio = Instant.now();
            LOGGER.info("Mensagem de envio ao Servidor: " + mensagemEnvio + " às " + envio);
            oos.writeObject(mensagemEnvio);

            ois = new ObjectInputStream(socket.getInputStream());
            String messageRetorno = (String) ois.readObject();

            Instant retorno = Instant.now();
            LOGGER.info("Mensagem de retorno do Servidor: " + messageRetorno + " às " + retorno);
            LOGGER.info("Tempo gasto na transação: " + (retorno.toEpochMilli() - envio.toEpochMilli()) + " milissengundos");
            ois.close();
            oos.close();

        }
    }

}