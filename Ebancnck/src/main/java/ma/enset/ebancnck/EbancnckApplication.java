package ma.enset.ebancnck;

import ma.enset.ebancnck.Entite.*;
import ma.enset.ebancnck.Enums.AccountStatus;
import ma.enset.ebancnck.Enums.OperationType;
import ma.enset.ebancnck.Reposetory.*;
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
    CommandLineRunner commandLineRunner(BanckAccountReposetory banckAccountReposetory){
        return args->{
          BanckAccount banckAccount =banckAccountReposetory.findById("6ee33cfb-2597-4eac-a430-7db9a0ca07c8").orElse(null);
            System.out.println(banckAccount);
            System.out.println(banckAccount.getClient().getName());
            System.out.println(banckAccount.getClass().getSimpleName());
            if(banckAccount instanceof CurrentAccount){
                System.out.println("Over draft=>"+((CurrentAccount)banckAccount).getOverDraft());
            }
            if(banckAccount instanceof SavinAccount){
                System.out.println("Over draft=>"+((SavinAccount)banckAccount).getInterestRate());
            }
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
          currentAccount.setBalance(9000*Math.random());
          currentAccount.setCreateAt(new Date());
          currentAccount.setStatus(AccountStatus.ACIVATED);
          currentAccount.setOverDraft(90000);
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
