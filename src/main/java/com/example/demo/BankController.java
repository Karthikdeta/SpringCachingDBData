package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;

    @PostMapping("/bank/insertBulkAccounts")
    public String insertBulkBankAccounts() {
        bankService.insertBankAccounts();
        return "Bulk Bank Insertion Successful";
    }

    @GetMapping("/bank/getBankDetails")
    public BankDetails getBankDetails(@RequestParam int bankId, @RequestParam String bankName) {
        return bankService.getBank(bankId, bankName);
    }
}
