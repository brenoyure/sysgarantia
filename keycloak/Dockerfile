FROM quay.io/keycloak/keycloak:26.1.2

WORKDIR /opt/keycloak

COPY br.albatross.key.pem .
COPY br.albatross.cert.pem .

RUN ./bin/kc.sh build --http-relative-path /keycloak --db mariadb --https-certificate-key-file br.albatross.key.pem --https-certificate-file br.albatross.cert.pem

ENV KC_DB_URL=jdbc:mariadb://${SYSGARANTIA_API_DB_HOST}:${SYSGARANTIA_API_DB_PORT}/keycloak?createDatabaseIfNotExist=true
ENV KC_DB_USERNAME=${SYSGARANTIA_API_DB_USERNAME}
ENV KC_DB_PASSWORD=${SYSGARANTIA_API_DB_PASSWORD}

ENTRYPOINT ["/opt/keycloak/kc.sh"]
