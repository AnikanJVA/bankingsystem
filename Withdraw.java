import java.util.*;
import java.io.*;
public class Withdraw{
    private File withdrawFile;

    public Withdraw(){
        withdrawFile = new File("withdraws.txt");
    }

    public ArrayList<Transaction> getWithdrawList(){
        ArrayList<Transaction> withdrawList = new ArrayList<>();
        try{
            Scanner scan = new Scanner(new FileReader(withdrawFile));
            while(scan.hasNextLine()){
                try{
                    Transaction Transaction = new Transaction();
                    Transaction.setAccountNum(scan.nextInt());
                    Transaction.setTransId(scan.nextInt());
                    Transaction.setAmount(scan.nextDouble());
                    withdrawList.add(Transaction);
                }
                catch(Exception err){
                    continue;
                }
            }
            scan.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return withdrawList;
    }

    public void withdrawamount(int accountNum, double amount){
        ArrayList<Transaction> withdrawList = getWithdrawList();
        FileWriter fw;
        BufferedWriter bw;
        int counter = 0;
        if(!withdrawList.isEmpty()){
            for (Transaction transaction : withdrawList) {
                if(transaction.getAccountNum() == accountNum){
                    counter++;
                }
            }
        }

        try{
            fw = new FileWriter(withdrawFile, true);
            bw = new BufferedWriter(fw);
            bw.write(accountNum + " " + counter + " " + amount + "\n");
            bw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public double showBalance(int accountNum){
        Login accountGetter = new Login();
        ArrayList<Account> accounts = accountGetter.getAllAccounts();
        double balance = 0;
        
        for (Account account : accounts) {
            if(account.getAccountNum() == accountNum){
                balance = account.getBalance();
            }
        }
        return balance;
    }

    // TO DO: move to showBalance class
    public double totalWithdraw(int accountNum){
        ArrayList<Transaction> withdrawList = getWithdrawList();
        double total = 0;
        for (Transaction transaction : withdrawList) {
            if(transaction.getAccountNum() == accountNum){
                total += transaction.getAmount();
            }
        }
        return total;
    }
}