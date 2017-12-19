FROM myresto_base

USER root 

# Should be changed for your public domain name
ENV SWAGGER_API_URL http://18.196.18.169/swagger/swagger.json

ENV SWAGGER_DIST swagger-ui
ENV SWAGGER_TARGET_DIR $JBOSS_HOME/welcome-content
ENV SWAGGER_DEPLOY_DIR $JBOSS_HOME/standalone/deployments/

# Add swagger ui
RUN rm -fr $SWAGGER_TARGET_DIR/*
COPY $SWAGGER_DIST/* $SWAGGER_TARGET_DIR/
RUN sed -i -- 's|http://petstore.swagger.io/v2/swagger.json|'$SWAGGER_API_URL'|g' $SWAGGER_TARGET_DIR/*.js $SWAGGER_TARGET_DIR/*.html

# Add swagger.json
ADD swagger.json $SWAGGER_TARGET_DIR/
ADD dist $SWAGGER_TARGET_DIR/
ADD swagger-ui $JBOSS_HOME/swagger

# Add application
ADD javaee7-angular.war $SWAGGER_DEPLOY_DIR/