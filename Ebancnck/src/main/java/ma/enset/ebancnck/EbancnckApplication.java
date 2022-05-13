package ma.enset.ebancnck;

import ma.enset.ebancnck.Entite.*;
import ma.enset.ebancnck.Enums.AccountStatus;
import ma.enset.ebancnck.Enums.OperationType;
import ma.enset.ebancnck.Exception.BalanceNotSuffisantExeption;
import ma.enset.ebancnck.Exception.BanckAccountNotFoundExeption;
import ma.enset.ebancnck.Exception.ClientNotFoundExeption;
import ma.enset.ebancnck.Reposetory.*;
import ma.enset.ebancnck.service.Banckaccountservice;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbancnckApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbancnckApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(Banckaccountservice banckaccountservice){
        return args->{
            Stream.of("hassane","imane","mohamed").forEach(name ->{
                Client client=new Client();
                client.setName(name);
                client.setEmail(name+"@gmail.com");
                banckaccountservice.saveClient(client);

            } );
            banckaccountservice.listClients().forEach(client -> {
                try {
                    banckaccountservice.savaCurrentBanckacount(Math.random()*900000,9000,client.getId());
                    banckaccountservice.savaSvingBanckacount(Math.random()*120000,5.5,client.getId());
                    banckaccountservice.banckAccountList().forEach(banckAccount -> {
                        for(int i=0;i<10;i++){
                            try {

                                banckaccountservice.credit(banckAccount.getId(),10000+Math.random()*120000,"credit");
                                banckaccountservice.debit(banckAccount.getId(),1000+Math.random()*90000,"debit");


                            } catch (BalanceNotSuffisantExeption e) {
                                e.printStackTrace();
                            } catch (BanckAccountNotFoundExeption e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (ClientNotFoundExeption e) {
                    e.printStackTrace();
                }
            });
        };

    }
//@Bean
CommandLineRunner start(ClientReposetory  clientReposetory ,BanckAccountReposetory banckAccountReposetory,BanckAccountOperationReposetory banckAccountOperationReposetory){
  return args->{
      Stream.of("hassan","yassin","khalid").forEach(name -> {
          Client client =new Client();
          client.setName(name);
          client.setEmail(name+"@gmail.com");
          clientReposetory.save(client);
      });
      clientReposetory.findAll().forEach(client -> {

          CurrentAccount currentAccount=new CurrentAccount();
          currentAccount.setId(UUID.randomUUID().toString());
          currentAccount.setBalance(900000*Math.random());
          currentAccount.setCreateAt(new Date());
          currentAccount.setStatus(AccountStatus.ACIVATED);
          currentAccount.setOverDraft(9000);
          currentAccount.setClient(client);
          banckAccountReposetory.save(currentAccount);

          SavinAccount savinAccount=new SavinAccount();
          savinAccount.setId(UUID.randomUUID().toString());

          savinAccount.setBalance(9000*Math.random());
          savinAccount.setCreateAt(new Date());
          savinAccount.setStatus(AccountStatus.CREATED);
          savinAccount.setInterestRate(5.5);
          savinAccount.setClient(client);
          banckAccountReposetory.save(savinAccount);

      });
      banckAccountReposetory.findAll().forEach(banckAccount -> {
          for (int i=0;i<10;i++)
          {
              BanckAccountOperation banckAccountOperation =new BanckAccountOperation();
              banckAccountOperation.setOperationDate(new Date());
              banckAccountOperation.setAmount(Math.random()*9000);
              banckAccountOperation.setType(Math.random()>0.5 ? OperationType.CEREDIT:OperationType.DEBIT);
              banckAccountOperation.setDescription("Operation de "+banckAccountOperation.getType());
              banckAccountOperation.setBanckAccount(banckAccount);
              banckAccountOperationReposetory.save(banckAccountOperation);

          }
      });
  };
}
}
