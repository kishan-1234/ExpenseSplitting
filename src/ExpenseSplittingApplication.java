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

    private static Expense expense;
    private static List<User> users;
    static {
        expense = new Expense();
        users = new ArrayList<>();
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
    }


    public static void main(String[] args) {
//        testEqualSplit();
//        testExactSplit();
        testPercentageSplit();
    }

    private static void testEqualSplit() {

        log.info("###################################### TEST EQUAL SPLIT START ######################################\n");
        expense.setExpenseSplitType(ExpenseSplitType.EQUAL_SPLIT);
        expense.setAmount(200.0);
        Map<User,Double> payingUsers = new HashMap<User, Double>();
        payingUsers.put(users.get(4), 10.0);
        payingUsers.put(users.get(1), 190.0);
        expense.setPayingUsersMap(payingUsers);

        ExpenseSplitServiceImpl.getInstance().addExpense(expense);
        logUserBalance(users.get(0));
        logUserBalance(users.get(1));
        logUserBalance(users.get(2));
        logUserBalance(users.get(3));
        logUserBalance(users.get(4));
        log.info("######################################  TEST EQUAL SPLIT END ######################################\n");
    }

    private static void testExactSplit() {

        log.info("###################################### TEST EXACT SPLIT START ######################################\n");
        expense.setExpenseSplitType(ExpenseSplitType.EXACT_SPLIT);
        expense.setAmount(200.0);
        Map<User,Double> payingUsers = new HashMap<User, Double>();
        payingUsers.put(users.get(4), 10.0);
        payingUsers.put(users.get(1), 190.0);
        expense.setPayingUsersMap(payingUsers);
        Map<User,Double> payeeUsers = new HashMap<User, Double>();
        payeeUsers.put(users.get(0), 20.0);
        payeeUsers.put(users.get(1), 30.0);
        payeeUsers.put(users.get(2), 25.0);
        payeeUsers.put(users.get(3), 25.0);
        payeeUsers.put(users.get(4), 100.0);
        expense.setPayeeUsersMap(payeeUsers);

        ExpenseSplitServiceImpl.getInstance().addExpense(expense);
        logUserBalance(users.get(0));
        logUserBalance(users.get(1));
        logUserBalance(users.get(2));
        logUserBalance(users.get(3));
        logUserBalance(users.get(4));
        log.info("######################################  TEST EXACT SPLIT END ######################################\n");
    }

    private static void testPercentageSplit() {

        log.info("###################################### TEST PERCENTAGE SPLIT START ######################################\n");
        expense.setExpenseSplitType(ExpenseSplitType.PERCENTAGE_SPLIT);
        expense.setAmount(100.0);
        Map<User,Double> payingUsers = new HashMap<User, Double>();
        payingUsers.put(users.get(0), 60.0);
        payingUsers.put(users.get(1), 40.0);
        expense.setPayingUsersMap(payingUsers);
        Map<User,Double> payeeUsers = new HashMap<User, Double>();
        payeeUsers.put(users.get(0), 20.0);
        payeeUsers.put(users.get(1), 30.0);
        payeeUsers.put(users.get(2), 10.0);
        payeeUsers.put(users.get(3), 15.0);
        payeeUsers.put(users.get(4), 25.0);
        expense.setPayeeUsersMap(payeeUsers);

        ExpenseSplitServiceImpl.getInstance().addExpense(expense);
        logUserBalance(users.get(0));
        logUserBalance(users.get(1));
        logUserBalance(users.get(2));
        logUserBalance(users.get(3));
        logUserBalance(users.get(4));
        log.info("######################################  TEST PERCENTAGE SPLIT END ######################################\n");
    }

    private static void logUserBalance(User user) {

        ExpenseSettlement expenseSettlement = ExpenseSplitServiceImpl.getInstance().getExpenseSettlement(user);
        log.info("Total balance amount for user: {} is : {} \n", user, expenseSettlement.getBalanceAmount());
        log.info("###################################### BALACES OF USER :{} ######################################", user);
        expenseSettlement.getExpenseSettlementBalances().stream().forEach(balance -> {
            log.info("Balance user : {}, AmountType : {}, Balance Amount : {}", balance.getUser(), balance.getAmountType(), balance.getBalanceAmount());
        });
        log.info("#######################################################################################################\n");
    }
}