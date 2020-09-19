#!/usr/bin/env bash
copilot app init "nifty-cool-business"
copilot env init --name "test" --profile default
copilot svc init -d ./Dockerfile --name "web-application" -t "Load Balanced Web Service"
copilot svc deploy --name "web-application" --env "test"
copilot svc init -d ./Dockerfile --name "api" -t "Backend Service"
copilot svc deploy --name "api" --env "test"
