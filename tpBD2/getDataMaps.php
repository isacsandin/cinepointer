<?
    include "conecta.php";
    $sql = "select regiao,regiao_id,count(regiao_id) as count from tweets natural join regioes group by regiao_id order by regiao_id";
    $qry = mysql_query($sql) or die(mysql_error());
    
    $estados = array ('','Distrito Federal','São Paulo, Minas','Pernanbuco','Rio Grande do Sul','Manaus');

    if (mysql_num_rows($qry)>0) {
            $string='{"cols": [{"id": "estado", "label": "Estado", "type": "string"},{"id": "regiao", "label": "Região", "type": "string"},{"id":"count", "label": "Tweets", "type": "number"}],"rows": [';
		$tam = mysql_num_rows($qry);
		$i = 0;
		while($R=mysql_fetch_object($qry)) {
                        $regiao = $R->regiao;
                        $count = $R->count;
                        $regiao_id = $R->regiao_id;
                        $string.='{"c":[{"v":"'.$estados[$regiao_id].'"},{"v":"'.$regiao.'"}, {"v":"'.$count.'"}]}';
                        if(++$i < $tam){$string.=',';}
                        }
                        $string.=']}';
                        echo $string;

    }else{
	echo "";
}
?>
