package com.example;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class HelloServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.getWriter().println("<h1>Hello from Jenkins CI/CD Pipeline 🚀</h1>");
    }
}
