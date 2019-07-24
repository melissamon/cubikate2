<?PHP

$hostname="localhost";
$database="id2716259_bd_cubikate";
$username="id2716259_root";
$password="Admin123!";

//$json=array();

if(isset($_GET["codigo_act"])&&($_GET["RUT_maestro"])){
    $codigo_act=$_GET['codigo_act'];
    $RUT_maestro=$_GET['RUT_maestro'];

    $conexion=mysqli_connect($hostname,$username,$password,$database);

    $consulta="UPDATE tabla_Maestros SET Activo=1, codigo_act='', clave='{$codigo_act}' WHERE RUT_maestro= '{$RUT_maestro}' and codigo_act= '{$codigo_act}'"; 
    $resultado=mysqli_query($conexion,$consulta);
    
    echo 'Gracias por su registro, ahora puede disfrutar de nuestra App Cubikate.';
}

?>
