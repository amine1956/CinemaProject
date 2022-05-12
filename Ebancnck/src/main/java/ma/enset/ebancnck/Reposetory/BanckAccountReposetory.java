package ma.enset.ebancnck.Reposetory;

import ma.enset.ebancnck.Entite.BanckAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanckAccountReposetory extends JpaRepository<BanckAccount,String> {
}
