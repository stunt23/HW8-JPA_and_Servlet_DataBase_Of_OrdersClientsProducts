package servlets_and_Entites;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "deleteClient", value = "/deleteClient")
public class Servlet_DeleteClient extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession httpSession = request.getSession();
        EntityManager em2 = (EntityManager)httpSession.getAttribute("entityManager");
        String email = request.getParameter("clientEMail");

        Query query2 = em2.createQuery("SELECT e FROM Client e WHERE e.email = :param ");
        query2.setParameter("param", email);
        Client client = (Client)query2.getSingleResult();

        em2.getTransaction().begin();
        try{
            em2.remove(client);
            em2.getTransaction().commit();
        } catch (Exception e){
            em2.getTransaction().rollback();
        }

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>"  + "Client was removed" + "</h1>");
        out.println("<a href=\"/menu\">Press to continue</a>");
        out.println("</body></html>");

    }
}
