package br.com.fiap.digitalbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.digitalbank.model.Account;
import br.com.fiap.digitalbank.model.AccountStatusType;
import br.com.fiap.digitalbank.repository.AccountRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("accounts")
@Slf4j
public class AccountController {
    
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public List<Account> index() {
        return accountRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@RequestBody @Valid Account account) {
        log.info("Cadastrando conta" + account);
        return accountRepository.save(account);
    }

    @GetMapping("{id}")
    public Account get(@PathVariable Long id) {
        log.info("Buscando conta com id " + id);
        return getAccountById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Excluindo conta com id " + id);
        Account account = getAccountById(id);
        account.setStatus(AccountStatusType.INACTIVE);
        accountRepository.save(account);
    }

    private Account getAccountById(Long id) {
        return accountRepository
                .findById(id)
                .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada com id " + id)
                );
    }
}
