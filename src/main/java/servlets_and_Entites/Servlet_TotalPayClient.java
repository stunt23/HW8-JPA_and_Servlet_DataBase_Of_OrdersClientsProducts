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

@WebServlet(name = "totalPayClientId", value = "/totalPayClientId")
public class Servlet_TotalPayClient extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession httpSession = request.getSession();
        EntityManager em2 = (EntityManager)httpSession.getAttribute("entityManager");
        String s1 = request.getParameter("clientId");
        Integer clientId = Integer.valueOf(s1);
        Query query2 = em2.createQuery("SELECT SUM(e.sumOfOrder) FROM Order e WHERE e.clientID = :param ");
        query2.setParameter("param", clientId);
        double payment = (Double) query2.getSingleResult();

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>"  + "Client " + clientId + " have do pay "+ payment + "</h1>");
        out.println("<a href=\"/menu\">Press to continue</a>");
        out.println("</body></html>");

    }
}
