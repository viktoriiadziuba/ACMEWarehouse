<!DOCTYPE html>
<html>
<head>
<title>Create User</title>
</head>
<body>
	<div style="padding-right:50px;font-family:monospace;">
		<h2>Create Equipment</h2>
		<form action="rest/equipmentInfo" method="POST">
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
				
				<h3>Please select some type and write this field in those ways as shown below</h3>				
				FORKLIFT
				HAND_TRUCKS 
				SERVICE_CARTS
				LADDER 
				PACKING_TABLE 
				STRATCH_WRAP_MACHINES
				SHELF
				<div style="padding:0px;">
				   Type: <input name="type" />
				</div>
				<div style="padding:20px;text-align:center">
					<input type="submit" value="Submit" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>