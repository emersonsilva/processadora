package br.com.processadora.apirest.resource;

import br.com.processadora.apirest.service.CartaoPrePagoService;
import br.com.processadora.socketserver.model.CartaoPrePago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartaoPrePagoResource {

    @Autowired
    private CartaoPrePagoService service;

    @GetMapping("/dadoscartao/{cardnumber}")
    public CartaoPrePago getCartaoPrePagoByCardnumber(@PathVariable("cardnumber") String cardnumber) {
        return service.findByCardnumber(cardnumber);
    }

}
