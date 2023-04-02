package servlets_and_Entites;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet_StartMenu", value = "/menu")
public class Servlet_StartMenu extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession httpSession = request.getSession();//кажется тут ТРУ
        Boolean check = (Boolean) httpSession.getAttribute("emWasCreated");

        if(check == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServletWithJPA_persistence");
            EntityManager em = emf.createEntityManager();
            httpSession.setAttribute("entityManager", em);
            boolean created = true;
            httpSession.setAttribute("emWasCreated", created);
            String path = request.getContextPath() + "/menu";
            response.sendRedirect(path);
        }

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<form action=\"/login\" , method=\"POST\"> C" +
                "Client's name: <input type=\"text\" name=\"clientName\">" +
                "Client's email: <input type=\"text\" name=\"clientEMail\"> " +
                "<button type=\"submit\" formaction=\"/addClient\">Add client to DB</button>" +
                "</form>");
        out.println("<form action=\"/login\" , method=\"POST\"> " +
                "Product name: <input type=\"text\" name=\"productName\">" +
                "Product price: <input type=\"text\" name=\"productPrice\"> " +
                "<button type=\"submit\" formaction=\"/addProduct\">Add product to DB</button>" +
                "</form>");
        out.println("<form action=\"/login\" , method=\"POST\"> " +
                "You should create few products and clients before creating an order" +
                "<br>" +
                "Order serial number: <input type=\"text\" name=\"orderSerialNumber\">" +
                "Order clientID: <input type=\"text\" name=\"clientID\">" +
                "Order productId: <input type=\"text\" name=\"productId\">" +
                "Order quantity of products: <input type=\"text\" name=\"productQuantity\">" +
                "<button type=\"submit\" formaction=\"/addOrder\">Add order to DB</button>" +
                "</form>" +
                "<br>");
        out.println("<form action=\"/login\" , method=\"POST\"> C" +
                "Delete Client by email" +
                "<br>" +
                "Client's email: <input type=\"text\" name=\"clientEMail\"> " +
                "<button type=\"submit\" formaction=\"/deleteClient\">Remove Client by his email</button>" +
                "</form>");
        out.println("<form action=\"/login\" , method=\"POST\"> C" +
                "Show all clients from DataBase    " +
                "<button type=\"submit\" formaction=\"/showClient\">Show clients</button>" +
                "</form>");
        out.println("<form action=\"/login\" , method=\"POST\"> C" +
                "Show total payment of the client by his clientId" +
                "<br>" +
                "Client's id: <input type=\"text\" name=\"clientId\"> " +
                "<button type=\"submit\" formaction=\"/totalPayClientId\">Show clients</button>" +
                "</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
//        out.println("<a href=\"/menu\">To the menu</a>");
//        out.println("<a href=\"index.jsp\">To index.jsp</a>");