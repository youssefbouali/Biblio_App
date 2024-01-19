package controller;

import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
import java.util.ArrayList;

import dao.UserDao;
import dao.UserImpl;
import entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Connect
 */
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        super();
        // TODO Auto-generated constructor stub
    }

    /*@Override
    public void init() throws ServletException {
    	String pilote = getServletContext().getInitParameter("jdbc.Driver");
    	String DataBase = getServletContext().getInitParameter("localisation");

    	try {
    		Class.forName(pilote);
    		Connection connection = DriverManager.getConnection(DataBase, "root", "123456");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
        super.init();
    }*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.sendRedirect("connexion.jsp");
		RequestDispatcher dispatcher = request.getRequestDispatcher("connexion.jsp");
		dispatcher.forward(request, response);

		/*HttpSession session = request.getSession();
		//User UserContext = (User) session.getAttribute("User");

		if(session.getAttribute("User") == null) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("connexion.jsp");
			dispatcher.forward(request, response);

		} else {
			response.sendRedirect("accueil.jsp");
		}*/

		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		HttpSession session = request.getSession();

		if(session.getAttribute("user") == null) {

			String username=request.getParameter("username").trim();
			String password = request.getParameter("password");


			UserDao UserDao = new UserImpl();

			User User = UserDao.getUser(username);


			ArrayList<String> errors = new ArrayList<>();


			if(username.isEmpty()) {
				errors.add("Username est Vide");
			} else if(User.getUsername() == null) {
				//response.getWriter().append("Username non correct");
				errors.add("Username non correct");
				//response.sendRedirect("connexion.jsp?error=1");
			} else if(password.isEmpty()) {
				errors.add("Password Empty");
			} else if (/*!User.getNom().equals(nom) || */!User.getPassword().equals(password)) {

				//response.sendRedirect("connexion.jsp?error=2");
				//response.getWriter().append("Password non correct");
				errors.add("Password non correct");
			}

			if(errors.isEmpty()) {

					session.setAttribute("user", username);

					response.sendRedirect("accueil.jsp");
			} else {
				request.setAttribute("errors", errors);
				RequestDispatcher dispatcher = request.getRequestDispatcher("connexion.jsp");
				dispatcher.forward(request, response);

			}

		} else {
			response.sendRedirect("accueil.jsp");
		}

	}

}
