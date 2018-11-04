package br.com.processadora.apirest.service;

import br.com.processadora.apirest.helper.TransactionRestHelper;
import br.com.processadora.apirest.repository.CartaoPrePagoRepository;
import br.com.processadora.apirest.repository.TransactionRepository;
import br.com.processadora.socketserver.model.CartaoPrePago;
import br.com.processadora.socketserver.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaoPrePagoServiceImpl implements CartaoPrePagoService {

    @Autowired
    private CartaoPrePagoRepository cartaoPrePagoRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionRestHelper transactionRestHelper;

    @Override
    public CartaoPrePago findByCardnumber(String cardnumber) {
        CartaoPrePago cartaoPrePago = cartaoPrePagoRepository.findByCardnumber(cardnumber);
        List<Transaction> transactions = transactionRepository.findTop10ByCardnumberOrderByDateDesc(cardnumber);
        cartaoPrePago.setTransactions(transactionRestHelper.parse(transactions));
        return cartaoPrePago;
    }
}
