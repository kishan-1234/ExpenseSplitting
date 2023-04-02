package logic.SplittStratergy;

import models.Expense;
import models.Transaction;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EqualSplitStratergy implements ISplittStratergy {

    private static EqualSplitStratergy equalSplitStratergy;

    private EqualSplitStratergy() {}

    public static EqualSplitStratergy getInstance() {
        if(equalSplitStratergy==null) {
            synchronized (EqualSplitStratergy.class) {
                if(equalSplitStratergy==null) {
                    equalSplitStratergy = new EqualSplitStratergy();
                }
            }
        }
        return equalSplitStratergy;
    }

    public List<Transaction> splitExpense(Expense expense) {

        Double perUserExpense = expense.getAmount()/expense.getUsers().size();
        List<Transaction> transactions = new ArrayList<Transaction>();
        Integer userIndex = 0;
        Double userExpense = perUserExpense;
        for(Map.Entry<User, Double> payingUser : expense.getPayingUsersMap().entrySet())
        {
            Double paidAmount = payingUser.getValue();
            while(paidAmount>0.0 && userIndex<expense.getUsers().size())
            {
                Transaction transaction = new Transaction();
                transaction.setPaidByUser(payingUser.getKey());
                transaction.setPaidToUser(expense.getUsers().get(userIndex));
                if(paidAmount>=userExpense) {
                    paidAmount -= userExpense;
                    transaction.setAmount(userExpense);
                    userExpense = perUserExpense;
                    ++userIndex;
                } else {
                    transaction.setAmount(paidAmount);
                    userExpense -= paidAmount;
                    paidAmount = 0.0;
                }
                transactions.add(transaction);
            }
        }
        return transactions;
    }
}
