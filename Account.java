public class Account{
    private int accountNum, pin, type;
    private double balance, limit;
    
    public void setAccountNum(int AccountNum){
        this.accountNum = AccountNum;
    }

    public void setPin(int pin){
        this.pin = pin;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public int getAccountNum(){
        return accountNum;
    }

    public int getPin(){
        return pin;
    }

    public int getType() {
        return type;
    }

    public double getBalance(){
        return balance;
    }

    public double getLimit() {
        return limit;
    }
}