package br.com.processadora;

import br.com.processadora.socketserver.controller.ServidorSocketController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.processadora.socket.repository"})
public class ProcessadoraApplication {

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC-03"));
    }

    public static void main(String[] args) {
        SpringApplication.run(ProcessadoraApplication.class, args);
        ServidorSocketController servidorController = new ServidorSocketController();
        servidorController.inicializar();
    }
}
