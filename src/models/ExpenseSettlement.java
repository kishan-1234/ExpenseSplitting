package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Data
@SuperBuilder
@AllArgsConstructor
public class ExpenseSettlement {

    Double balanceAmount;
    List<Transaction> expenseSettlementTransactions;
}
