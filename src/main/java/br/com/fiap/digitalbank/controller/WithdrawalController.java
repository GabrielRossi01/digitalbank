package br.com.fiap.digitalbank.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.digitalbank.model.Account;
import br.com.fiap.digitalbank.service.WithdrawalService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("withdrawals")
@Slf4j
public class WithdrawalController {

    public record WithdrawalRequest(Long accountId, BigDecimal amount) {}
    
    @Autowired
    private WithdrawalService withdrawalService;

    @PostMapping
    public ResponseEntity<?> withdraw(@RequestBody WithdrawalRequest request) {
        try {
            Account updatedAccount = withdrawalService.withdraw(request.accountId(), request.amount());
            log.info("Saque realizado com sucesso para a conta {}", request.accountId());
            return ResponseEntity.ok(updatedAccount);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
