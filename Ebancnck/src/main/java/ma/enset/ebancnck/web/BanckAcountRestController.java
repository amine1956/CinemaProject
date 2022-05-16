package ma.enset.ebancnck.web;

import lombok.AllArgsConstructor;
import ma.enset.ebancnck.Dtos.AccountHistoryDTO;
import ma.enset.ebancnck.Dtos.BanckAccountDTO;
import ma.enset.ebancnck.Dtos.BanckAccountOperationDTO;
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
}
