import KeyCloak from 'keycloak-js'

const keycloakhost = process.env.NODE_ENV == 'development' ? "http://localhost:8180" : '/keycloak'

const keycloak = new KeyCloak({
    url: keycloakhost,
    realm: "br.albatross.sysgarantia",
    clientId: "sysgarantia-vue-client"
})

export default keycloak