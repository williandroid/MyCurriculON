<%@page import="java.util.ArrayList"%>
<%
    /**
     * Capturando as mensagens
     */
    ArrayList<String> successMessages = (ArrayList<String>) request.getAttribute("successMessages");
    ArrayList<String> errorMessages = (ArrayList<String>) request.getAttribute("errorMessages");
    ArrayList<String> infoMessages = (ArrayList<String>) request.getAttribute("infoMessages");
%>

<%
    /*
     * Exibindo mensagens de sucesso
     */
    for (String successMessage : successMessages) {
%>
<div class="alert alert-success"><p><strong><%= successMessage %></strong></p></div>
<%  } %>


<%
    /*
     * Exibindo mensagens de erro
     */
    for (String errorMessage : errorMessages) {
%>
<div class="alert alert-danger"><p><strong><%= errorMessage %></strong></p></div>
<%  } %>


<%
    /*
     * Exibindo mensagens informativas
     */
    for (String infoMessage : infoMessages) {
%>
<div class="alert alert-success"><p><strong><%= infoMessage %></strong></p></div>
<%  } %>