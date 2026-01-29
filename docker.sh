#!/bin/bash

set -e

clear
echo "Faz build da imagem do container da API (backend)"

# Porta interna no container, que deve ser igual definida no Dockerfile
PORT=8080

docker build -f Dockerfile -t vendas-api .
docker run -it --rm --env-file .env -p ${PORT}:${PORT} vendas-api
