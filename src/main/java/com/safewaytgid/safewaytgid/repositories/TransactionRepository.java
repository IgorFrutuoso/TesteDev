package com.safewaytgid.safewaytgid.repositories;

import com.safewaytgid.safewaytgid.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
