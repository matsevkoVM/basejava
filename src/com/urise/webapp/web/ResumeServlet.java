package com.urise.webapp.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResumeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String[] names = request.getParameterValues("fullName");
        String[] uuids = request.getParameterValues("uuid");
        PrintWriter pr = response.getWriter();
        pr.println("<html>");
        pr.println("<body>");
        pr.println("<table border=\"1\">");
        pr.println("<tr>");
        pr.println("<th>Full Name</th> <th>UUID</th>");
        pr.println("</tr>");
        for (int i = 0; i < names.length; i++) {
            pr.println("<tr>");
            pr.println("<td>" + names[i] + "</td> <td>" + uuids[i] + "</td>");
            pr.println("</tr>");
        }
        pr.println("</table>");
        pr.println("</body>");
        pr.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
