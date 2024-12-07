package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.model.Client;
import com.model.ClientManager;

@WebServlet("/AddClient")
public class AddClient extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ClientManager clientManager = new ClientManager();
        String message;

        try {
            // Retrieve client data from request parameters
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");

            // Create Client object and set values
            Client client = new Client();
            client.setId(id);
            client.setName(name);

            // Insert client into the database
            message = clientManager.insertData(client);
        } catch (NumberFormatException e) {
            message = "Error: Invalid ID format.";
        } catch (Exception e) {
            message = "Error: " + e.getMessage();
        }

        // Output the result message
        out.println("<html><body>");
        out.println("<h2>" + message + "</h2>");
        out.println("<a href='index.html'>Back to Home</a>");
        out.println("</body></html>");
    }
}
