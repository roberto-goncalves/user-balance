while true
do
   curl -X POST -H "Content-Type: application/json" -d  '{"userId":1,"amount":-150}' http://localhost:8080/api/transaction/
   sleep 1
done
