import KeyCloak from 'keycloak-js'

const keycloak = new KeyCloak({
    url: "http://localhost:8180",
    realm: "br.albatross.sysgarantia",
    clientId: "sysgarantia-vue-client"
})

export default keycloak