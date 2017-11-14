#Ajout resto 1
#response=$(curl -s -I POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"string\", \"address\": \"string\", \"url_img\": \"string\", \"tel_number\": \"string\", \"email\": \"string\"}")
#res=`echo $response | grep HTTP/1.1 | awk {'print $2'}`
#echo $response
#if [ $res = 200 ]
#then
#	echo "Error $res on $1"
#fi


### Scénario de test pour le filtrage d'un restaurant

#	Ajout d'un restaurant (nom = "resto1")
#	Ajout d'un restaurant (nom = "resto2")
#	Ajout d'un restaurant (nom = "resto3")
# 	Ajout d'une table de 5 places non bougeable sur resto1 
#	Ajout d'une table de 2 places bougeable sur resto1
#	Ajout d'une table de 2 places non bougeable sur resto2
# 	Ajout de la disponibilité MONDAY-MORNING sur resto1
#	Ajout de la disponibilité MONDAY-EVENING sur resto1
#	Ajout de la disponibilité TUESDAY-MIDDAY sur resto2
#	Ajout de la disponibilité SUNDAY-NIGHT sur resto2
#	Ajout de la spécialité ITALIAN sur resto1
#	Ajout de la spécialité CHINESE sur resto1
#	Ajout de la spécialité JAPANESE sur resto1
#	Ajout de la spécialité CHINESE sur resto2
#	Ajout de la spécialité JAPANESE sur resto2
#	Filtrage (aucun filtre) => Ressort tout
#	Filtrage (disponibility=MORNING,MIDDAY ; day=MONDAY) => Ressort resto1
#	Filtrage (day=SUNDAY) => Ressort resto2
#	Filtrage (disponibility=NIGHT ; day=MONDAY,TUESDAY,SUNDAY) => Ressort resto2
#	Filtrage (day=MONDAY,TUESDAY : speciality = ITALIAN) => Ressort resto1
#	Filtrage (day=MONDAY,TUESDAY : speciality = JAPANESE) => Ressort resto1 et resto2
#	Filtrage (day=MONDAY : speciality = JAPANESE) => Ressort resto1
#	Filtrage (day=WEDNESDAY : speciality = JAPANESE) => Ressort rien
#	Filtrage (day=TUESDAY : speciality = ITALIAN) => Ressort rien
#	Filtrage (nbCouverts=6) => Ressort resto1
#	Filtrage (nbCouverts=2) => Ressort resto1 et resto 2
#	Filtrage (nbCouverts=8) => Ressort rien
#	Suppression (id_restaurant=1) => OK
#	Suppression (id_restaurant=2) => OK

### 


#Ajout resto 1
curl -X POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"resto 1\", \"address\": \"string\", \"url_img\": \"string\", \"tel_number\": \"string\", \"email\": \"string\"}"

#Ajout Table (5 places) sur resto 1
curl -X POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: 1" -H "Content-Type: application/json" -d "{ \"number\": 1, \"id\": 0, \"places\": 5, \"movable\": true}"

#Ajout Dispo morning monday sur resto 1
curl -X POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: 1" -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"MORNING\", \"day\": \"MONDAY\"}"

#Ajout Dispo evening monday sur resto 1
curl -X POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: 1" -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"EVENING\", \"day\": \"MONDAY\"}" 

#Ajout Speciality sur resto 1 ITALIAN
curl -X POST "http://localhost:8080/javaee7-angular/resources/specialities" -H "accept: application/json" -H "restaurant_id: 1" -H "Content-Type: application/json" -d "{ \"id\": 0, \"speciality_label\": \"ITALIAN\"}"

#Ajout resto 2
curl -X POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"resto 2\", \"address\": \"string\", \"url_img\": \"string\", \"tel_number\": \"string\", \"email\": \"string\"}"

#Ajout Speciality sur resto 2 CHINESE
curl -X POST "http://localhost:8080/javaee7-angular/resources/specialities" -H "accept: application/json" -H "restaurant_id: 2" -H "Content-Type: application/json" -d "{ \"id\": 0, \"speciality_label\": \"CHINESE\"}"

#Ajout Table (3 places) sur resto 2
curl -X POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: 2" -H "Content-Type: application/json" -d "{ \"number\": 1, \"id\": 0, \"places\": 3, \"movable\": true}"

#Ajout dispo evening sur resto 2
curl -X POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: 2" -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"EVENING\"}" 

#Requête de filtrage searchResto avec disponibility = MORNING et MONDAY > Sort resto 1
curl -X GET "http://localhost:8080/javaee7-angular/resources/restaurants/search?disponibility=MORNING&day=MONDAY&speciality=ITALIAN&nbCouverts=1" -H "accept: application/json"

#Requete de filtrage ---> Pas de filtrage
curl -X GET "http://localhost:8080/javaee7-angular/resources/restaurants/search?nbCouverts=0" -H "accept: application/json"