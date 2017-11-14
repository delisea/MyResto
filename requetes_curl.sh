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
idResto1=$(curl -X POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"resto1\", \"address\": \"string\", \"url_img\": \"string\", \"tel_number\": \"string\", \"email\": \"string\"}")
idResto1=$(echo $idResto1 | cut -d':' -f 2 | cut -d',' -f 1)

#	Ajout d'un restaurant (nom = "resto2")
idResto2=$(curl -X POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"resto2\", \"address\": \"string\", \"url_img\": \"string\", \"tel_number\": \"string\", \"email\": \"string\"}")
idResto2=$(echo $idResto2 | cut -d':' -f 2 | cut -d',' -f 1)

#	Ajout d'un restaurant (nom = "resto3")
idResto3=$(curl -X POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"resto3\", \"address\": \"string\", \"url_img\": \"string\", \"tel_number\": \"string\", \"email\": \"string\"}")
idResto3=$(echo $idResto3 | cut -d':' -f 2 | cut -d',' -f 1)

# 	Ajout d'une table de 5 places non bougeable sur resto1 
curl -X POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: "+$idResto1 -H "Content-Type: application/json" -d "{ \"number\": 1, \"id\": 0, \"places\": 5, \"movable\": false}"

#	Ajout d'une table de 2 places bougeable sur resto1
curl -X POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: "+$idResto1 -H "Content-Type: application/json" -d "{ \"number\": 2, \"id\": 0, \"places\": 2, \"movable\": true}"

#	Ajout d'une table de 2 places non bougeable sur resto2
curl -X POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: "+$idResto2 -H "Content-Type: application/json" -d "{ \"number\": 2, \"id\": 0, \"places\": 2, \"movable\": false}"

# 	Ajout de la disponibilité MONDAY-MORNING sur resto1
curl -X POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: "+$idResto1 -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"MORNING\", \"day\": \"MONDAY\"}"

#	Ajout de la disponibilité MONDAY-EVENING sur resto1
curl -X POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: "+$idResto1 -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"EVENING\", \"day\": \"MONDAY\"}"

#	Ajout de la disponibilité TUESDAY-MIDDAY sur resto2
curl -X POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: "+$idResto2 -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"MIDDAY\", \"day\": \"TUESDAY\"}"

#	Ajout de la disponibilité SUNDAY-NIGHT sur resto2
curl -X POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: "+$idResto2 -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"NIGHT\", \"day\": \"SUNDAY\"}"

#	Ajout de la spécialité ITALIAN sur resto1
curl -X POST "http://localhost:8080/javaee7-angular/resources/specialities" -H "accept: application/json" -H "restaurant_id: "+$idResto1 -H "Content-Type: application/json" -d "{ \"id\": 0, \"speciality_label\": \"ITALIAN\"}"

#	Ajout de la spécialité CHINESE sur resto1
curl -X POST "http://localhost:8080/javaee7-angular/resources/specialities" -H "accept: application/json" -H "restaurant_id: "+$idResto1 -H "Content-Type: application/json" -d "{ \"id\": 0, \"speciality_label\": \"CHINESE\"}"

#	Ajout de la spécialité JAPANESE sur resto1
curl -X POST "http://localhost:8080/javaee7-angular/resources/specialities" -H "accept: application/json" -H "restaurant_id: "+$idResto1 -H "Content-Type: application/json" -d "{ \"id\": 0, \"speciality_label\": \"JAPANESE\"}"

#	Ajout de la spécialité CHINESE sur resto2
curl -X POST "http://localhost:8080/javaee7-angular/resources/specialities" -H "accept: application/json" -H "restaurant_id: "+$idResto2 -H "Content-Type: application/json" -d "{ \"id\": 0, \"speciality_label\": \"CHINESE\"}"

#	Ajout de la spécialité JAPANESE sur resto2
curl -X POST "http://localhost:8080/javaee7-angular/resources/specialities" -H "accept: application/json" -H "restaurant_id: "+$idResto2 -H "Content-Type: application/json" -d "{ \"id\": 0, \"speciality_label\": \"JAPANESE\"}"

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

#	Suppression restaurant resto1 => OK
curl -X DELETE "http://localhost:8080/javaee7-angular/resources/restaurants/"+$idResto1 -H "accept: application/json"
#	Suppression restaurant resto2 => OK
curl -X DELETE "http://localhost:8080/javaee7-angular/resources/restaurants/"+$idResto2 -H "accept: application/json"

###