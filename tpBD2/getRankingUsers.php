<?
    include "conecta.php";
    $sql = "SELECT from_user,count(from_user) as c FROM tweets group by from_user order by c desc limit 0,20";
    $qry = mysql_query($sql) or die(mysql_error());

    if (mysql_num_rows($qry)>0) {
		while($R=mysql_fetch_object($qry)) {
                        $hashtag = $R->hashtag;
                        $count = $R->count;
                        $string.='{"c":[{"v":"'.$hashtag.'"}, {"v":"'.$count.'"}]}';
                        if(++$i < $tam){$string.=',';}
                        }
                        $string.=']}';
                        echo $string;

    }else{
	echo "";
}
?>
