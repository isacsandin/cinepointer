<?

/*
-------------------------------------------------------------
	Servidor MySQL:     localhost
	Banco de dados:     bd01
	Usuario de acesso:  usuario
	Senha do usuario:   minicurso
*/

$dbserver  = "localhost";	// Servidor onde está o banco de dados
$dbname    = "twitter_bd";		// Nome do banco de dados MySQL 
$dbuser    = "usuarioMySQL";		// Uusuariosuario MySQL para conexao 
$dbpassw   = "naosei";	// Senha do usuario MySQL

$conexao = mysql_connect($dbserver,$dbuser,$dbpassw) or die(mysql_error());
mysql_select_db($dbname,$conexao) or die(mysql_error());

?>
