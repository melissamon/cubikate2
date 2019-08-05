<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Evaluación Semana 5</title>
        <meta charset="utf-8">
        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <meta name="robots" content="index,follow">
        <meta name="description" content="Evaluación Semana 5">
        <meta name="keywords" content="Cubikate, Buscador de maestros">
        
        <title>Registro Cubikate</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" />        
        
    </head>
    <body>


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
    
    //echo 'Gracias por su registro, ahora puede disfrutar de nuestra App Cubikate.';
}

?>

   <div class="container">    
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                <div class="panel-heading">
                    <div class="panel-title">Protocolo de uso correcto del Sistema Cubikate</div>
                </div>     

                <div style="padding-top:30px" class="panel-body" >
                    <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                    <form id="loginform" class="form-horizontal" role="form" enctype="multipart/form-data" method="post" action="">
                        <div class="container-fluid">
                            <div class="alert alert-success">«CUBIKATE ES UNA APLICACIÓN DE BÚSQUEDA DE MAESTROS CERTIFICADOS PARA TELÉFONOS MÓVILES DE ÚLTIMA GENERACIÓN, LOS LLAMADOS SMARTPHONES. PERMITE EL CONTACTO ENTRE UN USUARIO Y UN MAESTRO. SU FUNCIONAMIENTO ES IDÉNTICO AL DE LOS PROGRAMAS COMO UBER O CABIFY. ES IMPRESCINDIBLE QUE, TANTO EL USUARIO COMO EL MAESTRO, TENGAN INSTALADA ESTA APLICACIÓN EN SU TELÉFONO»</div>
                            <div class="alert alert-info">Y estos con los consejos sugeridos para un protocolo de buen uso de esa aplicación:<br><br>
                            
1. Úselo en ocasiones de real necesidad. No hay justificación alguna que pueda convertirla en una obligación de uso diario, mucho menos cada hora.<br><br>

2. Sea breve y al grano, es decir, no mande mensajes largos. Más allá de unos 300 caracteres todo es irrelevante.<br><br>

3. Respete la ortografía. No está mal usar abreviaciones, pero, cuando escriba, hágalo con propiedad y eso incluye evitar malas palabras (a menos que sea absolutamente necesario).<br><br>

4. Asimile el efecto que tiene el exceso de mensajes poco importantes y que es el de ocultar los mensajes urgentes e importantes. No ocupe su tiempo libre en mandar un mensaje detrás de otro.<br><br>

5. No exija que sus mensajes ocupen en los otros más de unos pocos segundos de su tiempo. Ellos deben ser directos y al grano. Si quiere mandar mensajes más amplios, use otros medios como Facebook o su correo.<br><br>

6. No use la app mientras camina, ni mientras conduce un auto. Los mensajes requieren una buena parte de su atención que usted quita a lo que más importa. Nunca hay tanta urgencia como para responder al instante.<br><br>

En resumen, para usar Cubikate como el resto de las herramientas de comunicación, use las neuronas y sea razonable. Cada mensaje que envía es una exigencia de tiempo ajeno que debe ser respetado.<br><br>
</div>
                            <div class="alert alert-info">cubikate.uniacc@gmail.com es el correo para comunicarse con nosotros.</div>
                            
                            <div class="alert alert-success">Gracias por su registro, desde ahora puede disfrutar de su App Cubikate.<br><br>
                            Recuerde que su clave es: <?PHP'{$codigo_act}'?></div>
                            
                        </div>                   
                    </form>     
                </div>                     
            </div>  
        </div>
    </div>

    <script src="js/bootstrap.min.js"></script>
    <script type='text/javascript' src="js/jquery-1.12.4.min.js"></script>

    </body>
</html>

