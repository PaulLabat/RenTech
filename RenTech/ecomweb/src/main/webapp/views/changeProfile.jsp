<br> <br> <br>
<!-- Text input-->
<div class="form-group">
	<label class="col-sm-3 control-label" for="textinput"><span translate="FIRST_NAME">Prénom</span></label>
	<div class="col-sm-8">
		<input type="text" placeholder="new first name" class="form-control" id="changeProfile_firstName">
	</div>
</div>

<!-- Text input-->
<div class="form-group">
	<label class="col-sm-3 control-label" for="textinput"><span translate="LAST_NAME">Nom</span></label>
	<div class="col-sm-8">
		<input type="text" placeholder="" class="form-control" id="changeProfile_name">
	</div>
</div>

<!-- Text input-->
<div class="form-group">
	<label class="col-sm-3 control-label" for="textinput">E-mail</label>
	<div class="col-sm-8">
		<input type="text" placeholder="" class="form-control" id="changeProfile_email">
	</div>
</div>


<div class="form-group">
	<div class="col-sm-offset-2 col-sm-10">
		<div class="pull-right">
			<button class="btn btn-primary" ng-click="apply()"><span>Apply</span></button>
			<button class="btn btn-default" ng-click="closeDialog()"><span>Cancel</span></button>
		</div>
	</div>
</div>