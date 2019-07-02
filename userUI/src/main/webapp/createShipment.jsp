<!DOCTYPE html>
<html>
<head>
<title>Create User</title>
</head>
<body>
	<div style="padding-right:50px;font-family:monospace;">
		<h2>Create Shipment</h2>
		<form action="rest/shipmentInfo" method="POST">
			<div style="width: 100px; text-align:left;">
				<div style="padding:15px;">
					Equipment ID: <input name="id" />
				</div>
				<div style="padding:10px;">
					Description: <input name="description" />
				</div>
				<div style="padding:10px;">
					Quantity: <input name="quantity" />
				</div>
				<div style="padding:10px;">
					Date Of Shipment: <input name="dateOfShipment" />
				</div>
				
				<div>
				<h3>Please state some type and write this field in those ways as shown below</h3>				
				PLANNED
				IN_PROGRESS
				DONE
				</div>
				<div style="padding:0px;">
				   Type: <input name="state" />
				</div>
				<div style="padding:20px;text-align:center">
					<input type="submit" value="Submit" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>