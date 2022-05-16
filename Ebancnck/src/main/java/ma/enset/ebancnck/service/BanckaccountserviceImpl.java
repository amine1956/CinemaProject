package ma.enset.ebancnck.service;

import lombok.AllArgsConstructor;
import ma.enset.ebancnck.Dtos.*;
import ma.enset.ebancnck.Entite.*;
import ma.enset.ebancnck.Enums.OperationType;
import ma.enset.ebancnck.Exception.BalanceNotSuffisantExeption;
import ma.enset.ebancnck.Exception.BanckAccountNotFoundExeption;
import ma.enset.ebancnck.Exception.ClientNotFoundExeption;
import ma.enset.ebancnck.Reposetory.BanckAccountOperationReposetory;
import ma.enset.ebancnck.Reposetory.BanckAccountReposetory;
import ma.enset.ebancnck.Reposetory.ClientReposetory;
import ma.enset.ebancnck.mappers.BanckAccountMappersImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public ClientDTO getClient(long idClient) throws ClientNotFoundExeption {
        return banckAccountMappers.formCustomer(clientReposetory.findById(idClient).orElseThrow(()->new ClientNotFoundExeption("client not found")));
    }

    @Override
    public ClientDTO saveClient(ClientDTO client) {
       Client saveClint= banckAccountMappers.formCustomerDTO(client);
       Client clientsave=clientReposetory.save(saveClint);
        return banckAccountMappers.formCustomer(clientsave);
    }

    @Override
    public BanckAccountCurrentDTO savaCurrentBanckacount(double innistialBlanc, double overDraft, Long clientId) throws ClientNotFoundExeption {
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
        return banckAccountMappers.fromCurrentAccount(savedbanckAcount);
    }

    @Override
    public BanckAccountSavingDTO savaSvingBanckacount(double innistialBlanc, double intersRate, Long clientId) throws ClientNotFoundExeption {
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
        return banckAccountMappers.fromSavinAccount(savedbanckAcount);

    }



    @Override
    public List<ClientDTO> listClients() {
        List<Client> list=clientReposetory.findAll();
        List<ClientDTO> collect = list.stream().map(client ->
                banckAccountMappers.formCustomer(client)).collect(Collectors.toList());

return  collect;
    }

    @Override
    public BanckAccountDTO getBanckAccount(String idAccount) throws BanckAccountNotFoundExeption {
        BanckAccount banckAccount= banckAccountReposetory.findById(idAccount).orElseThrow(()->new BanckAccountNotFoundExeption("AcountNotFound"));
         if(banckAccount instanceof SavinAccount){
             SavinAccount savinAccount= (SavinAccount) banckAccount;
             return banckAccountMappers.fromSavinAccount(savinAccount);
         }
         else {
             CurrentAccount currentAccount= (CurrentAccount) banckAccount;
             return banckAccountMappers.fromCurrentAccount(currentAccount);
         }

    }

    @Override
    public void debit(String accountId, double amount, String description) throws BanckAccountNotFoundExeption, BalanceNotSuffisantExeption {
        BanckAccount banckAccount= banckAccountReposetory.findById(accountId).orElseThrow(()->new BanckAccountNotFoundExeption("AcountNotFound"));
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
        BanckAccount banckAccount= banckAccountReposetory.findById(accountId).orElseThrow(()->new BanckAccountNotFoundExeption("AcountNotFound"));

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
    public  List<BanckAccountDTO> banckAccountList() {
        List<BanckAccount> banckAccountList = banckAccountReposetory.findAll();
        List<BanckAccountDTO> collect = banckAccountList.stream().map(banckAccount ->
        {
            if (banckAccount instanceof SavinAccount) {
                SavinAccount savinAccount = (SavinAccount) banckAccount;
                return banckAccountMappers.fromSavinAccount(savinAccount);

            } else {
                CurrentAccount currentAccount = (CurrentAccount) banckAccount;
                return banckAccountMappers.fromCurrentAccount(currentAccount);
            }

        }).collect(Collectors.toList());

        return collect;
    }

    @Override
    public ClientDTO updatClient(ClientDTO client) {
        Client saveClint= banckAccountMappers.formCustomerDTO(client);
        Client clientsave=clientReposetory.save(saveClint);
        return banckAccountMappers.formCustomer(clientsave);
    }
    @Override
    public void deletClient(long idClient){
        clientReposetory.deleteById(idClient);
    }
    @Override
    public List<BanckAccountOperationDTO> historyOperationAccount(String accountId){
        return  banckAccountOperationReposetory.findByBanckAccountId(accountId).stream().map(banckAccountOperation -> banckAccountMappers.fromBanckAcountOperation(banckAccountOperation)).collect(Collectors.toList());

    }

    @Override
    public AccountHistoryDTO getAccountHystory(String idAccount, int page, int size) throws BanckAccountNotFoundExeption {
        BanckAccount banckAccount=banckAccountReposetory.findById(idAccount).orElse(null);
        if(banckAccount==null) throw new BanckAccountNotFoundExeption("banck not found");
        Page<BanckAccountOperation> accountOperations = banckAccountOperationReposetory.findByBanckAccountId(idAccount, PageRequest.of(page, size));
        AccountHistoryDTO accountHistoryDTO=new AccountHistoryDTO();
         List<BanckAccountOperationDTO> collect = accountOperations.getContent().stream().map(banckAccountOperation -> banckAccountMappers.fromBanckAcountOperation(banckAccountOperation)).collect(Collectors.toList());
         accountHistoryDTO.setAccountOperationDTOS(collect);
         accountHistoryDTO.setAccountId(banckAccount.getId());
         accountHistoryDTO.setBalance(banckAccount.getBalance());
         accountHistoryDTO.setPageize(size);
         accountHistoryDTO.setCurrentpage(page);
         accountHistoryDTO.setTotalepages(accountOperations.getTotalPages());

        return accountHistoryDTO;
    }

    @Override
    public List<ClientDTO> gestSerchClient(String keyword) {
        List<Client> customers=clientReposetory.searchCustomer(keyword);
        List<ClientDTO> customerDTOS = customers.stream().map(cust -> banckAccountMappers.formCustomer(cust)).collect(Collectors.toList());
        return customerDTOS;


    }
}
