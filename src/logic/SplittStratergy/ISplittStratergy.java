package logic.SplittStratergy;

import models.Expense;
import models.Transaction;

import java.util.List;

public interface ISplittStratergy {
    List<Transaction> splitExpense(Expense expense);
}
