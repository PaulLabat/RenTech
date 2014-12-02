#!/bin/sh

#!parse fichier settings.xml
settingsXML='/home/augustin/.m2/settings.xml'
password="";
admin="";
host=`hostname`;

DeployementWithXML(){
#deploiement du site avec settings.xml
echo "deploiement du site avec settings.xml"
asadmin --user $admin undeploy ecom
asadmin --user $admin stop-database
asadmin --user $admin stop-domain
echo "\n ########################################"
echo " ####### Restarting glassfish ... #######"
echo " ########################################\n"
asadmin --user $admin start-domain
asadmin --user $admin start-database
mvn clean install
asadmin --user $admin redeploy --name ecomear --contextroot "ecom" ecomear/target/ecomear-0.1.0.ear
xdg-open "http://$host:8080/ecom/"
}

DeployementWithoutXML(){
#deploiement du site sans settings.xml
echo "deploiement du site sans settings.xml"
asadmin $admin undeploy ecom
asadmin stop-database
asadmin stop-domain
echo "\n ########################################"
echo " ####### Restarting glassfish ... #######"
echo " ########################################\n"
asadmin start-domain
asadmin start-database
mvn clean install
asadmin redeploy --name ecomear --contextroot "ecom" ecomear/target/ecomear-0.1.0.ear
xdg-open "http://$host:8080/ecom/"
}

if [ -e $settingsXML ]
then
    echo "##### fichier settings.xml trouvé #####"
    password=`sed -n -r 's%<local.glassfish.adminPassword>(.*)<\/local.glassfish.adminPassword>%\1%p' $settingsXML`
    admin=`sed -n -r 's%<local.glassfish.user>(.*)<\/local.glassfish.user>%\1%p' $settingsXML`
    echo $password
    echo $admin
    DeployementWithXML
else
    echo "##### le fichier settings.xml n'a pas été trouvé #####"
    DeployementWithoutXML
fi