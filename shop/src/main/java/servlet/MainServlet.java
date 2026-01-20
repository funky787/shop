package servlet;

import model.Product;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet("/shop")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        getServletContext()
                .getRequestDispatcher("/index.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int productId = Integer.parseInt(req.getParameter("id"));

        ConcurrentHashMap<Integer, Product> catalog =
                (ConcurrentHashMap<Integer, Product>)
                        getServletContext().getAttribute("catalog");

        Product product = catalog.get(productId);

        if (product.getStock() > 0) {
            HttpSession session = req.getSession();

            Map<Product, Integer> cart =
                    (Map<Product, Integer>) session.getAttribute("cart");

            if (cart == null) {
                cart = new HashMap<>();
                session.setAttribute("cart", cart);
            }

            cart.put(product, cart.getOrDefault(product, 0) + 1);
            product.setStock(product.getStock() - 1);
        }

        resp.sendRedirect("shop");
    }
}