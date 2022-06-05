package ma.enset.ebancnck.Dtos;
import lombok.Data;

import lombok.Data;

@Data
public class TransferRequestDTO {
    private String accountSource;
    private String accountDestination;
    private double amount;
    private String description;
}