package controller;

import java.io.IOException;
import java.util.ArrayList;
//import java.util.List;

//import dao.UserDao;
//import dao.UserImpl;
//import entity.User;

import dao.EmpruntDao;
import dao.EmpruntImpl;
import entity.Livre;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Livres
 */
public class Emprunts extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Emprunts() {
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

		HttpSession session = request.getSession();

		if(session.getAttribute("user") == null) {
			response.sendRedirect("Connexion");
			return;
		}

		String isbn=request.getParameter("isbn");

		EmpruntDao EmpruntDao = new EmpruntImpl();

		if(isbn == null) {

			ArrayList<Livre> Emprunts = EmpruntDao.getEmprunts((String) session.getAttribute("user"));

			request.setAttribute("emprunts", Emprunts);
			RequestDispatcher dispatcher = request.getRequestDispatcher("emprunts.jsp");
			dispatcher.forward(request, response);

		} else {

			Livre Emprunt = EmpruntDao.getEmprunt(isbn, (String) session.getAttribute("user"));

			request.setAttribute("emprunt", Emprunt);
			RequestDispatcher dispatcher = request.getRequestDispatcher("emprunt.jsp");
			dispatcher.forward(request, response);
		}
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
			response.sendRedirect("Connexion");
		}

		String submit=request.getParameter("submit");

		if(submit.equals("Emprunter")) {
			String isbn=request.getParameter("isbn");


			EmpruntDao EmpruntDao = new EmpruntImpl();

			Livre Emprunt = EmpruntDao.getEmprunt(isbn, (String) session.getAttribute("user"));

			ArrayList<String> errors = new ArrayList<>();

			if(isbn.isEmpty()) {
				errors.add("ISBN est Vide");
			} else if(Emprunt != null && Emprunt.getIsbn() != null) {
				errors.add("Livre deja emprunté");
			}

			if(errors.isEmpty()){

				//String Username = (String) session.getAttribute("user");

				EmpruntDao.addEmprunt(isbn, (String) session.getAttribute("user"));

				response.sendRedirect("Emprunts?isbn="+isbn);

			} else {

				request.setAttribute("emprunt", Emprunt);
				request.setAttribute("errors", errors);
				RequestDispatcher dispatcher = request.getRequestDispatcher("emprunt.jsp");
				dispatcher.forward(request, response);
				//response.getWriter().append("ISBN deja existe");
			}

		} else if(submit.equals("Supprimer")) {
			String isbn=request.getParameter("isbn");


			EmpruntDao EmpruntDao = new EmpruntImpl();

			Livre Emprunt = EmpruntDao.getEmprunt(isbn, (String) session.getAttribute("user"));

			ArrayList<String> errors = new ArrayList<>();


			if(isbn.isEmpty()) {
				errors.add("ISBN est Vide");
			} else if(Emprunt.getIsbn() == null) {
				errors.add("ISBN non existe");
			}

			if(errors.isEmpty()){

				EmpruntDao.removeEmprunt(isbn, (String) session.getAttribute("user"));

				response.sendRedirect("Emprunts");

			} else {
				request.setAttribute("emprunt", Emprunt);
				request.setAttribute("errors", errors);
				RequestDispatcher dispatcher = request.getRequestDispatcher("emprunt.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
