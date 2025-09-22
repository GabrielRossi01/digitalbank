package br.com.fiap.digitalbank.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.digitalbank.model.Account;
import br.com.fiap.digitalbank.service.PixService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("pix")
@Slf4j
public class PixController {

    public record PixRequest(Long originAccountId, Long destinationAccountId, BigDecimal amount) {
    }

    @Autowired
    private PixService pixService;

    @PostMapping
    public ResponseEntity<Account> transferPix(@RequestBody PixRequest request) {
        Account updatedAccount = pixService.transfer(request.originAccountId(), request.destinationAccountId(),
                request.amount());
        log.info("PIX realizado com sucesso da conta {}", request.originAccountId());
        return ResponseEntity.ok(updatedAccount);
    }
}
