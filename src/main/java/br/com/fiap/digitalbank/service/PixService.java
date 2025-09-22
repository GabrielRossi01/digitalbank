package br.com.fiap.digitalbank.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.digitalbank.model.Account;
import br.com.fiap.digitalbank.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PixService {
    
    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public Account transfer(Long originAccountId, Long destinationAccountId, BigDecimal amount) {
        if (originAccountId.equals(destinationAccountId)) {
            throw new IllegalArgumentException("Conta de origem e destino não podem ser iguais.");
        }

        Account origin = accountRepository
                    .findById(originAccountId)
                    .orElseThrow(() -> new EntityNotFoundException("Conta de origem não encontrada"));

        Account destionation = accountRepository
                    .findById(destinationAccountId)
                    .orElseThrow(() -> new EntityNotFoundException("Conta de destino não encontrada"));

        if (origin.getOpeningBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para a transferência.");
        }

        origin.setOpeningBalance(origin.getOpeningBalance().subtract(amount));
        destionation.setOpeningBalance(destionation.getOpeningBalance().add(amount));

        accountRepository.save(destionation);
        return accountRepository.save(origin);
    }
}
