package service;

import exceptions.ExpenseException;
import models.Expense;
import models.ExpenseSettlement;
import models.Group;
import models.User;

public interface ExpenseSplittingService {

    ExpenseException addExpense(Expense expense);
    ExpenseSettlement getExpenseSettlement(User user);
}
