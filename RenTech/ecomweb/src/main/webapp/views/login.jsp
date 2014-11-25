<section id="form">
	<!--form-->
	<div class="container">
		<div class="row">
			<div class="col-sm-4 col-sm-offset-1">
				<div class="login-form">
					<!--login form-->
					<h2>
						<span translate="CONNEXION"> Se connecter</span>
					</h2>
					<form ng-controller="Cntrl" ng-submit="changeView('connected')">
						<div class="input-group margin-bottom-sm">
							<span class="input-group-addon">
								<i class="fa fa-envelope-o fa-fw"></i>
							</span> 
							<input ng-model="username" class="form-control"	type="text" placeholder="Email address" name="email">
						</div>
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
							<input ng-model="userpwd" class="form-control" type="password" placeholder="Password" name="password">
						</div>
						<span> 
							<input type="checkbox" class="checkbox"> 
							<span translate="REMEMBER_ME"> Se souvenir de moi</span>
						</span>
						
						<button type="submit" class="btn btn-primary pull-right">
							<span translate="CONNEXION">Se connecter</span>
						</button>		
				
					</form>
				</div>
				<!--/login form-->
			</div>

			<div class="col-sm-4">
				<div class="signup-form">
					<!--sign up form-->
					<h2><span translate="NOT_A_MEMBER"> Pas encore membre ?</span></h2>
					<form action="RegisterServlet" method="POST">
						<div class="input-group margin-bottom-sm">
							<span class="input-group-addon"><i class="fa fa-user"></i></span>
							<input class="form-control" type="text" placeholder="Name" name="name">
						</div>
						<div class="input-group margin-bottom-sm">
							<span class="input-group-addon"><i
								class="fa fa-envelope-o fa-fw"></i></span> <input class="form-control"
								type="text" placeholder="Email address" name="email">
						</div>
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
							<input class="form-control" type="password"	placeholder="Password" name="password">
						</div>
						<button type="submit" class="btn btn-primary pull-right"><span translate="SIGNUP">S'inscrire</span></button>
					</form>
				</div>
				<!--/sign up form-->
			</div>
		</div>
	</div>
</section>
<!--/form-->
