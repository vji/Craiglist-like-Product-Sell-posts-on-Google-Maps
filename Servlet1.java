import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String search=(String) request.getParameter("textbox2");
		String s[]=new String[2];
		s[0]=search;
		new Demo().main(s);
		ArrayList<Integer> result2=new Demo().result2;
		String[][] array2=Point.array;
		double[][] array=Point.array1;
		double[][] array1=new double[5][2];
		for(int i=0;i<5;i++)
		{
			array1[i][0]=array[i][0];
			array1[i][1]=array[i][1];
		}
		String[] test =new String[2];
		 test[0] = new String("2");
		 test[1] = new String("3");
		request.setAttribute("result2", result2);
		request.setAttribute("array2",array2);
		request.setAttribute("array1", array1);
		getServletContext().getRequestDispatcher("/NewFile.jsp").forward(request, response);
		PrintWriter writer = response.getWriter();
		writer.print("");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

}
