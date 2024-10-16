#!/bin/bash

REMOTE_HOME_PATH="/home/studs/s408968"
REMOTE_WILDFLY_PATH="$REMOTE_HOME_PATH/web/wildfly"
LOCAL_WAR_PATH="$(pwd)/app/build/libs/app.war"
HOST_NAME="itmo"

# gradle clean build && \
# scp $LOCAL_WAR_PATH $HOST_NAME:$REMOTE_HOME_PATH 
echo "ssh $HOST_NAME \"$REMOTE_WILDFLY_PATH/bin/jboss-cli.sh --connect --commands=\"deploy --force $REMOTE_HOME_PATH/app.war\"\""
# ssh $HOST_NAME "rm $REMOTE_HOME_PATH/app.war"

