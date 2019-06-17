MoneyManager

1 - Considerations:

* There is two endpoins [POST] /api/transcation/ which will insert a new transaction and [GET] /api/balances/retrive/<userId> which will retrieve a specific user balance.
* Since there's no create endpoints, users can be created by altering the number in AccountSeed.class -> seedAccounts
* AccountSeed have only a @PostConstruct method which means that he will be called only once, just after the bean's initialization
* i18n/Message is a class with purpose to work as a ResourceBundle which will fetch the code from resources/messages.properties
* Initialy there is 15 account's created within AccountSeed, with users from id 0 to id 14

2 - Execution:

To compile & run tests: mvn clean install

To run in localhost 8080: mvn spring-boot:run

3 - Example requests:

[Insert Transaction]

curl -X POST -H "Content-Type: application/json" -d  '{"userId":1,"amount":300}' http://localhost:8080/api/transaction/

{"message":"Success.","data":{"userId":1,"amount":300}}

The response is configured to return the POST request as "data" field.

[Retrieve User Balance]

curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/balances/retrieve/1

{"message":"Success.","data":{"userId":1,"balance":300}}



