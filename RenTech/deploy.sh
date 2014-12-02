#!/bin/sh
message=`hostname`;
echo $message
echo "http://$message:8080/ecom/"
asadmin undeploy ecom
asadmin stop-database
asadmin stop-domain
echo "Restarting glassfish ..."
asadmin start-domain
asadmin start-database
mvn clean install
asadmin redeploy --name ecomear --contextroot "ecom" ecomear/target/ecomear-0.1.0.ear
firefox "http://$message:8080/ecom/"