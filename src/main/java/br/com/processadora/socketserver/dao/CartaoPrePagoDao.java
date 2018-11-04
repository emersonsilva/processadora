package br.com.processadora.socketserver.dao;

import br.com.processadora.socketserver.model.CartaoPrePago;

import java.math.BigDecimal;

public interface CartaoPrePagoDao {

    Long getQuantidade();

    boolean hasConta(String cardnumber);

    BigDecimal getSaldo(String cardnumber);

    void salvar(CartaoPrePago cartaoPrePago);

    void atualizarSaldo(String amount, String cardnumber);

}
