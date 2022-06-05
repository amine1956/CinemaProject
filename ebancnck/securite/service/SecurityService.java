package ma.enset.ebancnck.securite.service;

import ma.enset.ebancnck.securite.entitee.AppRole;
import ma.enset.ebancnck.securite.entitee.AppUser;

import java.util.List;

public interface SecurityService {

    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser( String username, String roleName);
    AppUser loadUserByUsername( String username);
    List<AppUser> listUsers();
    // login
    AppUser login(AppUser appUser);
    // logout
}