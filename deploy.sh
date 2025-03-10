#!/bin/bash
echo
echo '**********************************************************************************************'
echo
echo '**********        xx - Destruindo Containers (caso existam) ... - xx     *********************'
echo
echo '**********************************************************************************************'
docker compose down

echo
echo '**********************************************************************************************'
echo
echo '************     01 - Construindo MariaDB para a API e o Keycloak...     *********************'
echo
echo '**********************************************************************************************'
docker compose build db
docker compose up -d db

echo
echo '**********************************************************************************************'
echo
echo '************     02 - Construindo o Kafka e o Keycloak                    ********************'
echo
echo '**********************************************************************************************'
./rebuild-kafka1.sh
./rebuild-keycloak.sh

echo
echo '**********************************************************************************************'
echo
echo '************     03 - Aguardando 30 segundos para termos certeza que      ********************'
echo '************         o Kafka e o Keycloak iniciaram por completo...       ********************'
echo
echo '**********************************************************************************************'
sleep 30

echo
echo '**********************************************************************************************'
echo
echo '************     04 - Construindo a API (Backend) do Serviço de Garantia...   ****************'
echo
echo '**********************************************************************************************'
./rebuild-api.sh

echo
echo '**********************************************************************************************'
echo
echo '************          05 - Container da API construido com sucesso               *************'
echo '************   Aguardando 10 segundos antes de iniciarmos o NGINX (Frontend)...  *************'
echo
echo '**********************************************************************************************'
sleep 10

echo
echo '**********************************************************************************************'
echo
echo '************             06 - Iniciando o NGINX (Frontend)...                    *************'
echo
echo '**********************************************************************************************'
./rebuild-front.sh
