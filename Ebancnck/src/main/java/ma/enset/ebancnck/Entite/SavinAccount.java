package ma.enset.ebancnck.Entite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public  class  SavinAccount extends BanckAccount{
    private double interestRate;
}
