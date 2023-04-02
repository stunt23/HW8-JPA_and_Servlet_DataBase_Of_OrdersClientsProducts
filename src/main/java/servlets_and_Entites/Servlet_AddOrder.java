package servlets_and_Entites;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addOrder", value = "/addOrder")
public class Servlet_AddOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession httpSession = request.getSession();
        EntityManager em2 = (EntityManager)httpSession.getAttribute("entityManager");
        String s1 = request.getParameter("orderSerialNumber");
        int orderSerialNumber = Integer.valueOf(s1);
        String s2 = request.getParameter("clientID");
        int clientID = Integer.valueOf(s2);
        String s3 = request.getParameter("productId");
        int productId = Integer.valueOf(s3);
        String s4 = request.getParameter("productQuantity");
        int productQuantity = Integer.valueOf(s4);

        Order order = new Order(em2,orderSerialNumber,clientID,productId,productQuantity);

        em2.getTransaction().begin();
        try{
            em2.persist(order);
            em2.getTransaction().commit();
        } catch (Exception e){
            em2.getTransaction().rollback();
        }

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>"  + "Order was added" + "</h1>");
        out.println("<a href=\"/menu\">Press to continue</a>");
        out.println("</body></html>");

    }
}
