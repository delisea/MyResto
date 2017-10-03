## Déployer une application web sur un container wildfly:
#### Requis: l’application au format war (export to war sous eclipse)
_(javaee6angularjs.war est une application test pour cette partie, remplacer app par javaee6angularjs)_


créer dans un workspace un fichier “Dockerfile” sans extension contenant:
```java
FROM jboss/wildfly

COPY app.war /opt/jboss/wildfly/standalone/deployments/app.war
```
ajouter le programme “app.war” à charger dans wildfly dans le même dossier

---
Dans le CLI docker, se placer dans le dossier précédant (cd) puis taper: (myapp le nom de l’image créée)
```bash
docker build --tag=myapp .
```


Puis:
```bash
docker run -it -p 8080:8080 myapp
````
(changer le port si occupé, on doit pouvoir voir le fichier app.war être charger dans les logs)

> si tout va bien l’application est accessible à l’addresse localhost:8080/app
