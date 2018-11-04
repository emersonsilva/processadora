package br.com.processadora.apirest.service;

import br.com.processadora.socketserver.model.CartaoPrePago;

public interface CartaoPrePagoService {

    CartaoPrePago findByCardnumber(String cardnumber);

}
