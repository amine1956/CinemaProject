package ma.enset.ebancnck.service;

import ma.enset.ebancnck.Dtos.ClientDTO;
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
    Client saveClient(Client client);
    CurrentAccount savaCurrentBanckacount(double innistialBlanc, double overDraft, Long clientId) throws ClientNotFoundExeption;
    SavinAccount savaSvingBanckacount(double innistialBlanc, double intersRate, Long clientId) throws ClientNotFoundExeption;
    List<ClientDTO> listClients();
    BanckAccount getBanckAccount(String idAccount) throws BanckAccountNotFoundExeption;
    void debit(String accountId,double amount,String description) throws BanckAccountNotFoundExeption, BalanceNotSuffisantExeption;
    void credit(String accountId,double amount,String description) throws BalanceNotSuffisantExeption, BanckAccountNotFoundExeption;
    void transfert(String accountId,String accountDestination,Double amount) throws BalanceNotSuffisantExeption, BanckAccountNotFoundExeption;

    List<BanckAccount> banckAccountList();
}
