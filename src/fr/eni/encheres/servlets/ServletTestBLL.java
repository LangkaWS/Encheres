package fr.eni.encheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.CategoryManager;
import fr.eni.encheres.bll.PickUpManager;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bll.bo.User;
import fr.eni.encheres.bll.bo.Category;
import fr.eni.encheres.bll.bo.PickUp;

/**
 * Servlet implementation class ServletTestBLL
 */
@WebServlet("/ServletTestBLL")
public class ServletTestBLL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTestBLL() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		UserManager um = new UserManager();
		
		//Test UserManager
		/* TEST MANAGER OK
		User u = new User("nanou", "Bibi", "Nougat", "nougatdu57@gmx.com", "0123456789", "28 rue des abeilles", "68000", "colmar", "meow");
		System.out.println(u.getUserId());
		try {
			//Ajouter un utilisateur
			um.addUser(u);
			int uId = u.getUserId();
			System.out.println(uId);
			
			//Récupérer un utilisateur
			User test = um.getUser(uId);
			System.out.println(test.toString());
			
			//Créer un autre utilisateur, essayer de modifier le premier en mettant le même pseudo que le deuxième
			// OK throw BLLException
			User u2 = new User("nounou", "Bibi", "Nougat", "nougat@gmx.com", "0123456789", "28 rue des abeilles", "68000", "colmar", "meow");
			um.addUser(u2);
			int u2Id = u2.getUserId();
			System.out.println(u2Id);
			
			User test2 = um.getUser(u2Id);
			System.out.println(test2.toString());
			
			//Mettre à jour un utilisateur
			
			u.setUserName("nana");
			um.updateUser(u);
			test = um.getUser(uId);
			System.out.println(test.toString());
			
			u.setUserName("nounou");
			um.updateUser(u);
			test = um.getUser(uId);
			System.out.println(test.toString());
			
			
			//Supprimer un utilisateur
			// OK throw NullPointerException
			um.deleteUser(uId);
			test = um.getUser(uId);
			//System.out.println(test.toString()); //NullPointerException si supprimé avec succès
		} catch (BLLException e) {
			e.printStackTrace();
		}
		*/
		
		PickUpManager pm = new PickUpManager();
		
		//Test PickUpManager
		
		PickUp pu = new PickUp();
		
		CategoryManager cm = new CategoryManager();
		/*
		Category c = new Category("Jardinage");
		try {
			cm.addCategory(c);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
