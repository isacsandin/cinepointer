<?
    include "conecta.php";
    $sql = "SELECT termo,count(termo) as count FROM tweets_termos  natural join termos group by termo order by count desc limit 0,15";
    $qry = mysql_query($sql) or die(mysql_error());
    if (mysql_num_rows($qry)>0) {
             $string="{cols: [{id: 'termo', label: 'Termo', type: 'string'},{id:  'count', label: 'Frequência', type: 'number'}],rows: [";
		while ($R=mysql_fetch_object($qry)) {
                        $termo = $R->termo;
                        $count = $R->count;
                        $string.="{c:[{v:'".$termo."'}, {v:".$count."}]},";
                         }
                         $string.="]}";
                         echo $string;
    }else{
	echo "";
}
?>
