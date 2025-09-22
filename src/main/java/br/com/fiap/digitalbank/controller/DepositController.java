package br.com.fiap.digitalbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.digitalbank.model.Account;
import br.com.fiap.digitalbank.service.DepositService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("deposits")
@Slf4j
public class DepositController {

    @Autowired
    private DepositService depositService;

    public record DepositRequest(Long accountId, Double amount) {}

    @PostMapping
    public ResponseEntity<Account> deposit(@Valid @RequestBody DepositRequest request) {
        Account updatedAccount = depositService.deposit(request.accountId(), request.amount());
        return ResponseEntity.ok(updatedAccount);
    }

}
