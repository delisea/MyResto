#!/bin/bash


#---------------------------------- AJOUT DE RESTAURANTS --------------------------------#

#	Ajout d'un restaurant (nom = "Chez Le Pèr'Gras")

idResto1=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"Chez Le Per'Gras\", \"description\": \"Cette table propose un menu regional gastronomique dans un cadre elegant ou en terrasse avec vue sur la ville.\", \"address\": \"90 Chemin de la Bastille, 38000 Grenoble\", \"url_img\": \"http://www.pergras.com/uploads/2016/08/PG_contact-footer.jpg\", \"tel_number\": \"04 76 42 09 47\", \"email\": \"pergras.fr\", \"latitude\": 45.201539, \"longitude\": 5.725881}")

idResto1=$(echo -e $idResto1 | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idResto1 = $idResto1 \n"

#	Ajout d'un restaurant (nom = "Dragons Elysées")
idResto2=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"Dragons Elysees\", \"description\": \"Degustez des specialites asiatiques les pieds sur l'eau. A deux pas des champs Elysees.\", \"address\": \"11 Rue de Berri, 75008 Paris\", \"url_img\": \"https://u.tfstatic.com/restaurant_photos/725/37725/169/612/dragons-elysees-vue-de-la-salle-b421b.jpg\", \"tel_number\": \"01 42 89 85 10\", \"email\": \"dragonselysees.fr\", \"latitude\": 48.872473, \"longitude\": 2.303882}")
idResto2=$(echo -e $idResto2 | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idResto2 = $idResto2 \n"

#	Ajout d'un restaurant (nom = "L'Ardoise")
idResto3=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"L'Ardoise\", \"description\": \"Dans un cadre classique et chaleureux, de copieux plats regionaux et traditionnels, avec terrasse en ete.\", \"address\": \"2 Rue de Miribel, 38000 Grenoble\", \"url_img\": \"https://media.v3.grenoble-tourisme.com/photos/structure_471/2051181.jpg.1200x900_q85.jpg\", \"tel_number\": \"04 76 27 75 90\", \"email\": \"ardoise_123@hotmail.fr\", \"latitude\": 45.190454, \"longitude\": 5.725394}")
idResto3=$(echo -e $idResto3 | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idResto3 = $idResto3 \n"

#	Ajout d'un restaurant (nom = "Hippopotamus Gières")
idHippo=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"Hippopotamus Gieres\", \"description\": \"Chaine de restaurants au decor noir et rouge, specialises en grillades de viandes avec service continu 7j/7.\", \"address\": \"1 Allee du Perou, 38610 Gieres\", \"url_img\": \"https://i.ytimg.com/vi/uLF1e4wziTA/maxresdefault.jpg\", \"tel_number\": \"04 76 61 61 10\", \"email\": \"hyppopotamus@hotmail.fr\", \"latitude\": 45.1896624, \"longitude\": 5.7766054 }")
idHippo=$(echo -e $idHippo | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idHippo = $idHippo \n"

#	Ajout d'un restaurant (nom = "MacDo de l'aigle")
idMacDo=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"MacDo de l'aigle\", \"description\": \"Fast food reconnu. Sur place ou a emporter. Nouveau : CBO est de retour.\", \"address\": \" 99 Cours Jean Jaures, 38000 Grenoble\", \"url_img\": \"https://ws.mcdonalds.fr/media/20/bf/e6/20bfe64a9cc5d60e387318306526924e65b5a587\", \"tel_number\": \"0476841508\", \"email\": \"mcdonalds.fr\", \"latitude\": 45.183095, \"longitude\": 5.717749 }")
idMacDo=$(echo -e $idMacDo | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idMacDo = $idMacDo \n"

#	Ajout d'un restaurant (nom = "58 Tour Eiffel Restaurant")
idEiffel=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"58 Tour Eiffel Restaurant\", \"description\": \"Vue imprenable sur Paris. Restaurant gastronomique inimitable dans un cadre idyllique.\", \"address\": \"Tour Eiffel, Champ de Mars, 75007 Paris\", \"url_img\": \"http://img0.svstatic.com/tour-eiffel/la-tour-eiffel-aux-couleurs-de-la-france-en-hommage-aux-victimes-des-attentats_12518_wide.jpg\", \"tel_number\": \"0 825 56 66 62\", \"email\": \"58toureiffel.fr\", \"latitude\": 48.858684, \"longitude\": 2.294250 }")
idEiffel=$(echo -e $idEiffel | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idEiffel = $idEiffel \n"

#	Ajout d'un restaurant (nom = "Fouquet's")
idFouquets=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/restaurants" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 0, \"name\": \"Fouquet's\", \"description\": \"Ce restaurant repute au cadre raffine propose des plats francais creatifs et plus de 350 vins et champagnes.\", \"address\": \"99 Av. des Champs-Élysées, 75008 Paris\", \"url_img\": \"https://www.casinosbarriere.com/content/dam/casinos/sites_locaux/toulouse/restaurants/fouquets/carousel/image_1/carousel_fouquets_toulouse_desktop.jpg\", \"tel_number\": \"01 40 69 60 50\", \"email\": \"58toureiffel.fr\", \"latitude\": 48.871525, \"longitude\": 2.301181 }")
idFouquets=$(echo -e $idFouquets | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idFouquets = $idFouquets \n"

#---------------------------------- AJOUT DE TABLES --------------------------------#

# 	Ajout d'une table de 10 places non bougeable sur La tour Eiffel 
echo -e "\nAjout d'une table de 10 places non bougeable sur La tour Eiffel \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: "+$idEiffel -H "Content-Type: application/json" -d "{ \"number\": 1, \"id\": 0, \"places\": 10, \"movable\": false}")

# 	Ajout d'une table de 7 places non bougeable sur Hippopotamus Gières
echo -e "\nAjout d'une table de 7 places non bougeable sur Hippopotamus Gières \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: "+$idHippo -H "Content-Type: application/json" -d "{ \"number\": 1, \"id\": 0, \"places\": 7, \"movable\": false}")

# 	Ajout d'une table de 3 places bougeable sur macDo de l'aigle
echo -e "\nAjout d'une table de 3 places bougeable sur macDo de l'aigle \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: "+$idMacDo -H "Content-Type: application/json" -d "{ \"number\": 1, \"id\": 0, \"places\": 3, \"movable\": true}")

# 	Ajout d'une table de 6 places non bougeable sur macDo de l'aigle
echo -e "\nAjout d'une table de 6 places non bougeable sur macDo de l'aigle \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: "+$idMacDo -H "Content-Type: application/json" -d "{ \"number\": 1, \"id\": 0, \"places\": 6, \"movable\": false}")

# 	Ajout d'une table de 5 places non bougeable sur resto1 
echo -e "\nAjout d'une table de 5 places non bougeable sur resto1 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: "+$idResto1 -H "Content-Type: application/json" -d "{ \"number\": 1, \"id\": 0, \"places\": 5, \"movable\": false}")

#	Ajout d'une table de 2 places bougeable sur resto1
echo -e "\nAjout d'une table de 2 places bougeable sur resto1 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: "+$idResto1 -H "Content-Type: application/json" -d "{ \"number\": 2, \"id\": 0, \"places\": 2, \"movable\": true}")

#	Ajout d'une table de 3 places bougeable sur resto1
echo -e "\nAjout d'une table de 3 places bougeable sur resto1 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: "+$idResto1 -H "Content-Type: application/json" -d "{ \"number\": 2, \"id\": 0, \"places\": 3, \"movable\": true}")

#	Ajout d'une table de 7 places non bougeable sur resto2
echo -e "\nAjout d'une table de 7 places non bougeable sur resto2 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: "+$idResto2 -H "Content-Type: application/json" -d "{ \"number\": 2, \"id\": 0, \"places\": 7, \"movable\": false}")

#	Ajout d'une table de 2 places non bougeable sur resto2
echo -e "\nAjout d'une table de 2 places non bougeable sur resto2 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/tables" -H "accept: application/json" -H "restaurant_id: "+$idResto2 -H "Content-Type: application/json" -d "{ \"number\": 2, \"id\": 0, \"places\": 2, \"movable\": false}")


#---------------------------------- AJOUT DE DISPONILITES --------------------------------#


# 	Ajout de la disponibilité WEDNESDAY-MORNING sur Eiffel
echo -e "\nAjout de la disponibilité MONDAY-MORNING sur resto1 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: "+$idEiffel -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"MORNING\", \"day\": \"WEDNESDAY\"}")

# 	Ajout de la disponibilité THURSDAY-EVENING sur Hippopotamus
echo -e "\nAjout de la disponibilité THURSDAY-EVENING sur Hippopotamus \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: "+$idHippo -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"EVENING\", \"day\": \"THURSDAY\"}")

# 	Ajout de la disponibilité THURSDAY-MORNING sur Hippopotamus
echo -e "\nAjout de la disponibilité THURSDAY-MORNING sur Hippopotamus \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: "+$idHippo -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"MORNING\", \"day\": \"THURSDAY\"}")

# 	Ajout de la disponibilité FRIDAY-EVENING sur MacDo
echo -e "\nAjout de la disponibilité FRIDAY-EVENING sur MacDo\n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: "+$idMacDo -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"EVENING\", \"day\": \"FRIDAY\"}")

# 	Ajout de la disponibilité SATURDAY-MORNING sur MacDo
echo -e "\nAjout de la disponibilité SATURDAY-MORNING sur MacDo\n"
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

#	Ajout de la disponibilité SUNDAY-MORNING sur resto2
echo -e "\nAjout de la disponibilité SUNDAY-MORNING sur resto2 \n"
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/disponibilities" -H "accept: application/json" -H "restaurant_id: "+$idResto2 -H "Content-Type: application/json" -d "{ \"id\": 0, \"periode\": \"MORNING\", \"day\": \"SUNDAY\"}")

#---------------------------------- AJOUT DE SPECIALITES --------------------------------#

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

#----------------------------------AJOUT DE MENUS--------------------------------#

#-------RESTO1----------#
# Ajout du menu "menu of the day" sur resto1
echo -e "\nAjout du menu 'menu of the day' sur resto1 \n"
idMenu=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/menu" -H "accept: application/json" -H "restaurant_id: "+$idResto1 -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 25, \"name\": \"menu du jour\"}")
idMenu=$(echo -e $idMenu | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idMenu = $idMenu \n"

#Ajout du plat 'gaspacho' de type STARTER sur 'menu du jour'
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/meal" -H "accept: application/json" -H "menu_id: "+$idMenu -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 6, \"type\": \"STARTER\", \"name\": \"beef gazpacho\"}")

#Ajout du plat 'saumon et legumes' de type MAINCOURSE sur 'menu du jour'
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/meal" -H "accept: application/json" -H "menu_id: "+$idMenu -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 15, \"type\": \"MAINCOURSE\", \"name\": \"marinated salmon with sweet seasonal vegetables\"}")

#Ajout du plat 'café gourmand' de type DESERT sur 'menu du jour'
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/meal" -H "accept: application/json" -H "menu_id: "+$idMenu -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 4, \"type\": \"DESERT\", \"name\": \"gourmet coffee\"}")

# Ajout du menu "menu d'orient" sur resto1
echo -e "\nAjout du menu 'menu d'orient' sur resto2 \n"
idMenu=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/menu" -H "accept: application/json" -H "restaurant_id: "+$idResto1 -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 15, \"name\": \"Orient menu\"}")
idMenu=$(echo -e $idMenu | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idMenu = $idMenu \n"

#Ajout du plat 'salade' de type STARTER sur 'menu d'orient'
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/meal" -H "accept: application/json" -H "menu_id: "+$idMenu -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 3, \"type\": \"STARTER\", \"name\": \"Salad\"}")

#Ajout du plat 'couscous' de type MAINCOURSE sur 'menu d'orient'
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/meal" -H "accept: application/json" -H "menu_id: "+$idMenu -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 10, \"type\": \"MAINCOURSE\", \"name\": \"Couscous\"}")

#Ajout du plat 'flan libanais à la fleur d'orange' de type DESERT sur 'menu d'orient'
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/meal" -H "accept: application/json" -H "menu_id: "+$idMenu -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 2, \"type\": \"DESERT\", \"name\": \"Lebanese flan with orange blossom\"}")

#-------RESTO2----------#
# Ajout du menu "menu 42" sur resto2
echo -e "\nAjout du menu 'menu of the day' sur resto1 \n"
idMenu=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/menu" -H "accept: application/json" -H "restaurant_id: "+$idResto2 -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 10, \"name\": \"Menu 42\"}")
idMenu=$(echo -e $idMenu | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idMenu = $idMenu \n"

#Ajout du plat 'Nems' de type STARTER sur 'menu 42'
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/meal" -H "accept: application/json" -H "menu_id: "+$idMenu -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 2, \"type\": \"STARTER\", \"name\": \"Nems\"}")

#Ajout du plat 'ravioles aux crevettes' de type MAINCOURSE sur 'menu 42'
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/meal" -H "accept: application/json" -H "menu_id: "+$idMenu -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 5, \"type\": \"MAINCOURSE\", \"name\": \"Ravioli with shrimp\"}")

#Ajout du plat 'glace aux lychees' de type DESERT sur 'menu 42'
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/meal" -H "accept: application/json" -H "menu_id: "+$idMenu -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 3, \"type\": \"DESERT\", \"name\": \"Lychee ice cream\"}")

# Ajout du menu "menu 64" sur resto2
echo -e "\nAjout du menu 'menu d'orient' sur resto2 \n"
idMenu=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/menu" -H "accept: application/json" -H "restaurant_id: "+$idResto2 -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 13, \"name\": \"Menu 64\"}")
idMenu=$(echo -e $idMenu | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idMenu = $idMenu \n"

#Ajout du plat 'rouleaux de printemps' de type STARTER sur 'menu 64'
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/meal" -H "accept: application/json" -H "menu_id: "+$idMenu -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 2, \"type\": \"STARTER\", \"name\": \"Spring Rolls\"}")

#Ajout du plat 'porc caramel' de type MAINCOURSE sur 'menu 64'
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/meal" -H "accept: application/json" -H "menu_id: "+$idMenu -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 7, \"type\": \"MAINCOURSE\", \"name\": \"Caramel pork\"}")

#Ajout du plat 'ile flottante' de type DESERT sur 'menu 64'
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/meal" -H "accept: application/json" -H "menu_id: "+$idMenu -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 4, \"type\": \"DESERT\", \"name\": \"Floating island\"}")

# Ajout du menu "menu 71" sur resto2
echo -e "\nAjout du menu 'menu d'orient' sur resto2 \n"
idMenu=$(curl --silent POST "http://localhost:8080/javaee7-angular/resources/menu" -H "accept: application/json" -H "restaurant_id: "+$idResto2 -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 15, \"name\": \"Menu 71\"}")
idMenu=$(echo -e $idMenu | cut -d':' -f 2 | cut -d',' -f 1)
echo -e "\n idMenu = $idMenu \n"

#Ajout du plat 'salade de pouce de soja' de type STARTER sur 'menu 71'
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/meal" -H "accept: application/json" -H "menu_id: "+$idMenu -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 3, \"type\": \"STARTER\", \"name\": \"soy sprout salad\"}")

#Ajout du plat 'pinces de crabes et riz cantonnais' de type MAINCOURSE sur 'menu 71'
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/meal" -H "accept: application/json" -H "menu_id: "+$idMenu -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 10, \"type\": \"MAINCOURSE\", \"name\": \"Crab claw with cantonese rice\"}")

#Ajout du plat 'beignets à la banane' de type DESERT sur 'menu 71'
echo -e $(curl --silent POST "http://localhost:8080/javaee7-angular/resources/meal" -H "accept: application/json" -H "menu_id: "+$idMenu -H "Content-Type: application/json" -d "{ \"id\": 0, \"price\": 2, \"type\": \"DESERT\", \"name\": \"Banana fritters\"}")