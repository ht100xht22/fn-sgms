#!/usr/bin/bash

echo "Installing functions..."

faas-cli up -f booking.yaml
faas-cli up -f instruments-info.yml
faas-cli up -f instruments.yml
faas-cli up -f lessons.yaml
faas-cli up -f rent.yml
faas-cli up -f stundents-info.yml

echo "Functions are now up and running at \"http://localhost:8080/ui/\""