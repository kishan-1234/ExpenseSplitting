package service;

import exceptions.ExpenseException;
import models.Expense;
import models.ExpenseSettlement;
import models.Group;
import models.User;
import repository.UserExpenseRepository;

public class ExpenseSplitServiceImpl implements ExpenseSplittingService{

    private static ExpenseSplitServiceImpl expenseSplitService;
    private static UserExpenseRepository userExpenseRepository = UserExpenseRepository.getInstance();

    private ExpenseSplitServiceImpl() {}

    public static ExpenseSplitServiceImpl getInstance() {
        if(expenseSplitService==null) {
            synchronized (ExpenseSplitServiceImpl.class) {
                if(expenseSplitService==null) {
                    expenseSplitService = new ExpenseSplitServiceImpl();
                }
            }
        }
        return expenseSplitService;
    }

    @Override
    public ExpenseException addExpense(Expense expense) {

        return userExpenseRepository.addUserExpense(expense);
    }

    @Override
    public ExpenseSettlement getExpenseSettlement(User user) {

        return userExpenseRepository.getUserExpenseSettlement(user);
    }
}
