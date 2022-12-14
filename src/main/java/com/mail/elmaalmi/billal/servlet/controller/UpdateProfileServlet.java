package com.mail.elmaalmi.billal.servlet.controller;

import com.mail.elmaalmi.billal.service.UserService;

import com.mail.elmaalmi.billal.service.imp.UserServiceImp;
import com.mail.elmaalmi.billal.util.DateParser;
import com.mail.elmaalmi.billal.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateProfileServlet", value = "/update-profile")
public class UpdateProfileServlet extends HttpServlet {
    private final UserService userService =  new UserServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = new User();
            user.setNom(request.getParameter("nom"));
            user.setPrenom(request.getParameter("prenom"));
            user.setEmail(request.getParameter("email"));
            user.setActive(true);
            user.setBirthday(DateParser.parse(request.getParameter("birthday")));
            userService.update(user);
            response.sendRedirect(getServletContext().getContextPath()+"/profile");
        } catch (Exception bex){
            bex.printStackTrace();
            response.sendRedirect(getServletContext().getContextPath()+"/signup?error_msg="+bex.getMessage());
        }
    }
}
