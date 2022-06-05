package ma.enset.ebancnck.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.ebancnck.Dtos.BanckAccountDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
    public class ClientAccountDTO {

    private Long clientId;
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private List<BanckAccountDTO> accounts;

}