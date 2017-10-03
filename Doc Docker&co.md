## Créer une application web minimal au format war:
#### Requis: eclipse pour javaEE et tomcat
Créer un projet eclipse nommé myResto de type :
> Dynamic Web Project

Vérifier que le server tomcat est bien selectionné.

---
Créer une class Test dans Java Ressources > src > myResto
```java
package myResto;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Test extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

	    response.setContentType("text/html");

	    response.setCharacterEncoding( "UTF-8" );

	    PrintWriter out = response.getWriter();

	    out.println("<!DOCTYPE html>");

	    out.println("<html>");

	    out.println("<head>");

	    out.println("<meta charset=\"utf-8\" />");

	    out.println("<title>Test</title>");

	    out.println("</head>");

	    out.println("<body>");

	    out.println("<p>Ceci est une page générée depuis une servlet.</p>");

	    out.println("</body>");

	    out.println("</html>");

	}
}
```

---
Créer un fichier xml web.xml dans WebContent > WEB-INF
```xml
<?xml version="1.0" encoding="UTF-8"?>

<web-app 

  xmlns="http://java.sun.com/xml/ns/javaee"

  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"

  xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"

  version="3.0">

<servlet>

    <servlet-name>Test</servlet-name>

    <servlet-class>myResto.Test</servlet-class>

</servlet>
<servlet-mapping>

    <servlet-name>Test</servlet-name>

    <url-pattern>/toto</url-pattern>

</servlet-mapping>
</web-app>
```

---
Clique droit sur le projet > exporter > war

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
>
> si le fichier est celui généré dans la partie précédente l'addresse est localhost:8080/nomfichier/toto
