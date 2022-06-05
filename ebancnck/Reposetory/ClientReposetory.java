package ma.enset.ebancnck.Reposetory;

import ma.enset.ebancnck.Entite.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientReposetory extends JpaRepository<Client,Long> {
    @Query("select c from Client c where c.name like :kw")
    List<Client> searchCustomer(@Param("kw") String keyword);

}
