#!/bin/bash
docker compose down keycloak
docker compose build keycloak
docker compose up -d keycloak

