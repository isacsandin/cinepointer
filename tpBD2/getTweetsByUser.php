<?
    include "conecta.php";
	
	$user = $_GET["user"];

    $sql = "SELECT created_at,text from tweets natural join tweet_text where from_user=".$user." order by created_at desc";
    $qry = mysql_query($sql) or die(mysql_error());

    if (mysql_num_rows($qry)>0) {
		while($R=mysql_fetch_object($qry)) {
                        $created_at = $R->created_at;
                        $text = $R->text;
                        echo "<div class='tweetList'>".$created_at."&nbsp;".$text."</div>";
                      }  
    }else{
	echo "erro!";
}
?>