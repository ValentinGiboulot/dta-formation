package fr.pizzeria.admin.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.tool.PizzaTool;
import fr.pizzeria.dao.IDao;
import fr.pizzeria.exception.SaveDaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

@WebServlet("/pizzas/new")
public class NouvellePizzaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IDao<Pizza, String> dao = PizzaTool.DAO;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/pizzas/nouvellePizza.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String code = request.getParameter("code");
		String nom = request.getParameter("nom");
		String prix = request.getParameter("prix");
		String categorie = request.getParameter("categorie");

		Pizza piz = new Pizza(code, nom, Double.parseDouble(prix), CategoriePizza.getEnum(categorie));

		try {
			dao.saveNew(piz);
			response.sendRedirect(request.getContextPath() + "/pizzas/list");
		} catch (SaveDaoException e) {
			response.setStatus(400);
			request.setAttribute("pizza", piz);
			response.sendRedirect(request.getContextPath() + "/pizzas/new");
		}

	}

}
