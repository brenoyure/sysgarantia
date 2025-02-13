<template>

<div class="container px-4 py-5" id="hanging-icons">
    <h2 class="pb-2 border-bottom" style="text-align: center;">Tecnologias utilizadas</h2>
    <div class="row g-4 py-5 row-cols-1 row-cols-lg-3 tecnologiasutilizadas-div">
        <TecnologiaUtilizada
            titulo="Apache Kafka" 
            texto="Sistema de processamento de streams/eventos conhecida pelo seu alto throughput. Utilizada para processar os eventos de novas Solicitações de Garantia, de uma maneria rápida e confiável." 
            hrefBotao="https://kafka.apache.org/quickstart"
            textoBotao="Saiba Mais"
            simbolo="kafka">
        </TecnologiaUtilizada>
        <TecnologiaUtilizada
            titulo="MariaDB" 
            texto="Banco de dados open source fork do MySQL utilizado na persistência dos dados do Sistema de Garantia." 
            hrefBotao="https://mariadb.com/"
            textoBotao="Saiba Mais"
            simbolo="mariadb">
        </TecnologiaUtilizada>
        <TecnologiaUtilizada
            titulo="VueJS" 
            texto="Framework Javascript utilizado na construção do Frontend. Conhecido por sua simplecidade e robustesa. Com ele conseguimos organizar nosso código em componentes reutilizáveis, como se estivéssemos criando nossas próprias tags html." 
            hrefBotao="https://vuejs.org/guide/introduction.html"
            textoBotao="Saiba Mais"
            simbolo="vuejs">
        </TecnologiaUtilizada>
        <TecnologiaUtilizada
            titulo="Quarkus" 
            texto="Framework Java utilizado na criação do Backend a partir as tecnologias do JakartaEE, como JAX-REST para os endpoints REST, Bean Validation para validação e JPA/Hibernate para a persistência dos dados." 
            hrefBotao="https://quarkus.io/get-started/"
            textoBotao="Saiba Mais"
            simbolo="quarkus">
        </TecnologiaUtilizada>
        <TecnologiaUtilizada
            titulo="Keycloak" 
            texto="Tecnologia de autenticação e autorização única (SSO), com vários recursos já implementados como o 'Esqueci Senha', autenticação em dois fatores com autenticador móvel, integração com servidores LDAP como o Active Directory, autorização baseada em roles/groups dentre outros." 
            hrefBotao="https://quarkus.io/get-started/"
            textoBotao="Saiba Mais"
            simbolo="keycloak">
        </TecnologiaUtilizada>
    </div>
</div>

<hr>

<div>
    <h5>Sobre as logos desta página</h5>
    <ul>
        <li><i>Kafka</i> e seu logo são propriedade da <i>Apache Software Foundation</i></li>
        <li><i>MariaDB</i> e seu logo são de propriedade de <i>MariaDB plc</i></li>
        <li><i>VueJS</i> e seu logo são de propriedade de <i>Evan You</i>, criador do <i>framework</i></li>
        <li><i>Quarkus</i> e seu logo são de propriedade da <i>Red Hat</i></li>
        <li><i>Java</i> e <i>MySQL</i> são marcas registradas da <i>Oracle Corporation</i></li>
    </ul>
</div>

</template>

<script setup>
import TecnologiaUtilizada from './TecnologiaUtilizada.vue'

</script>
