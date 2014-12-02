RenTech
=======

Projet E-commerce RICM5


###Tutorial Déploiement du projet
Pour pouvoir déployer le projet en ligne de commande il y a quelques étapes à respecter qui sont les suivantes :

* Installation de java, maven et de glassfish en ayant dans le path les commandes exécutables respectives à chaque logiciel
* Paramétrage de Maven avec la rédaction du fichier **settings.xml**
* Déployement le projet

Je rappel que tout ce qui va être fait ici fonctionnera exclusivement en ligne de commande. Concernant la première étape je ne m'attarderai que sur l'installation de Glassfish.

1: Installation de Glassfish
-----------------------------
##### Sur Windows

   * Télécharger glassfish (version full et non pas la version glassfish-4.1-web.zip
   * Décompresser le dossier glassfish dans un endroit où il n'y a pas besoin de droit d'administrateur pour y accéder (même en lecture)
   * Mettre la variable d'environnement système GLASSFISH_HOME dans le path (vous devez créer au préalable cette variable en lui donnant pour valeur le chemain d'accès au dossier bin de Glassfish)
   * Lancer un terminal
   * Il faut maintenant paramétrer votre mot de passe admin (par défaut il n'y en a pas)
       * rentrer la commande suivante : `asadmin change-admin-password` et suivre les instructions qu'il y a marqué dans votre console et **mettre un mot de passe de plus de 6 caractères**
       * rentrer ensuite la commande suivante : `asadmin change-master-password --savemasterpassword` . Le mot de passe par défaut est **changeit** . Mettez votre mot de passe admin pour plus de simpliciter
       * rentrer maintenant la commande suivante : `asadmin delete-domain domain1`
   * Dans le dossier bin de glassfish, on trouvera deux fichiers *asadmin* : un .bat (windows) et un .sh (unix). Renommez le fichier .sh en **asadminUnix** (cela vous évitera une erreur du genre : *cette commande n'est pas une application win32 valable*

   Voilà maintenant votre glassfish est correctement paramétré.

2: Rédaction du fichier settings.xml de Maven
---------------------------------
 Ce fichier configure Maven selon votre configuration local. Ce fichier doit se mettre dans le dossier .m2/ . Ceci est vrai pour tous les OS.
 Sur windows, voilà le path où mettre ce fichier : **C:\Users\Nom_Utilisateur\\.m2**
 Sur linux : **home/Nom_Utilisateur/.m2/**

 Dans ce fichier vous copierez/collerez les balises suivantes :
 
     <?xml version="1.0" encoding="UTF-8"?>
     <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
        <profiles>
            <profile>
    	        <id>glassfish-context</id>
       	            <properties>
            	        <local.glassfish.home>C:\glassfish4\glassfish</local.glassfish.home>
            	        <local.glassfish.user>admin</local.glassfish.user>
            	        <local.glassfish.adminPassword></local.glassfish.adminPassword>
            	        <local.glassfish.domain.name>domain1</local.glassfish.domain.name>
            	        <local.glassfish.domain.host>localhost</local.glassfish.domain.host>
            	        <local.glassfish.domain.adminPort>4848</local.glassfish.domain.adminPort>
                    </properties>
            </profile>
        </profiles>
        <activeProfiles>
            <activeProfile>glassfish-context</activeProfile>
        </activeProfiles>
     </settings>

 Vous remplirez bien évidemment les champs manquants et remplacerez le path de glassfish qui correspondra à votre configuration.

3: Déployement du projet
-------------------------
Maintenant que la base de données est à priori en état de marche, il y a eu quelques petits changements sur la manière de déployer le projet. Cela devrait à priori vous simplifier la vie.
Vous trouverez dans le projet dans le dossier Rentech/ un script **deploy.bat** (pour windows) et **deploy.sh** (pour linux). Pour lancer serveur, base de données et voir le site, il vous suffit d'exécuter le script propre à votre OS.
Sur windows il suffira donc de double cliquer sur le **deploy.bat**.

Ce script va successivement faire les actions suivantes :
  * démarrer le domain1
  * démarrer la base de données
  * arrêter l'application ecomear
  * compiler l'ensemble du projet
  * deployer le projet nouvellement compilé
  * lancer le navigateur par défaut avec le site en guise d'url

Il n'y a rien de plus à faire. J'espère que ça marchera du premier coup de votre côté...

5: Quelques commandes utiles :
---------------------------
 * `mvn clean install` : compilation du projet
 * `mvn glassfish:deploy` : déploie l'application
 * `mvn glassfish:undeploy` : stop l'application
 * `mvn glassfish:redeploy` : Après modification du code, plutôt que de stopper l'application pour la relancer avec les deux commandes précédentes, celle-ci va les condenser.