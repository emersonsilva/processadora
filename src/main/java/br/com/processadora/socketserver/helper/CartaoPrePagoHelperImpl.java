package br.com.processadora.socketserver.helper;

import br.com.processadora.socketserver.dao.CartaoPrePagoDao;
import br.com.processadora.socketserver.dao.CartaoPrePagoDaoImpl;
import br.com.processadora.socketserver.model.CartaoPrePago;

public class CartaoPrePagoHelperImpl implements CartaoPrePagoHelper {

    private CartaoPrePagoDao cartaoPrePagoDao = new CartaoPrePagoDaoImpl();

    public void criarCartoesPrePagos() {
        Integer quantidadeCartoesNovos = 2;
        if (cartaoPrePagoDao.getQuantidade() == 0) {
            for (int i = 1; i <= quantidadeCartoesNovos; i++) {
                cartaoPrePagoDao.salvar(new CartaoPrePago(String.format("%016d", i), "1000,00"));
            }
        }
    }
}
