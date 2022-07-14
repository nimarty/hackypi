<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home energy management system 4.0</title>
</head>
<body>
<div align="center">
    <table cellpadding="0" cellspacing="0">
        <tr style="font-size: 0">
            <td><img src="picture.png"/></td>
            <td><img src="spacer.png" width="50" height="100"/><img src="title.png"/><img src="spacer.png" width="50"
                                                                                          height="100"/></td>
        </tr>
        <tr>
            <td bgcolor="616C38"></td>
            <td align="center">
                <div style="font-family: sans-serif; font-size: x-large">
                    <br/>
                    <table>
                        <tr>
                            <td align="right">Energy production:</td>
                            <td>
                                <div style="color: green"> <?php echo rand(347, 352); ?> W</div>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">Energy consumption:</td>
                            <td>
                                <div style="color: red"> <?php echo rand(128, 132); ?> W</div>
                            </td>
                        </tr>
                    </table>
                </div>
                <br/>
                <form action="disconnect.php" onchange="" method="get" id="form1">
                </form>
                <button enabled type="submit" form="form1">Disconnect consumers</button>
                <br/><br/>
            </td>
        </tr>
    </table>
</div>
</body>
</html>