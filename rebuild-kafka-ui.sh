#!/bin/bash
docker compose down kafka-ui
docker compose build kafka-ui
docker compose up -d kafka-ui
