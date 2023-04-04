package logic.SplittStratergy;

import models.Expense;
import models.Transaction;
import models.User;

import java.util.List;
import java.util.Map;

public class PercentageSplitStratergy implements ISplittStratergy {

    private static PercentageSplitStratergy percentageSplitStratergy;
    private static ExactSplitStratergy exactSplitStratergy = ExactSplitStratergy.getInstance();

    private PercentageSplitStratergy() {};

    public static PercentageSplitStratergy getInstance() {
        if(percentageSplitStratergy==null) {
            synchronized (PercentageSplitStratergy.class) {
                if(percentageSplitStratergy==null) {
                    percentageSplitStratergy = new PercentageSplitStratergy();
                }
            }
        }
        return percentageSplitStratergy;
    }

    @Override
    public List<Transaction> splitExpense(Expense expense) {
        transformToExact(expense);
        return exactSplitStratergy.splitExpense(expense);
    }

    private void transformToExact(Expense expense) {
        for(Map.Entry< User, Double> payeeUser : expense.getPayeeUsersMap().entrySet())
        {
            payeeUser.setValue((expense.getAmount()*payeeUser.getValue())/100);
        }
    }
}