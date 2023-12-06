package LoginEx;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MemberHandler {
	List<LoginVO> list = new ArrayList<>();
	private int idx;

	public MemberHandler() { 
		
	}
	public MemberHandler(int num) {

		idx = 0;
	}
	private boolean isUniqueID(String uid) { // 아이디의 중복 여부를 리턴
		if (idx == 0)
			return true;

		for (int i = 0; i < idx; i++) {
			if (list.get(i).getUid().equals(uid)) {
				return false;
			}
		}
		return true;
	}
	public void memberInsert() { // 회원가입용 메소드
		List<LoginVO> list = new ArrayList<>();
		String uid, pwd, name;
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 : ");
		uid = sc.next();
		if (!isUniqueID(uid)) // 아이디가 중복되었으면
		{
			System.out.println("이미 사용중인 아이디 입니다. \n");
			return;
		}
		System.out.print("암 호 : ");
		pwd = sc.next();
		System.out.print("이 름 : ");
		name = sc.next();
		list.add(new LoginVO(uid, pwd, name));
		LoginDAO dao = new LoginDAO();
		dao.insert(list);
		System.out.println("가입 완료!! \n");
	}
	public void memberLogin() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("아이디 : ");
			String uid = sc.nextLine();
			System.out.print("패스워드 : ");
			String pwd = sc.nextLine();
			String msg = "존재하지 않는 아이디 입니다.";

			LoginDAO dao = new LoginDAO();
			LoginVO temp = new LoginVO();
			temp.setUid(uid);
			temp.setPw(pwd);

			LoginVO user = dao.login(temp);

			// System.out.println(user);

			if (user == null) {
				System.out.println("로그인에 실패 하였습니다.");
				System.out.println("아이디와 비번을 다시 입력하세요.");
			} else {
				System.out.println("로그인 성공 하였습니다.");
				MathQuiz quiz = new MathQuiz();
				quiz.showQuiz(uid);
				break;
			}
			// System.out.println(user.getName());
		}

	}
	public void memberdelete() {

		Scanner sc = new Scanner(System.in);
		System.out.print("삭제 할 아이디를 입력하세요 : ");
		String uid = sc.nextLine();
		System.out.print("비밀번호를 입력하세요 : ");
		String pwd = sc.nextLine();

		LoginDAO dao = new LoginDAO();
		dao.delete(uid, pwd);

		System.out.println("계정이 삭제 되었습니다.");

	}
	public void managerLogin() {
		Scanner sc = new Scanner(System.in);
		MemberHandler handler = new MemberHandler();
		System.out.println("관리자 로그인 진행");
		System.out.print("아이디 : ");
		String uid = sc.nextLine();
		System.out.print("패스워드 : ");
		String pwd = sc.nextLine();
		String msg = "존재하지 않는 아이디 입니다.";

		LoginDAO dao = new LoginDAO();
		ManagerVO temp = new ManagerVO();
		temp.setId(uid);
		temp.setPassword(pwd);

		int user = dao.managerLogin(temp);

		// System.out.println(user);

		if (user == 0) {
			System.out.println("로그인에 실패 하였습니다.");
			System.out.println("아이디와 비번을 다시 입력하세요.");
		} else {
			System.out.println("로그인 성공 하였습니다.");
			handler.Manager();
		}
		// System.out.println(user.getName());
	}
	private void Manager() {
		MemberHandler handler = new MemberHandler();

		while (true) {
			System.out.println("------- 관리자 메뉴 선택 -------");

			System.out.println("1. 회원 조회");
			System.out.println("0. 프로그램 종료");
			System.out.print("항목을 입력하세요: ");

			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				handler.showMemebers();
				break;
			case 0:
				System.out.println("프로그램 종료");
				System.exit(0);
			}

		}

	}
	private void showMemebers() {
		List<LoginVO> list = new ArrayList<LoginVO>();
		LoginDAO dao = new LoginDAO();
		System.out.println("=========================================");
		System.out.println("회원 조회");
		System.out.println("아이디\t이름\t비밀번호");
		System.out.println("=========================================");
		list = dao.showMembers();
		if (list.size() == 0) {
			System.out.println("데이터 없음");
		} else {
			for (LoginVO member : list) {
				System.out.println(member.getUid() + "\t" + member.getName() + "\t" + member.getPw());
			}
		}
		System.out.println("=========================================");
	}
}
public class MemberInfo {
	public static void main(String[] args) {
		MemberHandler handler = new MemberHandler(100);

		while (true) {
			System.out.println("------- 메뉴 선택 -------");
			System.out.println("1. 회원가입");
			System.out.println("2. 로 그 인");
			System.out.println("3. 회원 탍퇴");
			System.out.println("4. 관리자 모드 로그인");
			System.out.println("0. 프로그램 종료");
			System.out.print("항목을 입력하세요: ");

			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				handler.memberInsert();
				break;
			case 2:
				handler.memberLogin();
				break;
			case 3:
				handler.memberdelete();
				break;
			case 4:
				handler.managerLogin();
				break;
			case 0:
				System.out.println("프로그램 종료");
				System.exit(0);
			}

		}
	}
}
