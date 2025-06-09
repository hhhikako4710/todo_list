package controllers;

import java.io.IOException;

import jakarta.persistence.EntityManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import models.Todo;
import utils.DBUtil;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public EditServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Todo t = em.find(Todo.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("todo", t);
        request.setAttribute("_token", request.getSession().getId());

        if(t != null) {
            request.getSession().setAttribute("todo_id", t.getId());
        }
            
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/todos/edit.jsp");
        rd.forward(request, response);
    }
}
