FROM quay.io/keycloak/keycloak:26.1.2

WORKDIR /opt/keycloak

RUN ./bin/kc.sh build --http-relative-path=/keycloak --db mariadb

RUN mkdir ./data/import/
ADD --chown=keycloak:keycloak --chmod=644 ./realm-backup/br.albatross.sysgarantia.realm.with.users/ ./data/import/

ADD --chown=keycloak:keycloak --chmod=644 br.albatross.key.pem .
ADD --chown=keycloak:keycloak --chmod=644 br.albatross.cert.pem .

ENTRYPOINT /opt/keycloak/bin/kc.sh start --optimized --db-url jdbc:mariadb://${SYSGARANTIA_API_DB_HOST}:${SYSGARANTIA_API_DB_PORT}/keycloak?createDatabaseIfNotExist=true --db-username ${SYSGARANTIA_API_DB_USERNAME} --db-password ${SYSGARANTIA_API_DB_PASSWORD} --import-realm --hostname-strict false --https-certificate-key-file ./br.albatross.key.pem --https-certificate-file ./br.albatross.cert.pem
