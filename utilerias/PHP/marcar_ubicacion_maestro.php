<?PHP
$hostname="localhost";
$database="id2716259_bd_cubikate";
$username="id2716259_root";
$password="Admin123!";
//$json=array();
//	if(isset($_GET["RUT"])&&($_GET["longitud"]) && isset($_GET["latitud"])){
        $RUT_maestro=$_POST['RUT'];
        $longitud=$_POST['longitud'];
        $latitud=$_POST['latitud'];
		
		$conexion=mysqli_connect($hostname,$username,$password,$database);
        
        //$consulta="UPDATE tabla_Maestros SET latitud='{$latitud}', longitud='{$longitud}' WHERE RUT_maestro= '{$RUT_maestro}'"; 
        $consulta="UPDATE tabla_Maestros SET latitud='" .$latitud. "', longitud='" .$longitud. "' WHERE RUT_maestro= '" .$RUT_maestro. "'"; 
		mysqli_query($conexion,$consulta) or die (mysqli_error());
        mysqli_close($conexion);
//  }
?>

