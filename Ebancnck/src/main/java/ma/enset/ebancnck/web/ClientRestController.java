package ma.enset.ebancnck.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebancnck.Dtos.ClientDTO;
import ma.enset.ebancnck.Entite.Client;
import ma.enset.ebancnck.service.Banckaccountservice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class ClientRestController {
    private Banckaccountservice banckaccountservice;
@GetMapping("/clients")
    public List<ClientDTO> clients(){
        return banckaccountservice.listClients();

    }
}
