package br.com.fiap.digitalbank.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.digitalbank.model.Account;
import br.com.fiap.digitalbank.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class WithdrawalService {
    
    @Autowired
    private AccountRepository accountRepository;

    public Account withdraw(Long accountId, BigDecimal amount) {
        Account account = accountRepository
                    .findById(accountId)
                    .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        if (account.getOpeningBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        } 

        account.setOpeningBalance(account.getOpeningBalance().subtract(amount));
        return accountRepository.save(account);
    }
}
