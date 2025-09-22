package br.com.fiap.digitalbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.digitalbank.model.Account;
import br.com.fiap.digitalbank.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DepositService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public Account deposit(Long accountId, Double amount) {
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        account.setOpeningBalance(account.getOpeningBalance().add(java.math.BigDecimal.valueOf(amount)));
        return accountRepository.save(account);
    }
}
