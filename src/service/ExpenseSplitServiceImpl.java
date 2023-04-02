package service;

import exceptions.ExpenseException;
import models.Expense;
import models.ExpenseSettlement;
import models.Group;
import models.User;

public class ExpenseSplitServiceImpl implements ExpenseSplittingService{

    @Override
    public ExpenseException addExpense(User user, Expense expense) {


        return null;
    }

    @Override
    public ExpenseException addGroupExpense(User user, Expense expense, Group group) {
        return null;
    }

    @Override
    public ExpenseSettlement getExpenseSettlement(User user) {
        return null;
    }

    @Override
    public ExpenseSettlement getGroupExpenseSettlement(Group group) {
        return null;
    }

    @Override
    public ExpenseSettlement getUserGroupExpenseSettlement(User user, Group group) {
        return null;
    }
}
