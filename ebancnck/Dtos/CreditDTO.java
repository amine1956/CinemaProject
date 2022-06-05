package ma.enset.ebancnck.Dtos;
import lombok.Data;

@Data
public class CreditDTO {
    private String accountId;
    private double amount;
    private String description;
}