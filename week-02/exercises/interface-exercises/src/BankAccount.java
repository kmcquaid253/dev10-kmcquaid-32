public class BankAccount implements MoneyStorage{
    private double balance;
    private String accountNumber;

    public BankAccount(double startingBalance, String accountNumber) {
        this.balance = startingBalance;
        this.accountNumber = accountNumber;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getDescription() {
        return String.format("BankAccount: #%s", accountNumber);
    }

    @Override
    public boolean deposit(double amount) {
        if (amount <= 0.0)  return false;
            balance += amount;


        return true;
    }

    @Override
    public double withdraw(double amount) {
        double maxWithdrawal = balance + 25;

        //cant withdraw more than balnce + 25
        if(amount > maxWithdrawal)return 0.0;

        balance -= amount;

        return amount;
    }
}
