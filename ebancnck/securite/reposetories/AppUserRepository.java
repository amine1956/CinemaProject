package ma.enset.ebancnck.securite.reposetories;

import ma.enset.ebancnck.securite.entitee.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}