connect 'jdbc:derby://augustin.gkny.fr:1527/sun-appserv-samples;user=APP;password=APP;create=true';
drop table utilisateur_siteweb;
drop table UTILISATEUR_SERVEURPHYSIQUE;
drop table UTILISATEUR_GIT;
drop table UTILISATEUR_FORUM;
drop table UTILISATEUR_COMMANDE;
drop table SUPPORT_SITEWEB;
drop table SUPPORT_GIT;
drop table SUPPORT_FORUM;
drop table SERVEURVIRTUEL_SITEWEB;
drop table SERVEURVIRTUEL_GIT;
drop table SERVEURVIRTUEL_FORUM;
drop table SERVEURPHYSIQUE_SERVEURVIRTUEL;
drop table PREFERENCE_SITEWEB;
drop table PREFERENCE_GIT;
drop table PREFERENCE_FORUM;
drop table PREFERENCE_ENTREPRISE;
drop table PREFERENCE_ASSOCIATION;
drop table OFFRE_SITEWEB;
drop table OFFRE_SERVEURVIRTUEL;
drop table OFFRE_SERVEURPHYSIQUE;
drop table OFFRE_GIT;
drop table OFFRE_FORUM;
drop table COMMANDE_OFFRE;

drop table ADMIN;
drop table SITEWEB;
drop table GIT;
drop table FORUM;
drop table SERVEURVIRTUEL;
drop table SERVEURPHYSIQUE;
drop table OFFRE;
drop table COMMANDE;
drop table SUPPORT;
drop table SEQUENCE;
drop table UTILISATEUR;
drop table ENTREPRISE;
drop table ASSOCIATION;
drop table PREFERENCE;

show TABLES;
disconnect;
exit;