package ma.enset.ebancnck.Reposetory;

import ma.enset.ebancnck.Entite.BanckAccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanckAccountOperationReposetory extends JpaRepository<BanckAccountOperation,Long> {
}
