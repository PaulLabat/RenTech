<div class="container">
	<ul class="breadcrumb">
		<li><a href="#">Home</a></li>
		<li class="active"><span translate="PANIER">Panier</span></li>
	</ul>

	<div ng-hide="emptyCard()" class="row">
		<div class="col-sm-12 col-md-10 col-md-offset-1">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Produit</th>
						<th class="text-center"><span translate="PRICE">Prix</span></th>
						<th> </th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="product in products">
						<td class="col-sm-8 col-md-6">
							<div class="media">
								<a class="thumbnail pull-left" href="#"> <img
									class="media-object"
									src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png"
									style="width: 72px; height: 72px;">
								</a>
								<div class="media-body">
									<h4 class="media-heading">
										<a href="#git">{{product.name}}</a>
									</h4>
								</div>
							</div>
						</td>

						<td class="col-sm-1 col-md-1 text-center"><strong>{{product.prix}}&#128;</strong></td>
						<td class="col-sm-1 col-md-1">
							<button ng-click="erase()" type="button" class="btn btn-danger">
								<span class="glyphicon glyphicon-trash"> </span>
							</button>
						</td>
					</tr>

					<tr>
						<td>
							<h3>Total</h3> 
						</td>
						<td class="text-right"><h3>
								<strong>{{total}}&#128;</strong>
							</h3></td>
						<td></td>
					</tr>
					<tr>
						<td> </td>

						<td><a href="#orderForm">
								<button type="button" class="btn btn-success">
									<span translate="PAYEMENT"></span>
								</button>
						</a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div ng-show="emptyCard()">
		<span translate="PANIER_VIDE"></span>
	</div>
</div>