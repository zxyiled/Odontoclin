package org.odontoclin.servlets;

import org.mindrot.jbcrypt.BCrypt;
import org.odontoclin.logic.Controller;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.odontoclin.logic.User;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SvUser", value = "/SvUser")
public class SvUser extends HttpServlet {

    Controller controller = new Controller();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<User> userList = controller.getUsers();
        HttpSession session = request.getSession();
        session.setAttribute("userList", userList);

        response.sendRedirect("viewUser.jsp");

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        controller.createUser(username, hashedPassword, role);

        response.sendRedirect("index.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}