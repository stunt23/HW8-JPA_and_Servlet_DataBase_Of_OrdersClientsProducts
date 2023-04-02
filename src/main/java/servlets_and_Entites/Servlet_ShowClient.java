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
import java.util.List;

@WebServlet(name = "showClient", value = "/showClient")
public class Servlet_ShowClient extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession httpSession = request.getSession();
        EntityManager em2 = (EntityManager)httpSession.getAttribute("entityManager");

        Query query = em2.createQuery(" FROM Client ", Client.class);
        List<Client> list = (List<Client>) query.getResultList();

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        for (Client c : list)
            out.println(c + "<br>");
        out.println("<a href=\"/menu\">Press to continue</a>");
        out.println("</body></html>");

    }
}
