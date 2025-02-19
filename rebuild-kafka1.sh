#!/bin/bash
docker compose down kafka1
docker compose build kafka1
docker compose up -d kafka1
