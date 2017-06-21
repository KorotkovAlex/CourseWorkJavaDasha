<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="j_security_check" method="POST">
                <legend>Форма авторизации</legend>
                Введите login:<br/>
                <input type="text" name="j_username"><br/>
                Введите password:<br/>
                <input type="password" name="j_password"><br/>
                <br/>
                <input type="submit" value="Login" >
        </form>
        
    </body>
</html>
