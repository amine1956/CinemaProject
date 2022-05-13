package ma.enset.ebancnck.mappers;

import com.fasterxml.jackson.databind.util.BeanUtil;
import ma.enset.ebancnck.Dtos.ClientDTO;
import ma.enset.ebancnck.Entite.Client;
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
}
