import java.math.BigDecimal;

public class Program {
    public static void main(String[] args) {
        Account account = new Account();
        System.out.println("account.getBalance() = " + account.getBalance());
        TransactionType.WITHDRAWAL.doTransactionOperation(account, BigDecimal.valueOf(100));
        System.out.println("account = " + account.getBalance());
    }
}
