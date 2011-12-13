<?
    include "conecta.php";

function unshort($url) {
	$_host = $url;

	if (substr($_host, 0, 7) == 'http://') {
		$_host = substr($_host, 7);
	}elseif (substr($_host, 0, 8) == 'https://') {
		$ssl = true;
		$_host = substr($_host, 8);
	}

	$pos = strpos($_host, '/');

	if ($pos === false) {
		$_host .= '/';
		$pos = strpos($_host, '/');
	}

	$host = substr($_host, 0, $pos);

	$request = substr($_host, $pos);

	if ($ssl === true) {
		$fp = @stream_socket_client("tcp://{$host}:443");
	}else{
		$fp = @stream_socket_client("tcp://{$host}:80");
	}

	if (!$fp) return false;

	fwrite($fp, "GET {$request} HTTP/1.0\r\nHost: {$host}\r\nAccept: */*\r\n\r\n");

	$header = fread($fp, 8192);

	preg_match('/location\:[\s]*(http[\S]*)[\s]*/mi', $header, $arr);

	fclose($fp);
	
	return $arr[1];
}

echo "opa!";
die();

    $sql = "SELECT * from urls";
    $qry = mysql_query($sql) or die(mysql_error());

    if (mysql_num_rows($qry)>0) {
		while($R=mysql_fetch_object($qry)) {
						echo "skllsknslksnlkn";
                        $url_id = $R->url_id;
                        $url = $R->url;
                        $url_longa = unshort($url);
                        $sql2 = "UPDATE urls set url_longa=".$url_longa." WHERE url_id=".$url_id;
						mysql_query($sql2) or die(mysql_error());
                      }  
    }else{
	echo "erro!";
}



?>