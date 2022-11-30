#!/usr/bin/bash

function checkCommands () {

if [ ! "$(command -v docker)" ]; then
    echo "Could not find \"docker\" command in system. Please start Docker Desktop or dowload docker."
    exit 0
fi

if [ ! "$(command -v arkade)" ]; then
    echo "Could not find  \"arkade\" command in system. Please install Arkade from \" https://github.com/alexellis/arkade \"."
    exit 0
fi

if [ ! "$(command -v kind)" ]; then
    echo "Could not find  \"kind\" command in system. Please install Arkade from \" https://github.com/alexellis/arkade \"."
    exit 0
fi
}

function startOpenFaaS () {
    echo "Creating KinD Cluster..."

  if kind create cluster >/dev/null 2>&1; then
    echo "KinD Cluster is created with the name \"kind\""
  else
    if [[ ! "$(kind get clusters)" == "kind" ]]; then
        echo "Cluster could not be created successfully."
        exit 0;
    fi
   echo "KinD Cluster already been created, cluster name is \"kind\"."
  fi

 echo "Initializing OpenFaaS Environment..."

   while faas-cli version >/dev/null 2>&1; do

  if arkade install openfaas >/dev/null 2>&1; then
    kubectl -n openfaas get deployments -l "release=openfaas, app=openfaas"
    curl -SLsf https://cli.openfaas.com | sudo sh
    kubectl rollout status -n openfaas deploy/gateway
    echo "Open a new terminal window and run the command \"kubectl port-forward -n openfaas svc/gateway 8080:8080 &\""
    read -n 1 -p "Press any key after opening a terminal and run the command"
    PASSWORD=$(kubectl get secret -n openfaas basic-auth -o jsonpath="{.data.basic-auth-password}" | base64 --decode; echo)
echo -n $PASSWORD | faas-cli login --username admin --password-stdin
echo "Navigate to \"http://localhost:8080/ui/\" and enter username: \"admin\" and password=\"${PASSWORD}\""
    break
  else
    echo "OpenFaas could not be installed, install manually. \"arkade install openfaas\""
    exit 0;
  fi
   done
 
  

}


echo "Initializing Development Environment..."

checkCommands

startOpenFaaS

echo "Development Environment is complete!"

