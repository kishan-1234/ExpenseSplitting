package service;

import exceptions.ExpenseException;
import models.Expense;
import models.ExpenseSettlement;
import models.Group;
import models.User;
import repository.UserExpenseRepository;

public class ExpenseSplitServiceImpl implements ExpenseSplittingService{

    private static ExpenseSplitServiceImpl expenseSplitService;

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

        return UserExpenseRepository.getInstance().addUserExpense(expense);
    }

    @Override
    public ExpenseSettlement getExpenseSettlement(User user) {

        return UserExpenseRepository.getInstance().getUserExpenseSettlement(user);
    }
}
