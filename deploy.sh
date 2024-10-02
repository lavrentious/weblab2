#!/bin/bash

WILDFLY_PATH="/Users/lavrent/Downloads/wildfly-preview-34.0.0.Beta1"
DEPLOY_PATH="$(pwd)/app/build/libs/app.war"

gradle clean build && $WILDFLY_PATH/bin/jboss-cli.sh --connect --commands="deploy --force $DEPLOY_PATH"
