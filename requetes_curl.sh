#Test filtrage par disponibilité
$err=false

#Ajout table
$response=curl -X POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"type\": 0, \"name\": \"22\", \"in\": \"string\"}"

#response=$(curl --write-out %{http_code} --silent --output /dev/null servername)

http_status=$(echo "$response" | grep HTTP |  awk '{print $2}')
echo $response
if[ $http_status != 500 ] then
	$err=true;
	echo "POST /tables failed";
endif



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
