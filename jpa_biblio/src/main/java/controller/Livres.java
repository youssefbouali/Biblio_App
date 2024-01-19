package controller;

import java.io.IOException;
import java.util.ArrayList;
//import java.util.List;

//import dao.UserDao;
//import dao.UserImpl;
//import entity.User;

import dao.LivreDao;
import dao.LivreImpl;
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
public class Livres extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Livres() {
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

		HttpSession sessione = request.getSession();

		if(sessione.getAttribute("user") == null) {
			response.sendRedirect("Connexion");
			return;
		}

		String isbn=request.getParameter("isbn");

		LivreDao LivreDao = new LivreImpl();

		if(isbn == null) {

			ArrayList<Livre> Livres = LivreDao.getLivres();

			request.setAttribute("livres", Livres);
			RequestDispatcher dispatcher = request.getRequestDispatcher("livres.jsp");
			dispatcher.forward(request, response);

		} else {

			Livre Livre = LivreDao.getLivre(isbn);

			request.setAttribute("livre", Livre);
			RequestDispatcher dispatcher = request.getRequestDispatcher("livre.jsp");
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

		HttpSession sessione = request.getSession();

		if(sessione.getAttribute("user") == null) {
			response.sendRedirect("Connexion");
		}

		String submit=request.getParameter("submit");

		if(submit.equals("Ajouter")) {
			String titre=request.getParameter("titre").trim();
			String auteur=request.getParameter("auteur").trim();
			String isbn=request.getParameter("isbn").trim();


			LivreDao LivreDao = new LivreImpl();

			Livre Livre = LivreDao.getLivre(isbn);

			ArrayList<String> errors = new ArrayList<>();

			if(titre.isEmpty()) {
				errors.add("Titre est Vide");
			}

			if(auteur.isEmpty()) {
				errors.add("Auteur est Vide");
			}

			if(isbn.isEmpty()) {
				errors.add("ISBN est Vide");
			} else if(Livre != null && Livre.getIsbn() != null) {
				errors.add("ISBN deja existe");
			}

			if(errors.isEmpty()){

				Livre nouvelLivre = new Livre(titre, auteur, isbn);

				LivreDao.addLivre(nouvelLivre);

				response.sendRedirect("Livres?isbn="+isbn);

			} else {

				request.setAttribute("errors", errors);
				RequestDispatcher dispatcher = request.getRequestDispatcher("ajout_livre.jsp");
				dispatcher.forward(request, response);
			}

		} else if(submit.equals("Modification")) {
			String isbn=request.getParameter("isbn");


			LivreDao LivreDao = new LivreImpl();

			Livre Livre = LivreDao.getLivre(isbn);

			request.setAttribute("livre", Livre);

			RequestDispatcher dispatcher = request.getRequestDispatcher("modifier_livre.jsp");
			dispatcher.forward(request, response);

		} else if(submit.equals("Modifier")) {
			String isbn=request.getParameter("isbn").trim();

			String titre=request.getParameter("titre").trim();
			String auteur=request.getParameter("auteur").trim();

			LivreDao LivreDao = new LivreImpl();

			Livre Livre = LivreDao.getLivre(isbn);

			ArrayList<String> errors = new ArrayList<>();


			if(titre.isEmpty()) {
				errors.add("Titre est Vide");
			}

			if(auteur.isEmpty()) {
				errors.add("Auteur est Vide");
			}

			if(isbn.isEmpty()) {
				errors.add("ISBN est Vide");
			} else if(Livre.getIsbn() == null) {
				errors.add("ISBN non existe");
			}

			if(errors.isEmpty()){

				Livre nouvelLivre = new Livre(titre, auteur, isbn);

				LivreDao.updateLivre(nouvelLivre);

				response.sendRedirect("Livres?isbn="+isbn);

			} else {
				request.setAttribute("errors", errors);
				RequestDispatcher dispatcher = request.getRequestDispatcher("modifier_livre.jsp");
				dispatcher.forward(request, response);
			}

		} else if(submit.equals("Supprimer")) {
			String isbn=request.getParameter("isbn");

			LivreDao LivreDao = new LivreImpl();

			Livre Livre = LivreDao.getLivre(isbn);

			ArrayList<String> errors = new ArrayList<>();


			if(isbn.isEmpty()) {
				errors.add("ISBN est Vide");
			} else if(Livre.getIsbn() == null) {
				errors.add("ISBN non existe");
			}

			if(errors.isEmpty()){

				LivreDao.removeLivre(isbn);

				response.sendRedirect("Livres");

			} else {
				request.setAttribute("errors", errors);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Livres?isbn="+isbn);
				dispatcher.forward(request, response);
			}
		} else if(submit.equals("Recherche")) {
			String q=request.getParameter("q").trim();

			LivreDao LivreDao = new LivreImpl();

			ArrayList<Livre> Livres = LivreDao.getLivresSearch(q);

			request.setAttribute("livres", Livres);
			RequestDispatcher dispatcher = request.getRequestDispatcher("livres.jsp");
			dispatcher.forward(request, response);


		} else if(submit.equals("Tri")) {
			String tri=request.getParameter("tri");

			if(tri == null) {
				tri = "id";
			}

			if(tri.equals("id") || tri.equals("titre") || tri.equals("auteur") || tri.equals("isbn")) {

				LivreDao LivreDao = new LivreImpl();

				ArrayList<Livre> Livres = LivreDao.getLivresTri(tri);

				request.setAttribute("tri", tri);
				request.setAttribute("livres", Livres);
				RequestDispatcher dispatcher = request.getRequestDispatcher("livres.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
