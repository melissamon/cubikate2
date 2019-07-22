<?PHP
$hostname="localhost";
$database="id2716259_bd_cubikate";
$username="id2716259_root";
$password="Admin123!";
$json=array();
	if(isset($_GET["usuario"]) && isset($_GET["clave"])){
		$usuario=$_GET['usuario'];
		$clave=$_GET['clave'];
		
		$conexion=mysqli_connect($hostname,$username,$password,$database);
		
		$consulta="SELECT usuario, clave, concat(nombres, ' ', apellidos) as names FROM tabla_Cliente WHERE usuario= '{$usuario}' AND clave = '{$clave}'";
        
		$resultado=mysqli_query($conexion,$consulta);

		if($consulta){

			if($reg=mysqli_fetch_array($resultado)){
				$json['datos'][]=$reg;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}

		else{
			$results["usuario"]='';
			$results["clave"]='';
			$results["names"]='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
		
	}
	else{
		   	$results["usuario"]='';
			$results["clave"]='';
			$results["names"]='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
?>