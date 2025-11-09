#!/usr/bin/env bash

export IMAGE=$1
docker-compose -f docker-compose.yaml up --detach

echo "successfully started the application using docker compose"
