#!/bin/bash

# Fail on the first error, rather than continuing
set -e

function on_exit {
  docker-compose stop
  docker-compose kill
}

trap on_exit EXIT

docker-compose kill
docker-compose rm -vf
docker-compose build
docker-compose up --remove-orphans
