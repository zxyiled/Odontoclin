package org.odontoclin.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.odontoclin.logic.Controller;

import java.io.IOException;

@WebServlet(name = "SvDeleteUser", value = "/SvDeleteUser")
public class SvDeleteUser extends HttpServlet {

    Controller controller = new Controller();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        controller.deleteUser(id);
        response.sendRedirect("SvUser");

    }

    @Override
    public String getServletInfo() {

        return "Short description";
    }
}
