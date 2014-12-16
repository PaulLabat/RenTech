#!/bin/sh
ij_home="/opt/glassfish4/javadb/bin/"
ij=`ij`
if [ -e $ij_home ]
then ij=`/opt/glassfish4/javadb/bin/./ij`
fi

user=`echo $USER`;
settingsXML="truc";
if [ $# = 1 ]
then if [ $1 = "whith" ]
    then settingsXML="/home/$user/.m2/settings.xml";
    else echo "unknown this command : "$1;
    fi
fi

password="";
admin="";
host=`hostname`;

DeleteBDD(){
$ij deleteTable.sql;
}

CreateAdminAccess(){
$ij createAdminUser.sql;
}

DeployementWithXML(){
#deploiement du site avec settings.xml
echo "deploiement du site avec settings.xml"
asadmin --user $admin stop-database
asadmin --user $admin stop-domain
echo "\n ########################################"
echo " ####### Restarting glassfish ... #######"
echo " ########################################\n"
asadmin --user $admin start-domain
asadmin --user $admin undeploy ecomear
asadmin --user $admin start-database
DeleteBDD
mvn clean install
asadmin --user $admin deploy --name ecomear --contextroot "ecom" ecomear/target/ecomear-0.1.0.ear
asadmin --user $admin get-client-stubs --appname ecomear ecomear/target/
}

DeployementWithoutXML(){
#deploiement du site sans settings.xml
echo "deploiement du site sans settings.xml"
asadmin stop-database
asadmin stop-domain
echo "\n ########################################"
echo " ####### Restarting glassfish ... #######"
echo " ########################################\n"
asadmin start-domain
asadmin undeploy ecomear
asadmin start-database
DeleteBDD
mvn clean install
asadmin deploy --name ecomear --contextroot "ecom" ecomear/target/ecomear-0.1.0.ear
asadmin get-client-stubs --appname ecomear ecomear/target/
}


if [ -e $settingsXML ]
then
    echo "##### fichier settings.xml trouvé #####"
    password=`sed -n -r 's%<local.glassfish.adminPassword>(.*)<\/local.glassfish.adminPassword>%\1%p' $settingsXML`
    admin=`sed -n -r 's%<local.glassfish.user>(.*)<\/local.glassfish.user>%\1%p' $settingsXML`
    DeployementWithXML
else
    echo "##### le fichier settings.xml n'a pas été trouvé #####"
    DeployementWithoutXML
fi

CreateAdminAccess

xterm  -e "appclient -jar ecomear/target/ecomearClient.jar" &
xdg-open "http://$host:8080/ecom/"
