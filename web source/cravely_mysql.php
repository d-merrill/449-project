<?php
	// make sure mysql returns utf-8
	function utf8ize($d) {
		if (is_array($d)) {
			foreach ($d as $k => $v) {
				$d[$k] = utf8ize($v);
			}
		} else if (is_string ($d)) {
			return utf8_encode($d);
		}
		return $d;
	}
	
	// connect
	$connect = mysqli_connect("db764067919.hosting-data.io", "dbo764067919", "Password1!", "db764067919");
	
	if (mysqli_connect_errno()){
		die("Failed to connect to MySQL: " . mysqli_connect_error());
	};

		$query = "SELECT f.FoodID, f.FoodName, f.Origin, f.Description, COUNT(t.Tag) as TagCount 
		FROM food as f, tag as t
		WHERE f.FoodID = t.FoodID AND f.Description IS NOT NULL
		GROUP BY f.FoodID
		ORDER BY COUNT(t.Tag) DESC";
		
		$result = mysqli_query($connect, $query) or die(mysql_error());
		
		$data = array();
		if (mysqli_num_rows($result) > 0){
			
			while($row = mysqli_fetch_array($result)){
				
				$temp = array();
				$temp['foodID'] = (int)$row[0];
				$temp['foodName'] = $row[1];
				$temp['origin'] = $row[2];
				$temp['description'] = $row[3];
				
				array_push($data, $temp);
			}
			
			echo json_encode(utf8ize($data));
		}
	
	mysqli_close($connect);
?> 

