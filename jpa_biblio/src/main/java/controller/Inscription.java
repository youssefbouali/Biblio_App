package controller;

import java.io.IOException;
import java.util.ArrayList;

import dao.UserDao;
import dao.UserImpl;
import entity.User;
import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class inscription
 */
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {

        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		//response.sendRedirect("inscription.jsp");
		RequestDispatcher dispatcher = request.getRequestDispatcher("inscription.jsp");
		dispatcher.forward(request, response);

/*
		HttpSession session = request.getSession();
		//User UserContext = (User) session.getAttribute("User");

		if(session.getAttribute("User") == null) {
			//response.getWriter().append(UserContext);
			RequestDispatcher dispatcher = request.getRequestDispatcher("inscription.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("accueil.jsp");
		}*/

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		HttpSession session = request.getSession();
		//User UserContext = (User) session.getAttribute("User");

		if(session.getAttribute("user") == null) {

			String username=request.getParameter("username").trim();
			String ageS= request.getParameter("age").trim();
			String password=request.getParameter("password");


			UserDao UserDao = new UserImpl();

			User User = UserDao.getUser(username);

			ArrayList<String> errors = new ArrayList<>();


			if(username.isEmpty()) {
				errors.add("Username est Vide");
			} else if(User != null && User.getUsername() != null) {
				errors.add("Username deja existe");
			}

			if(ageS.isEmpty()) {
				errors.add("Age est Vide");
			} else if(Integer.parseInt(ageS) > 120 || Integer.parseInt(ageS) < 1) {
				errors.add("Age non valid");
			}

			if(password.isEmpty()) {
				errors.add("Password est Vide");
			}

			if(errors.isEmpty()){

				int age= Integer.parseInt(ageS);
				User nouvelUser = new User(username, age, password);


				UserDao.addUser(nouvelUser);

				session.setAttribute("user", username);

				response.sendRedirect("accueil.jsp");

			} else {
				request.setAttribute("errors", errors);
				RequestDispatcher dispatcher = request.getRequestDispatcher("inscription.jsp");
				dispatcher.forward(request, response);
			}


		} else {
			response.sendRedirect("accueil.jsp");
		}

	}

}
