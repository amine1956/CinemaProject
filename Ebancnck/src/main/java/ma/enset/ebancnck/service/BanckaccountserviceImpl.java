package ma.enset.ebancnck.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import ma.enset.ebancnck.Dtos.ClientDTO;
import ma.enset.ebancnck.Entite.*;
import ma.enset.ebancnck.Enums.OperationType;
import ma.enset.ebancnck.Exception.BalanceNotSuffisantExeption;
import ma.enset.ebancnck.Exception.BanckAccountNotFoundExeption;
import ma.enset.ebancnck.Exception.ClientNotFoundExeption;
import ma.enset.ebancnck.Reposetory.BanckAccountOperationReposetory;
import ma.enset.ebancnck.Reposetory.BanckAccountReposetory;
import ma.enset.ebancnck.Reposetory.ClientReposetory;
import ma.enset.ebancnck.mappers.BanckAccountMappersImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
 public class BanckaccountserviceImpl implements Banckaccountservice {

   private ClientReposetory clientReposetory;
   private BanckAccountReposetory banckAccountReposetory;
    
   private BanckAccountOperationReposetory banckAccountOperationReposetory;

   private BanckAccountMappersImpl banckAccountMappers;

    @Override
    public Client saveClient(Client client) {
       Client saveClint= clientReposetory.save(client);
        return saveClint;
    }

    @Override
    public CurrentAccount savaCurrentBanckacount(double innistialBlanc, double overDraft, Long clientId) throws ClientNotFoundExeption {
        Client client= clientReposetory.findById(clientId).orElse(null);
        if(client==null)
            throw new ClientNotFoundExeption(" Client not found");
        CurrentAccount currentAccount=new CurrentAccount();

        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreateAt(new Date());
        currentAccount.setBalance(innistialBlanc);
        currentAccount.setOverDraft(overDraft);
        currentAccount.setClient(client);
        CurrentAccount savedbanckAcount=banckAccountReposetory.save(currentAccount);
        return savedbanckAcount;
    }

    @Override
    public SavinAccount savaSvingBanckacount(double innistialBlanc, double intersRate, Long clientId) throws ClientNotFoundExeption {
        Client client= clientReposetory.findById(clientId).orElse(null);
        if(client==null)
            throw new ClientNotFoundExeption(" Client not found");
        SavinAccount savinAccount=new SavinAccount();

        savinAccount.setId(UUID.randomUUID().toString());
        savinAccount.setCreateAt(new Date());
        savinAccount.setBalance(innistialBlanc);
        savinAccount.setInterestRate(intersRate);
        savinAccount.setClient(client);
        SavinAccount savedbanckAcount=banckAccountReposetory.save(savinAccount);
        return savedbanckAcount;

    }



    @Override
    public List<ClientDTO> listClients() {
        List<Client> list=clientReposetory.findAll();
        List<ClientDTO> collect = list.stream().map(client ->
                banckAccountMappers.formCustomer(client)).collect(Collectors.toList());

return  collect;
    }

    @Override
    public BanckAccount getBanckAccount(String idAccount) throws BanckAccountNotFoundExeption {
        BanckAccount banckAccount= banckAccountReposetory.findById(idAccount).orElseThrow(()->new BanckAccountNotFoundExeption("AcountNotFound"));
    return  banckAccount;
    }

    @Override
    public void debit(String accountId, double amount, String description) throws BanckAccountNotFoundExeption, BalanceNotSuffisantExeption {
         BanckAccount banckAccount=getBanckAccount(accountId);
         if(banckAccount.getBalance()<amount)
             throw new BalanceNotSuffisantExeption("balance Not suffisant");
         BanckAccountOperation banckAccountOperation=new BanckAccountOperation();
         banckAccountOperation.setType(OperationType.DEBIT);
         banckAccountOperation.setDescription(description);
         banckAccountOperation.setOperationDate(new Date());
         banckAccountOperation.setBanckAccount(banckAccount);
         banckAccountOperationReposetory.save(banckAccountOperation);
        banckAccount.setBalance(banckAccount.getBalance()-amount);
        banckAccountReposetory.save(banckAccount);

    }

    @Override
    public void credit(String accountId, double amount, String description) throws BanckAccountNotFoundExeption {
        BanckAccount banckAccount=getBanckAccount(accountId);

        BanckAccountOperation banckAccountOperation=new BanckAccountOperation();
        banckAccountOperation.setType(OperationType.CEREDIT);
        banckAccountOperation.setDescription(description);
        banckAccountOperation.setOperationDate(new Date());
        banckAccountOperation.setBanckAccount(banckAccount);
        banckAccountOperationReposetory.save(banckAccountOperation);
        banckAccount.setBalance(banckAccount.getBalance()+amount);
        banckAccountReposetory.save(banckAccount);

    }

    @Override
    public void transfert(String accountId, String accountDestination, Double amount) throws BalanceNotSuffisantExeption, BanckAccountNotFoundExeption {
        debit(accountId,amount,"transfert to"+accountDestination);
        credit(accountDestination,amount,"transfet from "+accountId);

    }
    @Override
    public  List<BanckAccount> banckAccountList(){
        return banckAccountReposetory.findAll();
    }
}
