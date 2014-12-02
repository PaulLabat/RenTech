echo off
hostname > tmp
set /p host=<tmp
del tmp
(
asadmin undeploy ecom
asadmin stop-database
asadmin stop-domain
echo ########################################
echo ####### Restarting glassfish ... #######
echo ########################################
asadmin start-domain
asadmin start-database
mvn clean install
asadmin deploy --name ecomear --contextroot "ecom" ecomear/target/ecomear-0.1.0.ear
start http://%host%:8080/ecom/
)