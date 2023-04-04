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

        expense.getPayingUsersMap().forEach((user,amount)->{
            if(expense.getPayeeUsersMap().containsKey(user)) {
                if(amount<expense.getPayeeUsersMap().get(user)) {
                    expense.getPayeeUsersMap().put(user, expense.getPayeeUsersMap().get(user)-amount);
                    expense.getPayingUsersMap().remove(user);
                } else {
                    expense.getPayingUsersMap().put(user, amount-expense.getPayeeUsersMap().get(user));
                    expense.getPayeeUsersMap().remove(user);
                }
            }
        });
        return SplitHelper.SplitExpense(expense);
    }
}
