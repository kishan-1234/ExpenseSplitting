package logic.SplittStratergy;

import models.Expense;
import models.Transaction;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExactSplitStratergy implements ISplittStratergy {

    private static ExactSplitStratergy exactSplitStratergy;

    private ExactSplitStratergy() {}

    public static ExactSplitStratergy getInstance() {
        if(exactSplitStratergy==null) {
            synchronized (ExactSplitStratergy.class) {
                if(exactSplitStratergy==null) {
                    exactSplitStratergy = new ExactSplitStratergy();
                }
            }
        }
        return exactSplitStratergy;
    }

    public List<Transaction> splitExpense(Expense expense) {

        List<Transaction> transactions = new ArrayList<Transaction>();
        for(Map.Entry<User, Double> payingUser : expense.getPayingUsersMap().entrySet())
        {
            for(Map.Entry<User, Double> payeeUser : expense.getPayeeUsersMap().entrySet())
            {
                Double payingAmount = payingUser.getValue();
                Double paidAmount = payeeUser.getValue();
                if(paidAmount==0.0)
                    continue;
                Transaction transaction = new Transaction();
                transaction.setPaidByUser(payingUser.getKey());
                transaction.setPaidToUser(payeeUser.getKey());
                if(payingAmount>=paidAmount)
                {
                    transaction.setAmount(paidAmount);
                    payeeUser.setValue(0.0);
                    payingUser.setValue(payingAmount-paidAmount);
                    transactions.add(transaction);
                } else {
                    transaction.setAmount(payingAmount);
                    payeeUser.setValue(paidAmount-payingAmount);
                    payingUser.setValue(0.0);
                    transactions.add(transaction);
                    break;
                }
            }
        }
        return transactions;
    }
}
