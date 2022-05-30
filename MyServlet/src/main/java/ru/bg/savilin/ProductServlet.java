package ru.bg.savilin;

import ru.bg.savilin.entity.Product;
import ru.bg.savilin.entity.ProductManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    private ProductManager productManager;


    @Override
    public void init() throws ServletException {
        productManager = new ProductManager();
        productManager.add(new Product("bag",2000));
        productManager.add(new Product("cap",3000));
        productManager.add(new Product("car",3000000));
        productManager.add(new Product("book",390));
        productManager.add(new Product("shave foam",200));
        productManager.add(new Product("lamp",1500));
        productManager.add(new Product("table",15000));
        productManager.add(new Product("snickers",12000));
        productManager.add(new Product("jacket",9000));
        productManager.add(new Product("jeans",11000));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<table>");
        printWriter.println("<tr>");
        printWriter.println("<th>id</th>");
        printWriter.println("<th>title</th>");
        printWriter.println("<th>cost</th>");
        printWriter.println("</tr>");

        for (int i = 0;i<productManager.size();i++){
            printWriter.println("<tr>");
            printWriter.println("<td>"+ productManager.get(i).getId() + "</td>");
            printWriter.println("<td>"+ productManager.get(i).getTitle() + "</td>");
            printWriter.println("<td>"+ productManager.get(i).getCost() + "</td>");
            printWriter.println("</tr>");
        }
        printWriter.println("</table>");
    }
}
