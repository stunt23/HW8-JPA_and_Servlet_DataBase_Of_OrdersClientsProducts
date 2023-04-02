package servlets_and_Entites;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addClient", value = "/addClient")
public class Servlet_AddClient extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession httpSession = request.getSession();
        EntityManager em2 = (EntityManager)httpSession.getAttribute("entityManager");
        String name = request.getParameter("clientName");
        String email = request.getParameter("clientEMail");

        Client client = new Client(name, email);

        em2.getTransaction().begin();
        try{
            em2.persist(client);
            em2.getTransaction().commit();
        } catch (Exception e){
            em2.getTransaction().rollback();
        }

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>"  + "Client was added" + "</h1>");
        out.println("<a href=\"/menu\">Press to continue</a>");
        out.println("</body></html>");

    }
}
