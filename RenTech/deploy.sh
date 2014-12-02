#!/bin/sh
host=`hostname`;
asadmin undeploy ecom
asadmin stop-database
asadmin stop-domain
echo "Restarting glassfish ..."
asadmin start-domain
asadmin start-database
mvn clean install
asadmin redeploy --name ecomear --contextroot "ecom" ecomear/target/ecomear-0.1.0.ear
firefox "http://$host:8080/ecom/"