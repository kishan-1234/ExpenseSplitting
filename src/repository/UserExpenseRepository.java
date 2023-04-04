package repository;

import exceptions.ExpenseException;
import logic.ExpenseCalculator;
import logic.SplittStratergy.SplitStratergy;
import lombok.extern.slf4j.Slf4j;
import models.*;

import java.util.*;

@Slf4j
public class UserExpenseRepository {

    private static UserExpenseRepository userExpenseRepository;
    private static SplitStratergy splitStratergy = SplitStratergy.getInstance();

    private static Map<User, ExpenseSettlement> userBalanceMap;
    static {
        userBalanceMap = new HashMap<>();
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
            List<Transaction> transactions = splitStratergy.splitExpense(expense);
            transactions.stream().forEach(transaction -> {
                User payingUser = transaction.getPaidByUser();
                User payeeUser = transaction.getPaidToUser();
                addUserBalance(payingUser, payeeUser, AmountType.PAID, transaction.getAmount());
                addUserBalance(payeeUser, payingUser, AmountType.BORROWED, -transaction.getAmount());
            });
        } catch (Exception e) {
            log.error("Error in adding user expense : {}", e);
            exception = new ExpenseException("USER_EXPENSE_EXCEPTION");
        }
        return exception;
    }

    private void addUserBalance(User user, User balanceUser, AmountType amountType, Double amount) {

        if (!this.userBalanceMap.containsKey(user)) {
            this.userBalanceMap.put(user, new ExpenseSettlement());
        }
        ExpenseSettlement expenseSettlement = userBalanceMap.get(user);
        expenseSettlement.getExpenseSettlementBalances().add(Balance.builder()
                .user(balanceUser)
                .amountType(amountType)
                .balanceAmount(amount)
                .build());
        expenseSettlement.setBalanceAmount(expenseSettlement.getBalanceAmount()+amount);
        userBalanceMap.put(user, expenseSettlement);
    }

    public ExpenseSettlement getUserExpenseSettlement(User user) {

        ExpenseSettlement expenseSettlement = null;
        try {
            if(!userBalanceMap.containsKey(user))
                return expenseSettlement;
            expenseSettlement = userBalanceMap.get(user);
        } catch (Exception e) {
            log.error("Error in getting user expense settlement : {}", e);
        }
        return expenseSettlement;
    }
}
