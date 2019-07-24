<?PHP
$hostname="localhost";
$database="id2716259_bd_cubikate";
$username="id2716259_root";
$password="Admin123!";
$json=array();
	if(isset($_GET["RUT_maestro"]) && isset($_GET["clave"])){
		$RUT_maestro=$_GET['RUT_maestro'];
		$clave=$_GET['clave'];
		
		$conexion=mysqli_connect($hostname,$username,$password,$database);
		
		$consulta="SELECT RUT_maestro, clave, concat(nombres, ' ', apellidos) as names, Activo FROM tabla_Maestros WHERE RUT_maestro= '{$RUT_maestro}' AND clave = '{$clave}'";
        
		$resultado=mysqli_query($conexion,$consulta);

		if($consulta){

			if($reg=mysqli_fetch_array($resultado)){
				$json['datos'][]=$reg;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}

		else{
			$results["RUT_maestro"]='';
			$results["clave"]='';
			$results["names"]='';
            $results["Activo"]='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
		
	}
	else{
		   	$results["RUT_maestro"]='';
			$results["clave"]='';
			$results["names"]='';
            $results["Activo"]='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
?>