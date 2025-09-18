package br.com.fiap.digitalbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.digitalbank.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    
}
