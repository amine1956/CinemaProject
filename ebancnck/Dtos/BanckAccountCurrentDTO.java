package ma.enset.ebancnck.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.enset.ebancnck.Entite.BanckAccountOperation;
import ma.enset.ebancnck.Entite.Client;
import ma.enset.ebancnck.Enums.AccountStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor

public  class BanckAccountCurrentDTO extends BanckAccountDTO {

    private String id;
    private double balance;
    private Date createAt;
    private AccountStatus status;
    private ClientDTO clientDTO;
    private double overDraft;

}
