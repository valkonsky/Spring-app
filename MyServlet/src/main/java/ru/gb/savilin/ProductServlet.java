package ru.gb.savilin;

import ru.gb.savilin.entity.Product;
import ru.gb.savilin.entity.ProductManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = "/products/*")
public class ProductServlet extends HttpServlet {

    private ProductManager productManager;

    private static final Pattern PARAM_PATTERN = Pattern.compile("\\/(\\d+)");
    @Override
    public void init() throws ServletException {
        productManager = new ProductManager();
        productManager.insert(new Product("cap",3000));
        productManager.insert(new Product("car",3000000));
        productManager.insert(new Product("bag",2000));
        productManager.insert(new Product("book",390));
        productManager.insert(new Product("shave foam",200));
        productManager.insert(new Product("lamp",1500));
        productManager.insert(new Product("table",15000));
        productManager.insert(new Product("snickers",12000));
        productManager.insert(new Product("jacket",9000));
        productManager.insert(new Product("jeans",11000));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(productManager.findAll().size());
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")){
            req.setAttribute("products",productManager.findAll());
            getServletContext().getRequestDispatcher("/product.jsp").forward(req,resp);
        }else{
            Matcher matcher = PARAM_PATTERN.matcher(req.getPathInfo());
            if (matcher.matches()) {
                long id = Long.parseLong(matcher.group(1));
                Product product = this.productManager.getById(id);
                if (product == null) {
                    resp.getWriter().println("user not found");
                    resp.setStatus(404);
                    return;
                }

                req.setAttribute("product",product);
                getServletContext().getRequestDispatcher("/product_form.jsp").forward(req,resp);

            }else{
                    resp.getWriter().println("Bad parameter value");
                    resp.setStatus(400);
                }
            }
        }
}
