package br.com.processadora.socketserver.validation;

import br.com.processadora.socketserver.dao.CartaoPrePagoDao;
import br.com.processadora.socketserver.dao.CartaoPrePagoDaoImpl;
import br.com.processadora.socketserver.enums.ActionEnum;
import br.com.processadora.socketserver.enums.TransactionCodeEnum;
import br.com.processadora.socketserver.helper.TransactionHelper;
import br.com.processadora.socketserver.helper.TransactionHelperImpl;
import br.com.processadora.socketserver.model.Transaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.regex.Pattern;

public class TransactionValidationImpl implements TransactionValidation {

    private TransactionHelper transactionHelper = new TransactionHelperImpl();
    private CartaoPrePagoDao cartaoPrePagoDao = new CartaoPrePagoDaoImpl();

    public Transaction createTransaction(String json) {
        Transaction transaction = new Transaction();
        try {
            transaction = transactionHelper.parse(json);
            transaction.setCode(getTransactionCode(transaction, json));
            transaction.setAction(ActionEnum.WITHDRAW);
            if (transaction.getCode() == TransactionCodeEnum.APROVADA) {
                transaction.setAuthorizationCode(transactionHelper.getAuthorizationCode());
            }
        } catch (IOException e) {
            transaction.setCode(TransactionCodeEnum.ERRO_DE_PROCESSAMENTO);
            transaction.setAction(ActionEnum.WITHDRAW);
        }
        return transaction;
    }

    private TransactionCodeEnum getTransactionCode(Transaction transaction, String json) {
        if (transaction.getAction() != ActionEnum.WITHDRAW) {
            return TransactionCodeEnum.ERRO_DE_PROCESSAMENTO;
        } else if (transaction.getCardnumber() == null
                || !Pattern.matches("\\d+", transaction.getCardnumber())
                || !(transaction.getCardnumber().length() == 16)
                || !cartaoPrePagoDao.hasConta(transaction.getCardnumber())) {
            return TransactionCodeEnum.CONTA_INVALIDA;
        } else {
            BigDecimal amountBigDecimal = new BigDecimal(transaction.getAmount().replace(",", "."));
            BigDecimal amountSubtract = cartaoPrePagoDao.getSaldo(transaction.getCardnumber())
                    .subtract(amountBigDecimal);

            if (amountSubtract.compareTo(BigDecimal.ONE) < 0) {
                return TransactionCodeEnum.SALDO_INSUFICIENTE;
            } else {
                cartaoPrePagoDao.atualizarSaldo(amountSubtract.toString(), transaction.getCardnumber());
                return TransactionCodeEnum.APROVADA;
            }
        }
    }
}
