package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankService {

    private final BankRepository bankRepository;

    public void insertBankAccounts() {
        List<String> bankList = List.of("Axis Bank", "Standard Chattered Bank", "HDFC Bank",
                "ICICI Bank", "Canara Bank");
        List<String> countryList =
                List.of("India", "Pakistan", "Singapore", "United States", "United Kingdom");
        for (int i = 0; i < bankList.size(); i++) {
            String bankName = bankList.get(i);
            List<BankDetails> list = new ArrayList<>();
            for (int j = 1; j <= 5; j++) {
                String country = countryList.get(j - 1);
                list.add(BankDetails.builder().bankId(j).bankName(bankName).country(country)
                        .state("State - " + j).noOfUsers((long) (Math.random() * 10000)).build());
            }
            bankRepository.saveAll(list);
        }
    }

    public BankDetails getBank(int bankId, String bankName) {
        return bankRepository.findByBankIdAndBankNameIgnoreCase(bankId, bankName);
    }

}
