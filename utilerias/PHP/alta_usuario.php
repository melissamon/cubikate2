<?PHP
$hostname="localhost";
$database="id2716259_bd_cubikate";
$username="id2716259_root";
$password="Admin123!";
$json=array();
	if(isset($_GET["usuario"])&&($_GET["RUT_Cliente"]) && isset($_GET["nombres"]) && isset($_GET["apellidos"])
       && isset($_GET["clave"]) && isset($_GET["direccion"]) && isset($_GET["telefono"]) && isset($_GET["email"])){
        $usuario=$_GET['usuario'];
        $RUT_Cliente=$_GET['RUT_Cliente'];
        $nombres=$_GET['nombres'];
        $apellidos=$_GET['apellidos'];
        $clave=$_GET['clave'];
        $direccion=$_GET['direccion'];
        $telefono=$_GET['telefono'];
        $email=$_GET['email'];
		
		$conexion=mysqli_connect($hostname,$username,$password,$database);
		
		$consulta="INSERT INTO tabla_Cliente(usuario, RUT_Cliente, nombres, apellidos, clave, direccion, telefono, email) 
        VALUES ('{$usuario}','{$RUT_Cliente}','{$nombres}','{$apellidos}','{$clave}','{$direccion}','{$telefono}','{$email}')";
		$resultado=mysqli_query($conexion,$consulta);

       
		if($consulta){
		   $consulta="SELECT * FROM tabla_Cliente  WHERE usuario='{$usuario}'";
		   $resultado=mysqli_query($conexion,$consulta);

			if($reg=mysqli_fetch_array($resultado)){
				$json['datos'][]=$reg;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}

		else{
			$results["usuario"]='';
			$results["RUT_Cliente"]='';
			$results["nombres"]='';
			$results["apellidos"]='';
			$results["clave"]='';
			$results["direccion"]='';
			$results["telefono"]='';
			$results["email"]='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
		
	}
	else{   
			$results["usuario"]='';
			$results["RUT_Cliente"]='';
			$results["nombres"]='';
			$results["apellidos"]='';
			$results["clave"]='';
			$results["direccion"]='';
			$results["telefono"]='';
			$results["email"]='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
?>

