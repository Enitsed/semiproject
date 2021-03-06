package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.BoardAction;
import action.UserAction;

@WebServlet("/main/*")
public class SemiController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}// end doGet()

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	} // end doPost()

	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"));
		String path = "";
		String command = "";
		System.out.println(action);
		UserAction userAction = new UserAction(); // 유저 관련 명령어 처리 객체
		BoardAction boardAction = new BoardAction(); // 게시판 관련 명령어 처리 객체

		if (action.equals("/*") || action.equals("/index")) {
			// 초기화면 수정필요
			command = "list";
			req.setAttribute("board_loc", "index");
			req.setAttribute("command", command);
			boardAction.execute(req, resp);
			path = "/semiproject/index.jsp";
		} else if (action.equals("/seoul")) {
			command = "list";
			req.setAttribute("command", command);
			req.setAttribute("board_loc", "seoul");
			boardAction.execute(req, resp);
			path = "/semiproject/seoul.jsp";
		} else if (action.equals("/gyeonggi")) {
			command = "list";
			req.setAttribute("command", command);
			req.setAttribute("board_loc", "gyeonggi");
			boardAction.execute(req, resp);
			path = "/semiproject/gyeonggi.jsp";
		} else if (action.equals("/gallery")) {
			command = "list";
			req.setAttribute("command", command);
			req.setAttribute("board_loc", "gallery");
			boardAction.execute(req, resp);
			path = "/semiproject/gallery.jsp";
		} else if (action.equals("/contact")) {
			command = "list";
			req.setAttribute("command", command);
			req.setAttribute("board_loc", "contact");
			boardAction.execute(req, resp);
			path = "/semiproject/contact.jsp";
		}
		////////////////////////////////////////////////////
		// 게시판 기능
		else if (action.equals("/board") || action.equals("/pageNum=*")) {
			// 게시판 목록 조회하기 (수정필요 : 페이지네이션, )
			command = "list";
			req.setAttribute("board_loc", "board");
			req.setAttribute("command", command);
			boardAction.execute(req, resp);
			path = "/semiproject/board.jsp";
		} else if (action.equals("/write")) {
			// 글쓰기 화면 (수정필요: 세션을 통한 글 작성)
			command = "write";
			req.setAttribute("command", command);
			boardAction.execute(req, resp);
			resp.sendRedirect("/semiproject/main/board");
		} else if (action.equals("/view")) {
			// 게시판 상세보기
			command = "view";
			req.setAttribute("command", command);
			boardAction.execute(req, resp);
			path = "/semiproject/view.jsp";
		} else if (action.equals("/download")) {
			// 첨부파일 다운로드
			command = "download";
			req.setAttribute("command", command);
			boardAction.execute(req, resp);
		} else if (action.equals("/delete_board")) {
			// 글 삭제
			command = "delete_board";
			req.setAttribute("command", command);
			boardAction.execute(req, resp);
			resp.sendRedirect("/semiproject/main/board");
		} else if (action.equals("/update")) {
			// 글 수정창 으로 이동
			command = "update";
			req.setAttribute("command", command);
			boardAction.execute(req, resp);
			path = "/semiproject/modify.jsp";
		} else if (action.equals("/modify")) {
			// 글 수정
			command = "modify";
			req.setAttribute("command", command);
			boardAction.execute(req, resp);
		}
		/////////////////////////////////////////////////
		// 댓글 기능
		else if (action.equals("/reply")) {
			// 댓글등록
			command = "reply";
			req.setAttribute("command", command);
			boardAction.execute(req, resp);
		} else if (action.equals("/reply_delete")) {
			// 댓글삭제
			command = "reply_delete";
			req.setAttribute("command", command);
			boardAction.execute(req, resp);
		} else if (action.equals("/reply_update")) {
			command = "reply_update";
			req.setAttribute("command", command);
			boardAction.execute(req, resp);
			path = "/semiproject/replyupdateview.jsp";
		} else if (action.equals("/reply_modify")) {
			// 댓글수정
			command = "reply_modify";
			req.setAttribute("command", command);
			boardAction.execute(req, resp);
		}

		//////////////////////////////////////////////////
		// 유저 관련 기능
		else if (action.equals("/join")) {
			// 회원가입 화면 (수정필요: 디자인 )
			path = "/semiproject/join.jsp";
		} else if (action.equals("/signup")) {
			// 회원가입 기능
			System.out.println("회원가입");
			command = "signup";
			req.setAttribute("command", command);
			userAction.execute(req, resp);
			System.out.println("회원가입 완료");
			path = "/semiproject/index.jsp";
		} else if (action.equals("/confirmId")) {
			// 아이디 중복 체크
			command = "confirmId";
			req.setAttribute("command", command);
			userAction.execute(req, resp);
			System.out.println("회원가입 완료");
			path = "/semiproject/confirmId.jsp";
		} else if (action.equals("/login")) {
			// 로그인 창으로 이동
			path = "/semiproject/login.jsp";
		} else if (action.equals("/login_check")) {
			// 로그인 회원 확인 기능
			command = "login_check";
			req.setAttribute("command", command);
			userAction.execute(req, resp);
		} else if (action.equals("/logout")) {
			// 로그아웃
			command = "logout";
			req.setAttribute("command", command);
			userAction.execute(req, resp);
		} else if (action.equals("/mypage")) {
			/////////////////// 로그인 후 마이페이지 창 이동/////////////////
			command = "mypage";
			req.setAttribute("command", command);
			userAction.execute(req, resp);
			path = "/semiproject/viewinfo.jsp";
		} else if (action.equals("/mypage_update")) {
			// 회원정보 수정
			command = "mypage_update";
			req.setAttribute("command", command);
			userAction.execute(req, resp);
		} else if (action.equals("/delete")) {
			// 회원탈퇴 폼
			path = "/semiproject/deleteForm.jsp";
		} else if (action.equals("/delete_final")) {
			// 회원탈퇴
			path = "/semiproject/deleteFinal.jsp";
		} else if (action.equals("/findid")) {
			// 아이디 찾기
			command = "findid";
			req.setAttribute("command", command);
			userAction.execute(req, resp);
			path = "/semiproject/find.jsp";
		} else if (action.equals("/findpw")) {
			// 비밀번호 찾기
			command = "findpw";
			req.setAttribute("command", command);
			userAction.execute(req, resp);
			path = "/semiproject/find.jsp";
		}

		if (path != "") {
			RequestDispatcher dis = req.getRequestDispatcher(path);
			dis.forward(req, resp);
		}

	} // end process

}// end class