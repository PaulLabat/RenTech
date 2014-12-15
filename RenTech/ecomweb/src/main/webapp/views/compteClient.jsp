<div class="container">

	<ol class="breadcrumb">
		<li><a href="#">Home</a></li>
		<li class="active"><span translate="PARAMETRES"></span></li>
	</ol>
	
	<ul class="nav nav-pills" role="tablist">
		<li role="presentation" class="active"><a href="#compte">Profile</a></li>
		<li role="presentation"><a href="#compte/adresse"><span translate="ADDRESS">Adresse</span></a></li>
		<li role="presentation"><a href="#compte/commandes"><span translate="CURRENT_ORDERS">Commandes en cours</span></a></li>
		<li role="presentation"><a href="#compte/historique"><span translate="COMPLETED_ORDERS">Commandes termin&eacute;es</span></a></li>
	</ul>


	<form class="form-horizontal" role="form">
		<fieldset>

			<br>
			<hr>
			<!-- Text input--> 
			<div class="form-group">
				<label class="col-sm-3 control-label" for="textinput"><span translate="FIRST_NAME">Pr�nom</span></label>
				<div class="col-sm-8">
					{{firstname}}
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-sm-3 control-label" for="textinput"><span translate="LAST_NAME">Nom</span></label>
				<div class="col-sm-8">
					{{name}}
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-sm-3 control-label" for="textinput">E-mail</label>
				<div class="col-sm-8">
					{{email}}
				</div>
			</div>

			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<div class="pull-right">
						<button class="btn btn-primary" ng-click="openDialogProfile()"><span translate="MODIFIER"></span></button>
					</div>
				</div>
			</div>
			
			<hr>
			
			<div class="form-group">
				<label class="col-sm-3 control-label" for="textinput"><span translate="PASSWORD"></span></label>
				<div class="col-sm-offset-2 col-sm-10">
					{{pwd}}
		
					<div class="pull-right">
						<button class="btn btn-primary" ng-click="openDialogPwd()"><span translate="MODIFIER"></span></button>
					</div>
				</div>

			</div>
			
			<hr>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<div class="pull-right">
						<button class="btn btn-default" ng-click="deleteAccount()">
							<i class="glyphicon glyphicon-remove-circle"></i><span translate="REMOVE_ACCOUNT"></span>
						</button>
					</div>
				</div>
			</div>
		</fieldset>
	</form>
</div>
<!-- /.col-lg-12 -->