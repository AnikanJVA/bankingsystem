import java.util.*;
import java.io.*;
public class Balance{
    private File accountFile, withdrawFile, depositFile;

    public Balance(){
        accountFile = new File("accounts.txt");
        withdrawFile = new File("withdraws.txt");
        // depositFile = new File("deposits.txt");
    }

    public double AllAmountWithdraw(int accountNum){
        Withdraw withdraw = new Withdraw();
        ArrayList<Transaction> withdrawList = withdraw.getWithdrawList();
        double total = 0;
        for (Transaction transaction : withdrawList) {
            if(transaction.getAccountNum() == accountNum){
                total += transaction.getAmount();
            }
        }
        return total;
    }

    public void computeBalance(int accountNum){
        Login accountGetter = new Login();
        ArrayList<Account> accounts = login.getAllAccounts();
        for (Account account : accounts) {
            if(account.getAccountNum() == accountNum){
                
            }
        }
    }
}
