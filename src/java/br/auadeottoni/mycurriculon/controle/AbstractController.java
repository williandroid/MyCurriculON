package br.auadeottoni.mycurriculon.controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tassioauad
 */
public class AbstractController extends HttpServlet {

    private static final String TEMPLATE_PATH = "template.jsp";
    private List<String> infoMessages = new ArrayList<>();
    private List<String> successMessages = new ArrayList<>();
    private List<String> errorMessages = new ArrayList<>();
    private String view;
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        /**
         * Enviando as mensagens para serem exibidas na view
         */
        request.setAttribute("errorMessages", errorMessages);
        request.setAttribute("successMessages", successMessages);
        request.setAttribute("infoMessages", infoMessages);
        
        /**
         * Dispatching para view
         */
        RequestDispatcher dispatcher = request.getRequestDispatcher(TEMPLATE_PATH);
        request.setAttribute("view", view);
        dispatcher.forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void setInfoMessages(String infoMessage) {
        this.infoMessages.add(infoMessage);
    }

    public void setSuccessMessages(String successMessage) {
        this.successMessages.add(successMessage);
    }

    public void setErrorMessages(String errorMessage) {
        this.errorMessages.add(errorMessage);
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
    
    public void clearMessages() {
        infoMessages = new ArrayList<>();
        successMessages = new ArrayList<>();
        errorMessages = new ArrayList<>();
    }
}
