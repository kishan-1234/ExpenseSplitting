package logic;

import models.Transaction;
import models.User;

import java.util.ArrayList;
import java.util.Map;

public class ExpenseCalculator {

    Map<User,Integer> userExpenseIndexMap;
    ArrayList<Double> debts;

    private static ExpenseCalculator expenseCalculator;

    private ExpenseCalculator() {}

    public static ExpenseCalculator getInstance() {
        if(expenseCalculator==null) {
            synchronized (ExpenseCalculator.class) {
                if(expenseCalculator==null) {
                    expenseCalculator = new ExpenseCalculator();
                }
            }
        }
        return expenseCalculator;
    }

    private Integer getUserIndex(User user) {
        if(!checkIsUserIndexPresent(user)) {
            addUserIndex(user);
        }
        return userExpenseIndexMap.get(user);
    }

    private boolean checkIsUserIndexPresent(User user) {
       return userExpenseIndexMap.containsKey(user);
    }

    private synchronized void addUserIndex(User user) {
        if(!userExpenseIndexMap.containsKey(user)) {
            debts.add(0.0);
            userExpenseIndexMap.put(user, debts.size() - 1);
        }
    }

    public void addTransaction(Transaction transaction) {
        Integer paidByUserIndex = getUserIndex(transaction.getPaidByUser());
        Integer paidToUserIndex = getUserIndex(transaction.getPaidToUser());
        synchronized (ExpenseCalculator.class) {
            debts.add(paidByUserIndex, transaction.getAmount());
            debts.add(paidToUserIndex, transaction.getAmount());
        }
    }

    public Double getUserDebt(User user) {
        if(checkIsUserIndexPresent(user)) {
            return null;
        }
        return debts.get(getUserIndex(user));
    }
}
