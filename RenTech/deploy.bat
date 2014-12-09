echo off
hostname > tmp
set /p host=<tmp
del tmp
(
echo ########################################
echo ####### Restarting glassfish ... #######
echo ########################################
asadmin start-domain
asadmin undeploy ecomear
asadmin start-database
mvn clean install
asadmin deploy --name ecomear --contextroot "ecom" ecomear/target/ecomear-0.1.0.ear
asadmin get-client-stubs --appname ecomear ecomear/target/
start appclient -jar ecomear/target/ecomearClient.jar
start http://%host%:8080/ecom/
)