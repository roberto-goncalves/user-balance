while true
do
   curl -X POST -H "Content-Type: application/json" -d  '{"userId":1,"amount":-150}' http://localhost:8080/insert
   sleep 0.5
done
