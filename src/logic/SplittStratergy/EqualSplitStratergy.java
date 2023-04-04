package logic.SplittStratergy;

import models.Expense;
import models.Transaction;
import models.User;

import java.util.ArrayList;
import java.util.HashMap;
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
        expense.setPayeeUsersMap(new HashMap<>());
        expense.getUsers().stream().forEach(user ->{
            expense.getPayeeUsersMap().put(user, perUserExpense);
        });
        expense.getPayingUsersMap().forEach((user,amount)-> {
            if(amount<perUserExpense) {
                expense.getPayeeUsersMap().put(user, perUserExpense-amount);
                expense.getPayingUsersMap().remove(user);
            } else {
                expense.getPayingUsersMap().put(user, amount-perUserExpense);
                expense.getPayeeUsersMap().remove(user);
            }
        });
        return SplitHelper.SplitExpense(expense);
    }
}
