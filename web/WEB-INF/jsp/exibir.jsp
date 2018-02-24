<%-- 
    Document   : exibir
    Created on : 23/02/2018, 03:33:26
    Author     : Weverton
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/css/bootstrap.css"/> " rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/resources/css/style.css"/> " rel="stylesheet" type="text/css"/>
        <script src="<c:url value="/resources/js/jquery.js"/> "></script>

        <script>
            $(document).ready(function () {
                $.ajax({
//                    pagina para obter o obj json
                    url: "retorno",
//                    definindo o tipo do retorno da pagina para json
                    dataType: 'json',
//                    se ocorrer tudo certo faça:
                    success: function (data) {
//                        percorrendo o objeto json
                        $.each(data, function (idx, obj) {
                            $("#tabela > tbody ").append('<tr><td>' + obj.id + '</td><td>' + obj.nome + '</td><td>' + obj.endereco + '</td><td>' + obj.email + '</td><td>' + obj.telefone + '</td></tr>');
                        });
                    }
                });
            });
        </script>

        <title>Lista de Cadastros</title>
    </head>
    <body>
        <div class="container">
            <table class="table" id="tabela">
                <thead>
                <th>id</th>
                <th>nome</th>
                <th>endereco</th>
                <th>email</th>
                <th>telefone</th>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </body>
</html>