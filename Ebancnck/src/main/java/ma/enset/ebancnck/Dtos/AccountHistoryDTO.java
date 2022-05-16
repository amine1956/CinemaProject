package ma.enset.ebancnck.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountHistoryDTO {
    private String accountId;
    private double balance;
    private int currentpage;
    private int totalepages;
    private  int pageize;
    private List<BanckAccountOperationDTO> accountOperationDTOS;
}
