#!/usr/bin/env bash

./gradlew clean
./gradlew build
export USER_UID=$(id -u)
docker-compose build
docker-compose up -d