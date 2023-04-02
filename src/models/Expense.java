package models;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Expense {

    ExpenseSplitType expenseSplitType;
    List<User> users;
    Double amount;
    Map<User,Double> payingUsersMap;
    Map<User,Double> payeeUsersMap;
}
