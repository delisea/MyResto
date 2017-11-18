#Ajout resto 1
#response=$(curl -s -I POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"string\", \"address\": \"string\", \"url_img\": \"string\", \"tel_number\": \"string\", \"email\": \"string\"}")
#res=`echo -e $response | grep HTTP/1.1 | awk {'print $2'}`
#echo -e $response
#if [ $res = 200 ]
#then
#	echo -e "Error $res on $1"
#fi


### Scénario de test pour le filtrage d'un restaurant

#	Ajout d'un restaurant (nom = "resto1")
idResto1=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"resto1\", \"address\": \"string\", \"url_img\": \"string\", \"tel_number\": \"string\", \"email\": \"string\"}")
idResto1=$(echo -e $idResto1 | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idResto1 = $idResto1 \n"

#	Ajout d'un restaurant (nom = "resto2")
idResto2=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"resto2\", \"address\": \"string\", \"url_img\": \"string\", \"tel_number\": \"string\", \"email\": \"string\"}")
idResto2=$(echo -e $idResto2 | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idResto2 = $idResto2 \n"

#	Ajout d'un restaurant (nom = "resto3")
idResto3=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"resto3\", \"address\": \"string\", \"url_img\": \"string\", \"tel_number\": \"string\", \"email\": \"string\"}")
idResto3=$(echo -e $idResto3 | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idResto3 = $idResto3 \n"

# 	Ajout d'une table de 5 places non bougeable sur resto1 
echo -e "\nAjout d'une table de 5 places non bougeable sur resto1 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: "+$idResto1 -H "Content-Type: application/json" -d "{ \"number\": 1, \"id\": 0, \"places\": 5, \"movable\": false}")

#	Ajout d'une table de 2 places bougeable sur resto1
echo -e "\nAjout d'une table de 2 places bougeable sur resto1 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: "+$idResto1 -H "Content-Type: application/json" -d "{ \"number\": 2, \"id\": 0, \"places\": 2, \"movable\": true}")

#	Ajout d'une table de 3 places bougeable sur resto1
echo -e "\nAjout d'une table de 2 places bougeable sur resto1 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: "+$idResto1 -H "Content-Type: application/json" -d "{ \"number\": 2, \"id\": 0, \"places\": 3, \"movable\": true}")


#	Ajout d'une table de 7 places non bougeable sur resto2
echo -e "\nAjout d'une table de 2 places non bougeable sur resto2 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: "+$idResto2 -H "Content-Type: application/json" -d "{ \"number\": 2, \"id\": 0, \"places\": 7, \"movable\": false}")


#	Ajout d'une table de 2 places non bougeable sur resto2
echo -e "\nAjout d'une table de 2 places non bougeable sur resto2 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: "+$idResto2 -H "Content-Type: application/json" -d "{ \"number\": 2, \"id\": 0, \"places\": 2, \"movable\": false}")

# 	Ajout de la disponibilité MONDAY-MORNING sur resto1
echo -e "\nAjout de la disponibilité MONDAY-MORNING sur resto1 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: "+$idResto1 -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"MORNING\", \"day\": \"MONDAY\"}")

#	Ajout de la disponibilité MONDAY-EVENING sur resto1
echo -e "\nAjout de la disponibilité MONDAY-EVENING sur resto1 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: "+$idResto1 -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"EVENING\", \"day\": \"MONDAY\"}")

#	Ajout de la disponibilité TUESDAY-MIDDAY sur resto2
echo -e "\nAjout de la disponibilité TUESDAY-MIDDAY sur resto2 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: "+$idResto2 -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"MIDDAY\", \"day\": \"TUESDAY\"}")

#	Ajout de la disponibilité SUNDAY-NIGHT sur resto2
echo -e "\nAjout de la disponibilité SUNDAY-NIGHT sur resto2 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: "+$idResto2 -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"NIGHT\", \"day\": \"SUNDAY\"}")

#	Ajout de la spécialité ITALIAN sur resto1
echo -e "\nAjout de la spécialité ITALIAN sur resto1 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/specialities" -H "accept: application/json" -H "restaurant_id: "+$idResto1 -H "Content-Type: application/json" -d "{ \"id\": 0, \"speciality_label\": \"ITALIAN\"}")

#	Ajout de la spécialité CHINESE sur resto1
echo -e "\nAjout de la spécialité CHINESE sur resto1 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/specialities" -H "accept: application/json" -H "restaurant_id: "+$idResto1 -H "Content-Type: application/json" -d "{ \"id\": 0, \"speciality_label\": \"CHINESE\"}")

#	Ajout de la spécialité JAPANESE sur resto1
echo -e "\nAjout de la spécialité JAPANESE sur resto1 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/specialities" -H "accept: application/json" -H "restaurant_id: "+$idResto1 -H "Content-Type: application/json" -d "{ \"id\": 0, \"speciality_label\": \"JAPANESE\"}")

#	Ajout de la spécialité CHINESE sur resto2
echo -e "\nAjout de la spécialité CHINESE sur resto2 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/specialities" -H "accept: application/json" -H "restaurant_id: "+$idResto2 -H "Content-Type: application/json" -d "{ \"id\": 0, \"speciality_label\": \"CHINESE\"}")

#	Ajout de la spécialité JAPANESE sur resto2
echo -e "\nAjout de la spécialité JAPANESE sur resto2 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/specialities" -H "accept: application/json" -H "restaurant_id: "+$idResto2 -H "Content-Type: application/json" -d "{ \"id\": 0, \"speciality_label\": \"JAPANESE\"}")

#	Filtrage (aucun filtre) => Ressort resto1 + resto2 + resto3
echo -e "\nFiltrage (aucun filtre) => Ressort resto1 + resto2 + resto3 \n"
echo -e $(curl --silent GET "http://localhost:8080/javaee7-angular/resources/restaurants/search" -H "accept: application/json")

#	Filtrage (disponibility=MORNING,MIDDAY ; day=MONDAY) => Ressort resto1
echo -e "\nFiltrage (disponibility=MORNING,MIDDAY ; day=MONDAY) => Ressort resto1 \n"
echo -e $(curl --silent GET "http://localhost:8080/javaee7-angular/resources/restaurants/search?disponibility=MORNING,MIDDAY&day=MONDAY" -H "accept: application/json")

#	Filtrage (day=SUNDAY) => Ressort resto2
echo -e "\nFiltrage (day=SUNDAY) => Ressort resto2 \n"
echo -e $(curl --silent GET "http://localhost:8080/javaee7-angular/resources/restaurants/search?disponibility=&day=SUNDAY" -H "accept: application/json")

#	Filtrage (disponibility=NIGHT ; day=MONDAY,TUESDAY,SUNDAY) => Ressort resto2
echo -e "\nFiltrage (disponibility=NIGHT ; day=MONDAY,TUESDAY,SUNDAY) => Ressort resto2 \n"
echo -e $(curl --silent GET "http://localhost:8080/javaee7-angular/resources/restaurants/search?disponibility=NIGHT&day=MONDAY,TUESDAY,SUNDAY" -H "accept: application/json")

#	Filtrage (day=MONDAY,TUESDAY : speciality = ITALIAN) => Ressort resto1
echo -e "\nFiltrage (day=MONDAY,TUESDAY : speciality = ITALIAN) => Ressort resto1 \n"
echo -e $(curl --silent GET "http://localhost:8080/javaee7-angular/resources/restaurants/search?disponibility=&day=MONDAY,TUESDAY&speciality=ITALIAN" -H "accept: application/json")

#	Filtrage (day=MONDAY,TUESDAY : speciality = JAPANESE) => Ressort resto1 et resto2
echo -e "\nFiltrage (day=MONDAY,TUESDAY : speciality = JAPANESE) => Ressort resto1 et resto2 \n"
echo -e $(curl --silent GET "http://localhost:8080/javaee7-angular/resources/restaurants/search?disponibility=&day=MONDAY,TUESDAY&speciality=JAPANESE" -H "accept: application/json")

#	Filtrage (day=MONDAY : speciality = JAPANESE) => Ressort resto1
echo -e "\nFiltrage (day=MONDAY : speciality = JAPANESE) => Ressort resto1 \n"
echo -e $(curl --silent GET "http://localhost:8080/javaee7-angular/resources/restaurants/search?disponibility=&day=MONDAY&speciality=JAPANESE" -H "accept: application/json")

#	Filtrage (day=WEDNESDAY : speciality = JAPANESE) => Ressort rien
echo -e "\nFiltrage (day=WEDNESDAY : speciality = JAPANESE) => Ressort rien \n"
echo -e $(curl --silent GET "http://localhost:8080/javaee7-angular/resources/restaurants/search?disponibility=&day=WEDNESDAY&speciality=JAPANESE" -H "accept: application/json")

#	Filtrage (day=TUESDAY : speciality = ITALIAN) => Ressort rien
echo -e "\nFiltrage (day=TUESDAY : speciality = ITALIAN) => Ressort rien \n"
echo -e $(curl --silent GET "http://localhost:8080/javaee7-angular/resources/restaurants/search?disponibility=&day=TUESDAY&speciality=ITALIAN" -H "accept: application/json")

#	Filtrage (nbCouverts=10) => Ressort resto1
echo -e "\nFiltrage (nbCouverts=10) => Ressort resto1 \n"
echo -e $(curl --silent GET "http://localhost:8080/javaee7-angular/resources/restaurants/search?nbCouverts=10" -H "accept: application/json")

#	Filtrage (nbCouverts=2) => Ressort resto1 et resto 2
echo -e "\nFiltrage (nbCouverts=2) => Ressort resto1 et resto2 \n"
echo -e $(curl --silent GET "http://localhost:8080/javaee7-angular/resources/restaurants/search?nbCouverts=2" -H "accept: application/json")

#	Filtrage (nbCouverts=12) => Ressort rien
echo -e "\nFiltrage (nbCouverts=12) => Ressort rien \n"
echo -e $(curl --silent GET "http://localhost:8080/javaee7-angular/resources/restaurants/search?nbCouverts=12" -H "accept: application/json")

#	Suppression restaurant resto1 => OK (Si on met pas -X il ne supprime pas) 
echo -e "\nSuppression restaurant resto1 => NO CONTENT \n"
echo -e $(curl -X DELETE "http://localhost:8080/javaee7-angular/resources/restaurants/"+$idResto1 -H "accept: application/json")

#	Suppression restaurant resto2 => OK (Si on met pas -X il ne supprime pas)
echo -e "\nSuppression restaurant resto2 => NO CONTENT \n"
echo -e $(curl -X DELETE "http://localhost:8080/javaee7-angular/resources/restaurants/"+$idResto2 -H "accept: application/json")

#	Suppression restaurant resto3 => OK (Si on met pas -X il ne supprime pas)
echo -e "\nSuppression restaurant resto3 => NO CONTENT \n"
echo -e $(curl -X DELETE "http://localhost:8080/javaee7-angular/resources/restaurants/"+$idResto3 -H "accept: application/json")

###