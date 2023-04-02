package logic.SplittStratergy;

import models.Expense;
import models.Transaction;

import java.util.List;

public class SplitStratergy {

    private static SplitStratergy splitStratergy;

    public static SplitStratergy getInstance() {
        if(splitStratergy==null) {
            synchronized (SplitStratergy.class) {
                if(splitStratergy==null) {
                    splitStratergy = new SplitStratergy();
                }
            }
        }
        return splitStratergy;
    }


    public List<Transaction> splitExpense(Expense expense) {

        ISplittStratergy splittStratergy = null;
        switch (expense.getExpenseSplitType()) {
            case EXACT_SPLIT:
                splittStratergy = ExactSplitStratergy.getInstance();
                break;
            case EQUAL_SPLIT:
                splittStratergy = EqualSplitStratergy.getInstance();
                break;
            case PERCENTAGE_SPLIT:
                splittStratergy = PercentageSplitStratergy.getInstance();
                break;
        }
        return splittStratergy.splitExpense(expense);
    }
}
