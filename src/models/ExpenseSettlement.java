package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;


@Data
@SuperBuilder
public class ExpenseSettlement {

    Double balanceAmount;
    List<Balance> expenseSettlementBalances;

    public ExpenseSettlement() {
        this.balanceAmount = 0.0;
        this.expenseSettlementBalances = new ArrayList<>();
    }
}
