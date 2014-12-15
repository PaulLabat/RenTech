#!/bin/sh
asadmin='/home/augustin/glassfish4/glassfish/bin/./asadmin';
ij='/home/augustin/glassfish4/javadb/bin/./ij';
DeleteBDD(){
$ij deleteTable.sql;
}
CreateAdminAccess(){
$ij createAdminUser.sql;
}
$asadmin --6969 stop-database
$asadmin --6969 stop-domain
echo " ########################################"
echo " ####### Restarting glassfish ... #######"
echo " ########################################"
$asadmin start-domain
$asadmin undeploy --port 6969 ecomear
$asadmin start-database
DeleteBDD
$asadmin deploy --port 6969 --name ecomear --contextroot "ecom" target/ecomear-0.1.0.ear
$asadmin get-client-stubs --port 6969 --appname ecomear target/
CreateAdminAccess