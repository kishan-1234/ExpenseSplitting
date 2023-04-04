package logic.SplittStratergy;

import models.Expense;
import models.Transaction;

import java.util.List;

public class SplitStratergy {

    private static SplitStratergy splitStratergy;
    private static EqualSplitStratergy equalSplitStratergy = EqualSplitStratergy.getInstance();
    private static ExactSplitStratergy exactSplitStratergy = ExactSplitStratergy.getInstance();
    private static PercentageSplitStratergy percentageSplitStratergy = PercentageSplitStratergy.getInstance();

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
                splittStratergy = exactSplitStratergy;
                break;
            case EQUAL_SPLIT:
                splittStratergy = equalSplitStratergy;
                break;
            case PERCENTAGE_SPLIT:
                splittStratergy = percentageSplitStratergy;
                break;
        }
        return splittStratergy.splitExpense(expense);
    }
}
