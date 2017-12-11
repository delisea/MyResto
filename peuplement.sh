#!/bin/bash

#	Ajout d'un restaurant (nom = "resto1")
idResto1=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"resto1\", \"address\": \"string\", \"url_img\": \"string\", \"tel_number\": \"string\", \"email\": \"string\", \"latitude\": 45.1871598, \"longitude\": 5.7367073}")
idResto1=$(echo -e $idResto1 | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idResto1 = $idResto1 \n"

#	Ajout d'un restaurant (nom = "resto2")
idResto2=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"resto2\", \"address\": \"string\", \"url_img\": \"string\", \"tel_number\": \"string\", \"email\": \"string\", \"latitude\": 45.1855057, \"longitude\": 5.7405284}")
idResto2=$(echo -e $idResto2 | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idResto2 = $idResto2 \n"

#	Ajout d'un restaurant (nom = "resto3")
idResto3=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"resto3\", \"address\": \"string\", \"url_img\": \"string\", \"tel_number\": \"string\", \"email\": \"string\", \"latitude\": 45.1845394, \"longitude\": 5.7421717}")
idResto3=$(echo -e $idResto3 | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idResto3 = $idResto3 \n"

#	Ajout d'un restaurant (nom = "Hippopotamus Gières")
idHippo=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"Hippopotamus Gieres\", \"address\": \"string\", \"url_img\": \"https://i.ytimg.com/vi/uLF1e4wziTA/maxresdefault.jpg\", \"tel_number\": \"string\", \"email\": \"string\", \"latitude\": 45.1896624, \"longitude\": 5.7766054 }")
idHippo=$(echo -e $idHippo | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idHippo = $idHippo \n"

#	Ajout d'un restaurant (nom = "MacDo de l'aigle")
idMacDo=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"MacDo de l'aigle\", \"address\": \"string\", \"url_img\": \"http://static.pix-geeks.com/2016/03/mcdonalds-burger-king-troll-twitter-42676-1.jpg\", \"tel_number\": \"string\", \"email\": \"string\", \"latitude\": 45.1830954, \"longitude\": 5.7155548 }")
idMacDo=$(echo -e $idMacDo | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idMacDo = $idMacDo \n"

#	Ajout d'un restaurant (nom = "La tour Eiffel")
idEiffel=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"La tour Eiffel\", \"address\": \"string\", \"url_img\": \"http://img0.svstatic.com/tour-eiffel/la-tour-eiffel-aux-couleurs-de-la-france-en-hommage-aux-victimes-des-attentats_12518_wide.jpg\", \"tel_number\": \"string\", \"email\": \"string\", \"latitude\": 48.8583701, \"longitude\": 2.2922873 }")
idEiffel=$(echo -e $idEiffel | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idEiffel = $idEiffel \n"

# 	Ajout d'une table de 10 places non bougeable sur La tour Eiffel 
echo -e "\nAjout d'une table de 5 places non bougeable sur resto1 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: "+$idEiffel -H "Content-Type: application/json" -d "{ \"number\": 1, \"id\": 0, \"places\": 10, \"movable\": false}")

# 	Ajout d'une table de 7 places non bougeable sur Hippopotamus Gières
echo -e "\nAjout d'une table de 5 places non bougeable sur resto1 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: "+$idHippo -H "Content-Type: application/json" -d "{ \"number\": 1, \"id\": 0, \"places\": 7, \"movable\": false}")

# 	Ajout d'une table de 3 places bougeable sur macDo de l'aigle
echo -e "\nAjout d'une table de 5 places non bougeable sur resto1 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: "+$idMacDo -H "Content-Type: application/json" -d "{ \"number\": 1, \"id\": 0, \"places\": 3, \"movable\": true}")

# 	Ajout d'une table de 6 places non bougeable sur macDo de l'aigle
echo -e "\nAjout d'une table de 5 places non bougeable sur resto1 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: "+$idMacDo -H "Content-Type: application/json" -d "{ \"number\": 1, \"id\": 0, \"places\": 6, \"movable\": false}")

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

# 	Ajout de la disponibilité WEDNESDAY-MORNING sur Eiffel
echo -e "\nAjout de la disponibilité MONDAY-MORNING sur resto1 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: "+$idEiffel -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"MORNING\", \"day\": \"WEDNESDAY\"}")

# 	Ajout de la disponibilité THURSDAY-EVENING sur Hippopotamus
echo -e "\nAjout de la disponibilité THURSDAY-EVENING sur Hippopotamus \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: "+$idHippo -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"EVENING\", \"day\": \"THURSDAY\"}")

# 	Ajout de la disponibilité THURSDAY-MORNING sur Hippopotamus
echo -e "\nAjout de la disponibilité THURSDAY-MORNING sur Hippopotamus \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: "+$idHippo -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"MORNING\", \"day\": \"THURSDAY\"}")

# 	Ajout de la disponibilité FRIDAY-NIGHT sur MacDo
echo -e "\nAjout de la disponibilité FRIDAY-NIGHT sur MacDo\n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: "+$idMacDo -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"NIGHT\", \"day\": \"FRIDAY\"}")

# 	Ajout de la disponibilité SATURDAY-MORNING sur MacDo
echo -e "\nAjout de la disponibilité FRIDAY-NIGHT sur MacDo\n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: "+$idMacDo -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"MORNING\", \"day\": \"SATURDAY\"}")

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

#	Ajout de la spécialité GRILS sur Hippopotamus
echo -e "\nAjout de la spécialité GRILS sur Hippopotamus \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/specialities" -H "accept: application/json" -H "restaurant_id: "+$idHippo -H "Content-Type: application/json" -d "{ \"id\": 0, \"speciality_label\": \"GRILS\"}")

#	Ajout de la spécialité FRENCH sur Eiffel
echo -e "\nAjout de la spécialité FRENCH sur Eiffel \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/specialities" -H "accept: application/json" -H "restaurant_id: "+$idEiffel -H "Content-Type: application/json" -d "{ \"id\": 0, \"speciality_label\": \"FRENCH\"}")

#	Ajout de la spécialité ITALIAN sur Eiffel
echo -e "\nAjout de la spécialité ITALIAN sur Eiffel \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/specialities" -H "accept: application/json" -H "restaurant_id: "+$idEiffel -H "Content-Type: application/json" -d "{ \"id\": 0, \"speciality_label\": \"ITALIAN\"}")

#	Ajout de la spécialité FASTFOOD sur MacDo
echo -e "\nAjout de la spécialité GRILS sur Hippopotamus \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/specialities" -H "accept: application/json" -H "restaurant_id: "+$idMacDo -H "Content-Type: application/json" -d "{ \"id\": 0, \"speciality_label\": \"FASTFOOD\"}")

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

# Ajout du menu "menu du jour" sur resto1
echo -e "\nAjout du menu 'menu du jour' sur resto1 \n"
idMenu=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/menu" -H "accept: application/json" -H "restaurant_id: "+$idResto1 -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 15, \"name\": \"menu du jour\"}")
idMenu=$(echo -e $idMenu | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idMenu = $idMenu \n"

#Ajout du plat 'salade' de type STARTER sur 'menu du jour'
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/meal" -H "accept: application/json" -H "menu_id: "+$idMenu -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 3, \"type\": \"STARTER\", \"name\": \"salade\"}")

#Ajout du plat 'steack frite' de type MAINCOURSE sur 'menu du jour'
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/meal" -H "accept: application/json" -H "menu_id: "+$idMenu -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 10, \"type\": \"MAINCOURSE\", \"name\": \"steack frites\"}")

#Ajout du plat 'glace au chocolat' de type DESERT sur 'menu du jour'
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/meal" -H "accept: application/json" -H "menu_id: "+$idMenu -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 2, \"type\": \"DESERT\", \"name\": \"glace au chocolat\"}")