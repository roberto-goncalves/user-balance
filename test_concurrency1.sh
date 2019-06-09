while true
do
   curl -X POST -H "Content-Type: application/json" -d  '{"userId":1,"amount":300}' http://localhost:8080/insert
   sleep 2
done
