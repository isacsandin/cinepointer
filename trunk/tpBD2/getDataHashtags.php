<?
    include "conecta.php";
    $sql = "SELECT hashtag,count(hashtag) as count FROM tweets_hashtags natural join hashtags group by hashtag order by count desc limit 0,30";
    $qry = mysql_query($sql) or die(mysql_error());

	

//function mysql2json($mysql_result){
//     $json=array();
//     $field_names = array();
//     $fields = mysql_num_fields($mysql_result);
//     for($x=0;$x<$fields;$x++){
//          $field_name = mysql_fetch_field($mysql_result, $x);
//          if($field_name){
//               $field_names[$x]["id"]=$field_name->name;
//               if ($field_name->type == "int")
//				$field_names[$x]["type"]="numeric";
//			else
//				$field_names[$x]["type"]=$field_name->type;
//          }
//     }
//     $json["cols"] = $field_names;
//
//     $n_rows = mysql_num_rows($mysql_result);
//     for($x=0;$x<$n_rows;$x++){
//          $row = mysql_fetch_array($mysql_result);
//          for($y=0;$y<$fields;$y++) {
//			  $row_array[$y]["v"] = $row[$y]; 
//		   }
//		   $rows[$x]["c"] = $row_array;
//		}
//	 $json["rows"] = $rows;	  
//     return json_encode($json);
//}
//
//$json =  mysql2json($qry);
//echo $json;


    if (mysql_num_rows($qry)>0) {
        $string='{"cols": [{"id": "hashtag", "label": "Hashtags", "type": "string"},{"id":  "count", "label": "Frequência", "type": "number"}],"rows": [';
		$tam = mysql_num_rows($qry);
		$i = 0;
		while($R=mysql_fetch_object($qry)) {
                        $termo = $R->termo;
                        $count = $R->count;
                        $string.='{"c":[{"v":"'.$termo.'"}, {"v":"'.$count.'"}]}';
                        if(++$i < $tam){$string.=',';}
                        }
                        $string.=']}';
                        echo $string;

    }else{
	echo "";
}


/*

	$main_arr = array();
		while($row = mysql_fetch_assoc($qry))
		{   
			foreach($row as $key => $value)
			{    $arr[$key] = $value; }

			$main_arr[] = $arr;
		}

		$json = json_encode($main_arr);
		echo $json;
*/
?>
