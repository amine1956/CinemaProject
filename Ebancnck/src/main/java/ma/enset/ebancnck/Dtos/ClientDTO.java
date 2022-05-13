package ma.enset.ebancnck.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.enset.ebancnck.Entite.BanckAccount;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientDTO {

    private Long id;
    private String name;
    private String email;


}
