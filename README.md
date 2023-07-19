# assignmentForVegapay
Assignment - SDE

Every credit card customer is assigned credit limits for the usage of a credit card. We deal with two types of limits as defined below -
Account limit - overall account level credit limit. A customer cannot have more than this limit credit balance at any time.
Per transaction limit - limit for a transaction. A customer cannot make a single transaction of more than this amount.

An existing credit customer is often given a new credit limit offer based on the previous credit use or repayment history. For example - if a customer regularly repays his bill, we can increase his/her credit limit. We need to implement a small service to manage and execute limit offers for a customer. 
Account object for a customer will have following fields - account_id, customer_id, account_limit, per_transaction_limit, last_account_limit, last_per_transaction_limit, account_limit_update_time, per_transaction_limit_update_time

