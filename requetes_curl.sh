#Ajout resto 1
#response=$(curl -s -I POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"string\", \"address\": \"string\", \"url_img\": \"string\", \"tel_number\": \"string\", \"email\": \"string\"}")
#res=`echo $response | grep HTTP/1.1 | awk {'print $2'}`
#echo $response
#if [ $res = 200 ]
#then
#	echo "Error $res on $1"
#fi

#Ajout resto 1
curl -X POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"resto 1\", \"address\": \"string\", \"url_img\": \"string\", \"tel_number\": \"string\", \"email\": \"string\"}"

#Ajout Table (5 places) sur resto 1
curl -X POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: 1" -H "Content-Type: application/json" -d "{ \"number\": 1, \"id\": 0, \"places\": 5, \"movable\": true}"

#Ajout Dispo morning sur resto 1
curl -X POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: 1" -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"MORNING\"}"

#Ajout Dispo evening sur resto 1
curl -X POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: 1" -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"EVENING\"}" 

#Ajout Speciality sur resto 1 ITALIAN
curl -X POST "http://localhost:8080/javaee7-angular/resources/specialities" -H "accept: application/json" -H "restaurant_id: 1" -H "Content-Type: application/json" -d "{ \"id\": 0, \"speciality_label\": \"ITALIAN\"}"

#Ajout Speciality sur resto 2 CHINESE
curl -X POST "http://localhost:8080/javaee7-angular/resources/specialities" -H "accept: application/json" -H "restaurant_id: 2" -H "Content-Type: application/json" -d "{ \"id\": 0, \"speciality_label\": \"CHINESE\"}"

#Ajout resto 2
curl -X POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"resto 2\", \"address\": \"string\", \"url_img\": \"string\", \"tel_number\": \"string\", \"email\": \"string\"}"

#Ajout Table (3 places) sur resto 2
curl -X POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: 2" -H "Content-Type: application/json" -d "{ \"number\": 1, \"id\": 0, \"places\": 3, \"movable\": true}"

#Ajout dispo evening sur resto 2
curl -X POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: 2" -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"EVENING\"}" 

#Requête de filtrage searchResto avec disponibility = MORNING > Sort resto 1
curl -X GET "http://localhost:8080/javaee7-angular/resources/restaurants/search?disponibility=MORNING&speciality=ITALIAN&nbCouverts=1" -H "accept: application/json"

#Requete de filtrage ---> Pas de filtrage
curl -X GET "http://localhost:8080/javaee7-angular/resources/restaurants/search?nbCouverts=0" -H "accept: application/json"