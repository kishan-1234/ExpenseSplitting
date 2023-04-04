package repository;

import exceptions.ExpenseException;
import logic.ExpenseCalculator;
import logic.SplittStratergy.SplitStratergy;
import lombok.extern.slf4j.Slf4j;
import models.Expense;
import models.ExpenseSettlement;
import models.Transaction;
import models.User;

import java.util.*;

@Slf4j
public class UserExpenseRepository {

    private static UserExpenseRepository userExpenseRepository;

    private static Map<User, List<Transaction>> userTransactionsMap;
    static {
        userTransactionsMap = new HashMap<>();
    }
    private UserExpenseRepository() {}

    public static UserExpenseRepository getInstance() {
        if(userExpenseRepository==null) {
            synchronized (UserExpenseRepository.class) {
                if(userExpenseRepository==null) {
                    userExpenseRepository = new UserExpenseRepository();
                }
            }
        }
        return userExpenseRepository;
    }

    public ExpenseException addUserExpense(Expense expense) {

        ExpenseException exception = null;
        try {
            List<Transaction> transactions = SplitStratergy.getInstance().splitExpense(expense);
            transactions.stream().forEach(transaction -> {
//                ExpenseCalculator.getInstance().addTransaction(transaction);
                User payingUser = transaction.getPaidByUser();
                    if (!userTransactionsMap.containsKey(payingUser)) {
                        userTransactionsMap.put(transaction.getPaidByUser(), new ArrayList<>(Arrays.asList(transaction)));
                    } else {
                        List<Transaction> userTransactions = userTransactionsMap.get(payingUser);
                        userTransactions.add(transaction);
                        userTransactionsMap.put(payingUser, userTransactions);
                    }
            });
        } catch (Exception e) {
            log.error("Error in adding user expense : {}", e);
            exception = new ExpenseException("USER_EXPENSE_EXCEPTION");
        }
        return exception;
    }

    public ExpenseSettlement getUserExpenseSettlement(User user) {

        ExpenseSettlement expenseSettlement = null;
        try {
            if(!userTransactionsMap.containsKey(user))
                return expenseSettlement;
            expenseSettlement = ExpenseSettlement.builder().expenseSettlementTransactions(userTransactionsMap.get(user)).build();
        } catch (Exception e) {
            log.error("Error in getting user expense settlement : {}", e);
        }
        return expenseSettlement;
    }
}
