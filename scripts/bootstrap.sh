#!/usr/bin/env bash
set -euo pipefail

mvn -B -ntp clean package

docker compose up --build -d

echo "Platform bootstrapped. Gateway: http://localhost:8080"
