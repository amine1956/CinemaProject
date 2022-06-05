package ma.enset.ebancnck.Entite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.enset.ebancnck.Enums.AccountStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.JOINED)

//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "TYPE" ,length=4 ,discriminatorType = DiscriminatorType.STRING)
@ToString
public  class BanckAccount {
    @Id
    private String id;
    private double balance;
    private Date createAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @ManyToOne
    private Client client;
    @OneToMany(mappedBy = "banckAccount" ,fetch = FetchType.LAZY)
    private List<BanckAccountOperation> accountOperations;
}
