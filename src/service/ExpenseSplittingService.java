package service;

import exceptions.ExpenseException;
import models.Expense;
import models.ExpenseSettlement;
import models.Group;
import models.User;

public interface ExpenseSplittingService {

    ExpenseException addExpense(User user, Expense expense);
    ExpenseException addGroupExpense(User user, Expense expense, Group group);
    ExpenseSettlement getExpenseSettlement(User user);
    ExpenseSettlement getGroupExpenseSettlement(Group group);
    ExpenseSettlement getUserGroupExpenseSettlement(User user, Group group);
}
