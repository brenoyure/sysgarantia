#!/bin/bash
docker compose down front
docker compose build front
docker compose up -d front

