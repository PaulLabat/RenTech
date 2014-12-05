<section id="form">
	<!--form-->
	<div class="container">
		<div class="row">
			<div ng-controller="loginController" class="col-sm-4 col-sm-offset-1">
				<div id="login-div" class="login-form">
					<!--login form-->
					<h2>
						<span translate="CONNEXION"> Se connecter</span>
					</h2>

					<h3>
						<span id="s_connectionFailed" style="display:none" translate="CONNEXION_FAILED"> </span>
					</h3>
					
					<div class="input-group margin-bottom-sm">
							<span class="input-group-addon"><i
								class="fa fa-envelope-o fa-fw"></i></span> <input class="form-control"
								type="text" placeholder="Email address" id="emailConnect">
						</div>
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
							<input class="form-control" type="password" placeholder="Password" id="passwordConnect">

						</div>
	
						<input translate="REMEMBER_ME" type="checkbox">  Se souvenir de moi<br>
						<button id="connect" ng-click="connectUser()" type="submit" class="btn btn-primary pull-right">

							<span translate="CONNEXION">Se connecter</span>
						</button>		
				</div>
				<!--/login form-->
			</div>

			<div ng-controller="registerController" class="col-sm-4">
				<div class="signup-form">
					<!--sign up form-->
					<h2><span translate="NOT_A_MEMBER"> Pas encore membre ?</span></h2>
						<div class="input-group margin-bottom-sm">
							<span class="input-group-addon"><i class="fa fa-user"></i></span>
							<input class="form-control" type="text" placeholder="Name" id="nameRegister">
						</div>
						<div class="input-group margin-bottom-sm">
							<span class="input-group-addon"><i
								class="fa fa-envelope-o fa-fw"></i></span> <input class="form-control"
								type="text" placeholder="Email address" id="emailRegister">
						</div>
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
							<input class="form-control" type="password"	placeholder="Password" id="passwordRegister">
						</div>
						<button ng-click="createUser();" class="btn btn-primary pull-right"><span translate="SIGNUP">S'inscrire</span></button>
					
				</div>
				<!--/sign up form-->
				
			</div>
		</div>
	</div>
</section>
<!--/form-->
