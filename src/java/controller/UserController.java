/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.UserBO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UsersManager;

/**
 *
 * @author dany
 */
public class UserController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        populateUsers(request, response);
        System.out.println("Procesing Get method");

        request.getRequestDispatcher("users.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Procesing Post method");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String passwd = request.getParameter("password");

        if (null != name && null != email && null != passwd) {
            UserBO u = new UserBO(name, email, passwd);
            UsersManager usersManager = new UsersManager();
            usersManager.create(u);
        } else {
            System.out.println("User cant be created");
        }

        populateUsers(request, response);
        request.getRequestDispatcher("users.jsp").forward(request, response);
    }

    private void populateUsers(HttpServletRequest request, HttpServletResponse response) {
        UsersManager usersManager = new UsersManager();
        List<UserBO> lu = usersManager.getAll();
        request.setAttribute("users", lu);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
