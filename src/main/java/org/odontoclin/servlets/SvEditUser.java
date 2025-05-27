package org.odontoclin.servlets;

import jakarta.servlet.http.HttpSession;
import org.odontoclin.logic.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.odontoclin.logic.Controller;

import java.io.IOException;

@WebServlet(name = "SvEditUser", value = "/SvEditUser")
public class SvEditUser extends HttpServlet {

    Controller controller = new Controller();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        User user = controller.bringUser(id);

        HttpSession session = request.getSession();
        session.setAttribute("EditUser", user);

        response.sendRedirect("editUser.jsp");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        User user = (User) request.getSession().getAttribute("EditUser");
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        controller.editUser(user);

        response.sendRedirect("SvUser");
    }

    @Override
    public String getServletInfo() {

        return "Short description";
    }
}
