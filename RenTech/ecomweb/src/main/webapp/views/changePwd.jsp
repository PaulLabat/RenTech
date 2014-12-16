Veuillez rentrer les nouvelles informations : 

<br> <br> <br>
<!-- Text input-->
<div class="form-group">
	<label class="col-sm-3 control-label" for="textinput"><span translate="OLD">Ancien</span></label>
	<div class="col-sm-8">
		<input type="password" placeholder="" class="form-control" id="changePwd_old">
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label" for="textinput"><span translate="NEW">Nouveau</span></label>
	<div class="col-sm-8">
		<input type="password" placeholder="" class="form-control" id="changePwd_new">
	</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label" for="textinput"><span translate="CHECK">V&eacute;rifier</span></label>
	<div class="col-sm-8">
		<input type="password" placeholder="" class="form-control">
	</div>
</div>

<br><br>

<div class="form-group">
	<div class="col-sm-offset-2 col-sm-10">
		<div class="pull-right">
			<button class="btn btn-primary" ng-click="apply()"><span>Apply</span></button>
			<button class="btn btn-default" ng-click="closeDialog()"><span>Cancel</span></button>
		</div>
	</div>
</div>