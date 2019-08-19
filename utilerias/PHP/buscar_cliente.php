<?php 
header( 'Content-Type: text/html;charset=utf-8' );

$hostname="localhost";
$database="id2716259_bd_cubikate";
$username="id2716259_root";
$password="Admin123!";

$json=array();

$conexion=mysqli_connect($hostname,$username,$password,$database);

//$consulta = "SELECT electrico FROM tabla_BusServicios  WHERE estado=1";

//$consulta = "SELECT usuario FROM tabla_Cliente"; //  WHERE estado=1";

$consulta = "SELECT tabla_BusServicios.ID_cliente, concat_ws(' ', tabla_Cliente.nombres, tabla_Cliente.apellidos) as nombre ";
$consulta .= "FROM tabla_BusServicios INNER JOIN tabla_Cliente ON tabla_BusServicios.ID_cliente=tabla_Cliente.ID_cliente ";
$consulta .= "WHERE tabla_BusServicios.estado = 1";


$conexion->set_charset("utf8");

$resultado=mysqli_query($conexion,$consulta);

while ($json = $resultado->fetch_array(MYSQLI_NUM)) {
echo json_encode($json);
}

mysqli_close($conexion);
    
?>