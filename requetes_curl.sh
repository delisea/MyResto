#Test filtrage par disponibilité

#Ajout resto 1
curl -X POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"string\", \"address\": \"string\", \"url_img\": \"string\", \"tel_number\": \"string\", \"email\": \"string\"}"

#Ajout Dispo morning sur resto 1
curl -X POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: 1" -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"MORNING\"}"

#Ajout Dispo evening sur resto 1
curl -X POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: 1" -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"EVENING\"}" 

#Ajout Speciality sur resto 1 ITALIAN
curl -X POST "http://localhost:8080/javaee7-angular/resources/specialities" -H "accept: application/json" -H "restaurant_id: 1" -H "Content-Type: application/json" -d "{ \"id\": 0, \"speciality_label\": \"ITALIAN\"}"

#Ajout Speciality sur resto 2 CHINESE
curl -X POST "http://localhost:8080/javaee7-angular/resources/specialities" -H "accept: application/json" -H "restaurant_id: 2" -H "Content-Type: application/json" -d "{ \"id\": 0, \"speciality_label\": \"CHINESE\"}"

#Ajout resto 2
curl -X POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"string2\", \"address\": \"string\", \"url_img\": \"string\", \"tel_number\": \"string\", \"email\": \"string\"}"

#Ajout dispo evening sur resto 2
curl -X POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: 2" -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"EVENING\"}" 

#Requête de filtrage searchResto avec disponibility = MORNING > Sort resto 1
curl -X GET "http://localhost:8080/javaee7-angular/resources/restaurants/search?disponibility=MORNING&speciality=ITALIAN" -H "accept: application/json"
