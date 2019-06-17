while true
do
   curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/balances/retrieve/1
   sleep 0.5
done
