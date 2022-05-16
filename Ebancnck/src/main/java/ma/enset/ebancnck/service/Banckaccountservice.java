package ma.enset.ebancnck.service;

import ma.enset.ebancnck.Dtos.*;
import ma.enset.ebancnck.Entite.BanckAccount;
import ma.enset.ebancnck.Entite.Client;
import ma.enset.ebancnck.Entite.CurrentAccount;
import ma.enset.ebancnck.Entite.SavinAccount;
import ma.enset.ebancnck.Exception.BalanceNotSuffisantExeption;
import ma.enset.ebancnck.Exception.BanckAccountNotFoundExeption;
import ma.enset.ebancnck.Exception.ClientNotFoundExeption;

import java.util.List;
import java.util.Optional;

public interface Banckaccountservice {
    ClientDTO getClient(long idClient) throws ClientNotFoundExeption;


    ClientDTO saveClient(ClientDTO client);

    BanckAccountCurrentDTO savaCurrentBanckacount(double innistialBlanc, double overDraft, Long clientId) throws ClientNotFoundExeption;
    BanckAccountSavingDTO savaSvingBanckacount(double innistialBlanc, double intersRate, Long clientId) throws ClientNotFoundExeption;
    List<ClientDTO> listClients();
    BanckAccountDTO getBanckAccount(String idAccount) throws BanckAccountNotFoundExeption;
    void debit(String accountId,double amount,String description) throws BanckAccountNotFoundExeption, BalanceNotSuffisantExeption;
    void credit(String accountId,double amount,String description) throws BalanceNotSuffisantExeption, BanckAccountNotFoundExeption;
    void transfert(String accountId,String accountDestination,Double amount) throws BalanceNotSuffisantExeption, BanckAccountNotFoundExeption;

    List<BanckAccountDTO> banckAccountList();
    ClientDTO updatClient(ClientDTO client);

    void deletClient(long idClient);

    List<BanckAccountOperationDTO> historyOperationAccount(String accountId);

    AccountHistoryDTO getAccountHystory(String idAccount, int page, int size) throws BanckAccountNotFoundExeption;

    List<ClientDTO> gestSerchClient(String keyword);
}
