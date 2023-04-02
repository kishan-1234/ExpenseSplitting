import logic.SplittStratergy.PercentageSplitStratergy;
import logic.SplittStratergy.ISplittStratergy;
import logic.SplittStratergy.SplitStratergy;
import lombok.extern.slf4j.Slf4j;
import models.Expense;
import models.Transaction;
import models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ExpenseSplittingApplication {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Expense expense = new Expense();
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
        payingUsers.put(user1, 100.0);
        payingUsers.put(user2, 100.0);
//        payingUsers.put(user3, 125.0);
        Map<User,Double> payeeUsers = new HashMap<User, Double>();
        payeeUsers.put(user1, 10.0);
        payeeUsers.put(user2, 20.0);
        payeeUsers.put(user3, 30.0);
        payeeUsers.put(user4, 40.0);
//        payeeUsers.put(user5, 20.0);
        expense.setPayingUsersMap(payingUsers);
        expense.setPayeeUsersMap(payeeUsers);

        List<Transaction> transactions = SplitStratergy.getInstance().splitExpense(expense);
        transactions.stream().forEach(transaction -> {
            log.info("PayBy: {}, PaidTo: {}, Amount: {}", transaction.getPaidByUser().getId(), transaction.getPaidToUser().getId(), transaction.getAmount());
        });
    }
}