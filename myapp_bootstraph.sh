#!/usr/bin/env bash
copilot app init myapp
copilot env init --name test --profile default 
copilot svc init -d ./Dockerfile --name front-end -t "Load Balanced Web Service"
copilot svc init -d ./Dockerfile --name back-end -t "Backend Service"

