FROM airhacks/glassfish
COPY ./target/cloud.war ${DEPLOYMENT_DIR}
