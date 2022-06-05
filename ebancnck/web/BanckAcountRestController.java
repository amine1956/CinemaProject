package ma.enset.ebancnck.web;

import lombok.AllArgsConstructor;
import ma.enset.ebancnck.Dtos.*;
import ma.enset.ebancnck.Exception.BalanceNotSuffisantExeption;
import ma.enset.ebancnck.Exception.BanckAccountNotFoundExeption;
import ma.enset.ebancnck.service.Banckaccountservice;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Service
@AllArgsConstructor
@CrossOrigin("*")
public class BanckAcountRestController {
    private Banckaccountservice banckaccountservice;
    @GetMapping("/accounts/{idAccount}")
    public BanckAccountDTO getBanckAccount(@PathVariable String idAccount) throws BanckAccountNotFoundExeption {
        return banckaccountservice.getBanckAccount(idAccount);
    }
    @GetMapping("/accounts")
    public List<BanckAccountDTO> listAccount(){
        return banckaccountservice.banckAccountList();
    }
    @GetMapping("/accounts/{idAccount}/operations")
    public List<BanckAccountOperationDTO> getHistory(@PathVariable String idAccount){
return banckaccountservice.historyOperationAccount(idAccount);
    }
    @GetMapping("/accounts/{idAccount}/pageoperations")
    public AccountHistoryDTO getHistoryAcount(@PathVariable String idAccount,
                                              @RequestParam(name = "page",defaultValue = "0") int page, @RequestParam(name = "size" ,defaultValue = "5")int size) throws BanckAccountNotFoundExeption {
        return banckaccountservice.getAccountHystory(idAccount,page,size);
    }
@PostMapping("/saveDebit/{id}")
    public void savOperationDebit(@RequestParam(name = "id") String id) throws BalanceNotSuffisantExeption, BanckAccountNotFoundExeption {
        banckaccountservice.debit(id,5000,"description");
}
    @PostMapping("/accounts/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BalanceNotSuffisantExeption, BanckAccountNotFoundExeption {
        this.banckaccountservice.debit(debitDTO.getAccountId(),debitDTO.getAmount(),debitDTO.getDescription());
        return debitDTO;
    }
    @PostMapping("/accounts/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BalanceNotSuffisantExeption, BanckAccountNotFoundExeption {
        this.banckaccountservice.credit(creditDTO.getAccountId(),creditDTO.getAmount(),creditDTO.getDescription());
        return creditDTO;
    }
    @PostMapping("/accounts/transfer")
    public void transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BalanceNotSuffisantExeption, BanckAccountNotFoundExeption {
        this.banckaccountservice.transfert(
                transferRequestDTO.getAccountSource(),
                transferRequestDTO.getAccountDestination(),
                transferRequestDTO.getAmount());
    }
}

