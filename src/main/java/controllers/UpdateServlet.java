package controllers;

import java.io.IOException;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import models.Todo;
import models.validators.TodoValidator;
import utils.DBUtil;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public UpdateServlet() {
        super();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            // セッションスコープからメッセージのIDを取得して
            // 該当のIDのメッセージ1件のみをデータベースから取得
            Todo t = em.find(Todo.class, (Integer)(request.getSession().getAttribute("todo_id")));

            // フォームの内容を各フィールドに上書き
            String content = request.getParameter("content");
            t.setContent(content);

            Boolean done = request.getParameter("done") != null;
            t.setDone(done);

            // バリデーションを実行してエラーがあったら編集画面のフォームに戻る
            List<String> errors = TodoValidator.validate(t);
            if(errors.size() > 0) {
                em.close();

                // フォームに初期値を設定、さらにエラーメッセージを送る
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("todo", t);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/todos/edit.jsp");
                rd.forward(request, response);
            } else {
                // データベースを更新
                em.getTransaction().begin();
                em.getTransaction().commit();
                request.getSession().setAttribute("flush", "更新が完了しました。");
                em.close();

                // セッションスコープ上の不要になったデータを削除
                request.getSession().removeAttribute("todo_id");

                // indexページへリダイレクト
                response.sendRedirect(request.getContextPath() + "/index");
            }
        }
    }

}
