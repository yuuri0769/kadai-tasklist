package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();

		// Taskのインスタンスを作成
		Task t = new Task();

		// mの各フィールドにデータを代入
		String title = "taro";
		t.setTitle(title);

		String content = "hello";
		t.setContent(content);

		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		t.setCreated_at(currentTime);
		t.setUpdated_at(currentTime);

		// データベースに保存
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();

		// 自動採番されたIDの値を表示
		response.getWriter().append(Integer.valueOf(t.getId()).toString());

		em.close();
	}

}
