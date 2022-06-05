package servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.PostBean;
import dao.PostDao;

/**
 * Servlet implementation class NewPostServlet
 */
@WebServlet("/newPost")
@MultipartConfig(maxFileSize=1048576)
public class NewPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final Set<String> imgExtensionSet =
		new HashSet<String>(Arrays.asList(".jpg", ".jpeg", ".gif", ".jpe", ".png", ".bmp", ".dib", ".ico"));
	public static final Set<String> docExtensionSet =
		new HashSet<String>(Arrays.asList(".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx"));

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String content = request.getParameter("content");
		Part part = request.getPart("file");

		String file = part.getSubmittedFileName();
		String extension = file.substring(file.lastIndexOf("."));

		//ファイル有無検査
		if(!file.equals("")){
			//拡張子検査
			if(!imgExtensionSet.contains(extension) && !docExtensionSet.contains(extension)){
				request.setAttribute("poster_name", name);
				request.setAttribute("poster_mail", mail);
				request.setAttribute("file_error", true);

				String view = "index.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
				return;
			}
			part.write("D:\\pleiades\\workspace\\bulletinBoard\\WebContent\\upload\\" + file);
		}

		//投稿内容エスケープ処理
		if (null != content && !content.equals("")) {
			content = content.replaceAll("&", "&amp;");
			content = content.replaceAll("<", "&lt;");
			content = content.replaceAll(">", "&gt;");
			content = content.replaceAll("'", "&#39;");
		}

		PostBean newPost = new PostBean(name, mail, content, file);
		PostDao.addPost(newPost);

		request.setAttribute("poster_name", name);
		request.setAttribute("poster_mail", mail);

		String view = "index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
