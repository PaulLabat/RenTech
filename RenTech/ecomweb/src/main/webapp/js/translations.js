	// ---------------------- TRADUCTIONS -------------------------- //

	scotchApp.config(function ($translateProvider) {
	  $translateProvider.translations('en', {
		  HOME : 'Home',
		  PANIER : 'Cart',
		  PRODUIT : 'Products',
		  CONNEXION : 'Login',
		  CONNECTE : 'Connected as ',
		  CONNEXION_FAILED : 'Incorrect email or password',
		  PARTICULIERS : 'Private individuals',
		  G_UTILISATEURS : 'Group of users',
		  ENTREPRISES : 'Companies',
		  CONNECTED : 'Connected as',
		  DECONNEXION : 'Logout',
		  A_PROPOS : 'About',
		  A_PARTIR_DE : 'Starting from',
		  TAXES : 'excl. VAT/month',
		  SIGNUP : 'Signup',
		  REMEMBER_ME : 'Remember me',
		  NOT_A_MEMBER : 'Not a member ?',
		  SAVE : "Save",
		  PASSWORD : "Password",
		  OLD : "Old",
		  NEW : "New",
		  CHECK : "Check",
		  ADDRESS : "Address",
		  COMPLETED_ORDERS : "Completed orders",
		  CURRENT_ORDERS : "Current orders",
		  FIRST_NAME : "First name",
		  LAST_NAME : "Last name",
		  CITY : "City",
		  POSTAL_CODE : "Postal code",
		  HISTORIQUE : "History rentals",
		  PARAMETRES : "Account parameters",
		  PRODUCT : "Product",
		  PRICE : "Price",
		  QUANTITY : "Quantity",
		  VOIR_PLUS: "See more >",
		  PAYEMENT : "Checkout",
		  PRODUIT_AJOUTE : "Product added!"
	  });
	  
	  
	  $translateProvider.translations('fr', {
		  HOME : 'Accueil',
		  PRODUIT : 'Produits',
		  PANIER : 'Panier',
		  CONNEXION : 'Connexion',
		  CONNECTE : 'Connect&eacute; en tant que ',
		  CONNEXION_FAILED : 'Email ou mot de passe invalide',
		  PARTICULIERS : 'Particuliers',
		  G_UTILISATEURS : 'Groupe d\'utilisateurs',
		  ENTREPRISES : 'Entreprises',
		  CONNECTED : 'Connect&eacute; en tant que',
		  DECONNEXION : 'D&eacuteconnexion',
		  A_PROPOS : '&Agrave; propos',
		  A_PARTIR_DE : '&Agrave; partir de',
		  TAXES : 'HT/mois',
		  SIGNUP : 'S\'inscrire',
		  REMEMBER_ME : 'Se souvenir de moi',
		  NOT_A_MEMBER : 'Pas encore membre ?',
		  SAVE : "Sauvegarde",
		  PASSWORD : "Mot de passe",
		  OLD : "Vieux",
		  NEW : "Nouveau",
		  CHECK : "V&eacute;rifier",
		  ADDRESS : "Adresse",
		  COMPLETED_ORDERS : "Commandes termin&eacute;es",
		  CURRENT_ORDERS : "Commandes en cours",
		  FIRST_NAME : "Pr&eacute;nom",
		  LAST_NAME : "Nom",
		  CITY : "Ville",
		  POSTAL_CODE : "Code postal",
		  HISTORIQUE : "Historique des commandes",
		  PARAMETRES : "Param&egrave;tres compte",
		  PRODUCT : "Produit",
		  PRICE : "Prix",
		  QUANTITY : "Quantit&eacute;",
		  VOIR_PLUS: "En voir plus >",
		  PAYEMENT: "Payement",
	      PRODUIT_AJOUTE : "Produit ajout&eacute;!"
	  });
	  
	  $translateProvider.preferredLanguage('fr');
	});
	
	scotchApp.controller('Ctrl', function ($scope, $translate) {
		  $scope.changeLanguage = function (key) {
		    $translate.use(key);
		  };
	});