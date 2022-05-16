package ma.enset.ebancnck.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebancnck.Dtos.ClientDTO;
import ma.enset.ebancnck.Entite.Client;
import ma.enset.ebancnck.Exception.ClientNotFoundExeption;
import ma.enset.ebancnck.service.Banckaccountservice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@CrossOrigin("*")

public class ClientRestController {
    private Banckaccountservice banckaccountservice;
@GetMapping("/clients")
    public List<ClientDTO> clients(){
        return banckaccountservice.listClients();

    }

    @GetMapping("/clients/search")
    public List<ClientDTO> clientsSerch(@RequestParam(name = "keyword",defaultValue = "")String keyword){
        return banckaccountservice.gestSerchClient("%"+keyword+"%");

    }

@GetMapping("/clients/{id}")
public ClientDTO getClient(@PathVariable(name = "id") Long idClient) throws ClientNotFoundExeption {
    return  banckaccountservice.getClient(idClient);
}
@PostMapping("/clients")
public ClientDTO saveClient(@RequestBody ClientDTO clientDTO){
return banckaccountservice.saveClient(clientDTO);
}
@PutMapping("/clients/{idClienet}")
public ClientDTO updatClient(@PathVariable(name = "idClienet") Long idClienet,@RequestBody ClientDTO clientDTO){
    clientDTO.setId(idClienet);

    return banckaccountservice.updatClient(clientDTO);

}
@DeleteMapping("/clinets/{id}")
    public void deletClient(@PathVariable(name = "id") long idClient){
    banckaccountservice.deletClient(idClient);
}
}
