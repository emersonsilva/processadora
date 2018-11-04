package br.com.processadora.socketserver.controller;

import br.com.processadora.socketserver.dao.TransactionDao;
import br.com.processadora.socketserver.dao.TransactionDaoImpl;
import br.com.processadora.socketserver.enums.TransactionCodeEnum;
import br.com.processadora.socketserver.helper.CartaoPrePagoHelper;
import br.com.processadora.socketserver.helper.CartaoPrePagoHelperImpl;
import br.com.processadora.socketserver.helper.TransactionHelper;
import br.com.processadora.socketserver.helper.TransactionHelperImpl;
import br.com.processadora.socketserver.model.Transaction;
import br.com.processadora.socketserver.util.PropertiesUtil;
import br.com.processadora.socketserver.validation.TransactionValidation;
import br.com.processadora.socketserver.validation.TransactionValidationImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;

import static br.com.processadora.socketserver.enums.KeyPropertiesEnum.*;

public class ServidorSocketController extends Thread {

    private static Logger LOGGER = Logger.getLogger(ServidorSocketController.class.getName());

    private Socket socket;
    private InputStream in;
    private ServerSocket server;
    private ObjectInputStream ois;
    private TransactionDao transactionDao = new TransactionDaoImpl();
    private TransactionHelper transactionHelper = new TransactionHelperImpl();
    private TransactionValidation validation = new TransactionValidationImpl();
    private CartaoPrePagoHelper cartaoPrePagoHelper = new CartaoPrePagoHelperImpl();

    public ServidorSocketController() {
        cartaoPrePagoHelper.criarCartoesPrePagos();
    }

    public ServidorSocketController(Socket socket) {
        this.socket = socket;
        try {
            in = socket.getInputStream();
            ois = new ObjectInputStream(in);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }

    public void inicializar() {
        try {
            ConfigurarServidor();
            while (true) {
                LOGGER.info(getValue(MSM_AGUARDE_CONEXAO.getKey()));
                Socket con = server.accept();
                LOGGER.info(getValue(MSM_CLIENTE_CONECTADO.getKey()));
                Thread t = new ServidorSocketController(con);
                t.start();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }

    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            String json = (String) ois.readObject();
            Transaction transaction = salvarTransaction(json);
            oos.writeObject(transactionHelper.getJson(transaction));
            oos.flush();
            LOGGER.info(json);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }

    private Transaction salvarTransaction(String json) {
        Transaction transaction = validation.createTransaction(json);
        if (transaction.getCode() == TransactionCodeEnum.APROVADA) {
            transaction.setDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
            transactionDao.salvar(transaction);
        }
        return transaction;
    }

    private void ConfigurarServidor() throws IOException {
        server = new ServerSocket(Integer.parseInt(getValue(CONFIGURACAO_PORTA_SERVIDOR_SOCKET.getKey())));
        LOGGER.info(getValue(MSM_CONFIGURACAO_AUTOMATICA.getKey()));
    }

    private static String getValue(String key) {
        return PropertiesUtil.getProp(key);
    }
}

