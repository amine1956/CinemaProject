package ma.enset.ebancnck.mappers;

import com.fasterxml.jackson.databind.util.BeanUtil;
import ma.enset.ebancnck.Dtos.BanckAccountCurrentDTO;
import ma.enset.ebancnck.Dtos.BanckAccountOperationDTO;
import ma.enset.ebancnck.Dtos.BanckAccountSavingDTO;
import ma.enset.ebancnck.Dtos.ClientDTO;
import ma.enset.ebancnck.Entite.BanckAccountOperation;
import ma.enset.ebancnck.Entite.Client;
import ma.enset.ebancnck.Entite.CurrentAccount;
import ma.enset.ebancnck.Entite.SavinAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BanckAccountMappersImpl {
 public ClientDTO formCustomer(Client client){
         ClientDTO clientDTO=new ClientDTO();
     BeanUtils.copyProperties(client,clientDTO);
     return clientDTO;
 }
    public Client formCustomerDTO(ClientDTO clientDTO){
        Client client1=new Client();
        BeanUtils.copyProperties(clientDTO,client1);
        return client1;
    }

    public BanckAccountSavingDTO fromSavinAccount(SavinAccount savinAccount){
BanckAccountSavingDTO banckAccountSavingDTO=new BanckAccountSavingDTO();
BeanUtils.copyProperties(savinAccount,banckAccountSavingDTO);
banckAccountSavingDTO.setType(savinAccount.getClass().getSimpleName());
banckAccountSavingDTO.setClientDTO(formCustomer(savinAccount.getClient()));


return banckAccountSavingDTO;

    }
    public SavinAccount fromSavingAccountDTO(BanckAccountSavingDTO banckAccountSavingDTO){

SavinAccount savinAccount=new SavinAccount();
BeanUtils.copyProperties(banckAccountSavingDTO,savinAccount);
savinAccount.setClient(formCustomerDTO(banckAccountSavingDTO.getClientDTO()));

return savinAccount;
    }

    public BanckAccountCurrentDTO fromCurrentAccount(CurrentAccount currentAccount){
     BanckAccountCurrentDTO banckAccountCurrentDTO=new BanckAccountCurrentDTO();
     BeanUtils.copyProperties(currentAccount,banckAccountCurrentDTO);
        banckAccountCurrentDTO.setType(currentAccount.getClass().getSimpleName());

        banckAccountCurrentDTO.setClientDTO(formCustomer(currentAccount.getClient()));

     return banckAccountCurrentDTO;
    }
    public CurrentAccount fromCurentAccountDTO(BanckAccountCurrentDTO banckAccountCurrentDTO){
CurrentAccount currentAccount=new CurrentAccount();
BeanUtils.copyProperties(banckAccountCurrentDTO,currentAccount);
currentAccount.setClient(formCustomerDTO(banckAccountCurrentDTO.getClientDTO()));
return currentAccount;
    }
    public BanckAccountOperationDTO fromBanckAcountOperation(BanckAccountOperation banckAccountOperation){

     BanckAccountOperationDTO banckAccountOperationDTO=new BanckAccountOperationDTO();
     BeanUtils.copyProperties(banckAccountOperation,banckAccountOperationDTO);
     return banckAccountOperationDTO;
    }



}
