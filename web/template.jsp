<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MeuCurriculON</title>
    </head>
    <body>
        <section id="menu">
            <%@include file="menu.jsp" %>
        </section>
        
        <section id="mensagens">
            <%@include file="mensagem.jsp" %>
        </section>
        
        <section id="view">
            <% String view = (String) request.getAttribute("view");%>
            <jsp:include page="<%= view %>" />
        </section>
    </body>
</html>
