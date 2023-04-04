# ExpenseSplitting - LLD (Low Level Design)

Requirements:

1. User should able to add an expense, add members(users) associated with the expense. 
2. Multiple users can pay for an expense, multiple users can be part of an expense.
3. Users can track the total amount they have lent/borrowed.
3. Expense can be splitted by below types:
        a. SPLIT EQUALLY Splitting the amount equally into all members of a group Example 4000 split equally among 8 persons equals 500 as due amount on each person
        b. SPLIT EXACTLY Exact Split distributes the amount to the exact given amount. Example 4000 divided exactly into two persons as 2500, 1500. First person owes 2500 and the second 1500 to the creditor
        c. SPLIT BY PERCENTAGE Split the given amount as per the given percentage values Example 4000 divided among 4 in the percentage 10%, 20%, 20% and 50% would mean the first user owe an amount of 400, second and third would owe 800 each and the fourth person would owe a total of 2000 to the creditor.
        
To run this project:

1. Clone it
2. Run this command: $ mvn clean install
   Above command will download all dependencies from maven central
3. Run main class - ExpenseSplittingApplication
