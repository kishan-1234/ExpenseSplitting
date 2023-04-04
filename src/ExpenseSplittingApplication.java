import logic.SplittStratergy.PercentageSplitStratergy;
import logic.SplittStratergy.ISplittStratergy;
import logic.SplittStratergy.SplitStratergy;
import lombok.extern.slf4j.Slf4j;
import models.*;
import service.ExpenseSplitServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ExpenseSplittingApplication {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Expense expense = new Expense();
        expense.setExpenseSplitType(ExpenseSplitType.EQUAL_SPLIT);
        List<User> users = new ArrayList<User>();
        User user1 = User.builder().Id(1L).build();
        User user2 = User.builder().Id(2L).build();
        User user3 = User.builder().Id(3L).build();
        User user4 = User.builder().Id(4L).build();
        User user5 = User.builder().Id(5L).build();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        expense.setUsers(users);
        expense.setAmount(200.0);
        Map<User,Double> payingUsers = new HashMap<User, Double>();
        payingUsers.put(user4, 100.0);
        payingUsers.put(user3, 100.0);
//        payingUsers.put(user3, 125.0);
        Map<User,Double> payeeUsers = new HashMap<User, Double>();
        payeeUsers.put(user1, 40.0);
        payeeUsers.put(user2, 30.0);
        payeeUsers.put(user3, 20.0);
        payeeUsers.put(user4, 10.0);
//        payeeUsers.put(user5, 20.0);
        expense.setPayingUsersMap(payingUsers);
//        expense.setPayeeUsersMap(payeeUsers);

        ExpenseSplitServiceImpl.getInstance().addExpense(expense);
        ExpenseSettlement expenseSettlement = ExpenseSplitServiceImpl.getInstance().getExpenseSettlement(user4);
        expenseSettlement.getExpenseSettlementTransactions().stream().forEach(transaction -> {
            log.info("Paid By: {}, Paid To : {}, Amount : {}", transaction.getPaidByUser(), transaction.getPaidToUser(), transaction.getAmount());
        });
        expenseSettlement = ExpenseSplitServiceImpl.getInstance().getExpenseSettlement(user3);
        expenseSettlement.getExpenseSettlementTransactions().stream().forEach(transaction -> {
            log.info("Paid By: {}, Paid To : {}, Amount : {}", transaction.getPaidByUser(), transaction.getPaidToUser(), transaction.getAmount());
        });
    }
}