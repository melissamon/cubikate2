<?php 

//session_start();

$hostname="localhost";
$database="id2716259_bd_cubikate";
$username="id2716259_root";
$password="Admin123!";

$json=array();

if(isset($_GET["email"])){
    //$usuario=$_GET['RUT_maestro'];
    $Correo=$_GET['email'];


    //$Correo = "cecheverria21@gmail.com";
    $codigo = rand(1111,9999);

    require("PHPMailer_5.2.4/class.phpmailer.php");

    $mail = new PHPMailer();

    //Luego tenemos que iniciar la validación por SMTP:
    $mail->IsSMTP();
    $mail->SMTPDebug = 2;
    $mail->Host = "smtp.gmail.com"; // A RELLENAR. Aquí pondremos el SMTP a utilizar. Por ej. mail.midominio.com "pop.gmail.com";
    $mail->SMTPSecure="ssl";
    $mail->SMTPAuth = true;
    $mail->Username = "cubikate.uniacc@gmail.com"; //doc.elec1@gmail.com"; // A RELLENAR. Email de la cuenta de correo. ej.info@midominio.com La cuenta de correo debe ser creada previamente. 

    $mail->Password = "clave1604"; // A RELLENAR. Aqui pondremos la contraseña de la cuenta de correo
    $mail->Port = 465; // Puerto de conexión al servidor de envio. 995 -- 465

    $mail->From = "cubikate.uniacc@gmail.com"; // A RELLENARDesde donde enviamos (Para mostrar). Puede ser el mismo que el email creado previamente.
    $mail->FromName = "Cristian Echeverria"; //A RELLENAR Nombre a mostrar del remitente. 
    $mail->AddAddress($Correo); 
    $mail->IsHTML(true); // El correo se envía como HTML 
    $mail->Subject = "Registro App Cubikate"; // Este es el titulo del email. 

    $body = "Estimado, para continuar con su registro "; 
    $body .= "necesita digitar el siguiente código de ingreso: " . $codigo; 

    $mail->Body = $body; // Mensaje a enviar. 
    $exito = $mail->Send(); // Envía el correo.
    if($exito){ 
        $results["Respuesta"]='Revise su correo para continuar';
        $json['datos'][]=$results;
        echo json_encode($json);
        }
    else
    { 
        $results["Respuesta"]='No fue posible enviar el correo';
        $json['datos'][]=$results;
        echo json_encode($json);
    }
}
else
{
    $results["Respuesta"]='No fue posible enviar el correo';
	$json['datos'][]=$results;
	echo json_encode($json);
    
}

?>