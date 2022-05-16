package ma.enset.ebancnck.Reposetory;

import ma.enset.ebancnck.Entite.BanckAccountOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BanckAccountOperationReposetory extends JpaRepository<BanckAccountOperation,Long> {
    public Page<BanckAccountOperation> findByBanckAccountId(String banckAccountId, Pageable pageable);

    public List<BanckAccountOperation> findByBanckAccountId(String banckAccountId);
}
