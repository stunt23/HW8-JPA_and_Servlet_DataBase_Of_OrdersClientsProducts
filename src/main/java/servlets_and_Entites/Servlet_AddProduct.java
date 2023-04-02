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

@WebServlet(name = "addProduct", value = "/addProduct")
public class Servlet_AddProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession httpSession = request.getSession();
        EntityManager em2 = (EntityManager)httpSession.getAttribute("entityManager");
        String name = request.getParameter("productName");
        String price = request.getParameter("productPrice");
        double d = Double.valueOf(price);

        Product product = new Product(name, d);

        em2.getTransaction().begin();
        try{
            em2.persist(product);
            em2.getTransaction().commit();
        } catch (Exception e){
            em2.getTransaction().rollback();
        }

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>"  + "Product was added" + "</h1>");
        out.println("<a href=\"/menu\">Press to continue</a>");
        out.println("</body></html>");

    }
}
