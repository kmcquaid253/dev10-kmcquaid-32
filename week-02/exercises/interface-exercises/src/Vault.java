public class Vault implements MoneyStorage{
    //fields
    private double balance;
    private String description;

    private Wallet wallet;

    //constructor
    public Vault(double startingBalance, String description) {
        this.balance = startingBalance;
        this.description = description;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean deposit(double amount) {
        if (amount > 0.0) {
            balance += amount;
            return true;
        }
        return false;
    }

    @Override
    public double withdraw(double amount) {
        // can't withdraw a negative amount
        if (amount <= 0.0) {
            return 0.0;
        }

        //cresult will hold the ACTUAL amount we do take out
        //we cant take out more than the balance
        //but we can take out less
        //so we sue the min to figure out how much we'll really remove
        double result = Math.min(amount, balance);
        balance -= result;

        return result;
    }
}

