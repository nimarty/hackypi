<?php 
$pin = chop(file_get_contents("/etc/hems"));
if ($_GET["code"] == $pin) {              
    header("Location: /disconnected.php");
    die();
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home energy management system 4.0</title>
</head>
<body>
<div align="center">
    <table cellpadding="0" cellspacing="0">
<?php if (isset($_GET["code"])) { ?>
        <tr>
            <td colspan="2">
                    <div align="center" style="font-family: sans-serif; color: red">Invalid access code! Your action has
                        been logged.
                    </div>
                </td>
            </tr>
<?php }; ?>   
        <tr style="font-size: 0">
            <td><img src="picture.png"/></td>
            <td><img src="spacer.png" width="50" height="100"/><img src="title.png"/><img src="spacer.png" width="50"
                                                                                          height="100"/>
            </th>
        </tr>
        <tr>
            <td bgcolor="616C38"></td>
            <td align="center">
                <br/>
                <div style="font-family: sans-serif; font-size: large">
                    Enter access code to disconnect consumers
                </div>
                <br/>
                <table>
                    <tr>
                        <td>
                            <form action="disconnect.php" onchange="" method="get" id="form1">
                                <input type="password" id="fname" name="code" pattern="^[0-9]{4}$">
                            </form>
                            <form action="index.php" onchange="" method="get" id="form2">
                            </form>
                        </td>
                        <td>
                            <button enabled type="submit" form="form1">Disconnect</button>
                            <button enabled type="submit" form="form2">Abort</button>
                        </td>
                    </tr>
                </table>
                <br/>
                <div style="font-family: sans-serif; font-size: small; color: gray">
                    The access code is printed on your energy controller.
                </div>
                <br/>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
