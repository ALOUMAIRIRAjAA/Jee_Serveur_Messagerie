package com.mail.elmaalmi.billal.servlet.controller;

import com.mail.elmaalmi.billal.model.Email;

import com.mail.elmaalmi.billal.model.User;
import com.mail.elmaalmi.billal.service.EmailService;
import com.mail.elmaalmi.billal.service.imp.EmailServiceImp;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "MailServlet", value = "/send-mail")
public class MailServlet extends HttpServlet {
    private EmailService emailService = new EmailServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Email email = new Email();
            HttpSession httpSession = request.getSession();
            User user = (User) httpSession.getAttribute("user");
            email.setFrom(user.getEmail());
            email.setMessage(request.getParameter("message"));
            email.setTo(request.getParameter("to"));
            email.setSubject(request.getParameter("subject"));
            email.setCreatedAt(new Date());
            emailService.create(email,user);
            response.sendRedirect(getServletContext().getContextPath());
        }catch (Exception e){
            response.sendRedirect(getServletContext().getContextPath()+"/mail?message="+e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
