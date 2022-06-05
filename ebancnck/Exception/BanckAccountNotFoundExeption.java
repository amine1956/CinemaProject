package ma.enset.ebancnck.Exception;

public class BanckAccountNotFoundExeption extends Exception {
    public BanckAccountNotFoundExeption(String acountNotFound) {
        super(acountNotFound);
    }
}
