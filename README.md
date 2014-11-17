RenTech
=======

Projet E-commerce RICM5


###Tutorial Déploiement du projet
Pour pouvoir déployer le projet en ligne de commande il y a quelques étapes à respecter qui sont les suivantes :

* Installation de java, maven et de glassfish en ayant dans le path les commandes exécutables respectives à chaque logiciel
* Paramétrage de Maven avec la rédaction du fichier **settings.xml**
* Compiler le projet avec maven
* Lancer glassfish

Je rappel que tout ce qui va être fait ici fonctionnera exclusivement en ligne de commande. Concernant la première étape je ne m'attarderai que sur l'installation de Glassfish.

1: Installation de Glassfish
##### Sur Windows

   * Télécharger glassfish
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

 Ce fichier configure Maven selon votre configuration local. Ce fichier doit se mettre dans le dossier .m2/ . Ceci est vrai pour tous les OS.
 Sur windows, voilà le path où mettre ce fichier : **C:\Users\Nom_Utilisateur\.m2**
 Sur linux : **home/Nom_Utilisateur**

 Dans ce fichier vous copierez/collerez les balises suivantes :

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

 Vous remplirez bien évidemment les champs manquants et remplacerez le path de glassfish qui correspondra à votre configuration.

3: Compilation du projet

 Placez vous (en ligne de commande) à la racine du projet (i.e. là où se trouve le premier fichier pom.xml) et exécutez la commande suivante :
     `mvn clean package`
 Il ne devrait pas y avoir de problème à ce niveau là. S'il y en a un, c'est que peut être votre maven est mal installé.

4: Lancement de Glassfish
Cette dernière étape est amenée à être modifiée dans les prochains jours pour qu'on n'est moins à naviguer dans les dossiers.

 Placez vous dans le dossier (en ligne de commande) ecomapp et faites la commande suivante :
     `mvn glassfish:deploy`
 Là également si vous avez correctement suivi toutes les étapes précédentes, vous ne devriez pas avoir d'erreur.
 Dans le cas où vous obtenez un **BUILD SUCCESS**, vous n'avez plus qu'à cliquer sur le lien suivant : [http://localhost:8080/ecomweb-0.1.0/index.jsp](http://localhost:8080/ecomweb-0.1.0/index.jsp)

5: Quelques commandes utiles :

 * `mvn glassfish:deploy` : déploie l'application
 * `mvn glassfish:undeploy` : stop l'application
 * `mvn glassfish:redeploy` : Après modification du code, plutôt que de stopper l'application pour la relancer avec les deux commandes précédentes, celle-ci va les condenser.