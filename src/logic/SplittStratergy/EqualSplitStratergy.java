package logic.SplittStratergy;

import models.Expense;
import models.Transaction;
import models.User;

import java.util.*;

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
        Iterator<Map.Entry<User,Double>> iter = expense.getPayingUsersMap().entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<User,Double> entry = iter.next();
            User user = entry.getKey();
            Double amount = entry.getValue();
            if(amount<perUserExpense) {
                expense.getPayeeUsersMap().put(user, perUserExpense-amount);
                iter.remove();
            } else {
                entry.setValue(amount-perUserExpense);
                expense.getPayeeUsersMap().remove(user);
            }
        }


        return SplitHelper.SplitExpense(expense);
    }
}
