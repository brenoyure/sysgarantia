#!/bin/bash

cd ./front/

cp ../nginx/nginx_template.conf .

npm i
npm run build

cd ..

docker compose build front

