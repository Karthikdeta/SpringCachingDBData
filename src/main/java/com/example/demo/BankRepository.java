package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankDetails, BankDetails.BankId> {

    BankDetails findByBankIdAndBankNameIgnoreCase(int bankId, String bankName);

}
