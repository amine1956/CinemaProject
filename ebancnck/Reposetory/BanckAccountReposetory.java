package ma.enset.ebancnck.Reposetory;

import ma.enset.ebancnck.Dtos.BanckAccountDTO;
import ma.enset.ebancnck.Dtos.ClientAccountDTO;
import ma.enset.ebancnck.Entite.BanckAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BanckAccountReposetory extends JpaRepository<BanckAccount,String> {
    public Page<BanckAccount> findBanckAccountByClient_Id(long id,Pageable pageable);

}
