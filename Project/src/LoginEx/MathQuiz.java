package LoginEx;

import java.util.Random;
import java.util.Scanner;

public class MathQuiz {
	public void showQuiz(String name) {
		Scanner sc = new Scanner(System.in);
		Random random = new Random();

		int cnt1 = 0;
		int cnt2 = 0;
		for (int i = 0; i < 20; i++) {
			System.out.println("사칙연산 문제를 풀어보세요!");
			System.out.println("연산자: +, -, *, /");
			// 랜덤으로 숫자 생성
			int num1 = random.nextInt(10) + 1; // 1부터 10까지의 난수
			int num2 = random.nextInt(10) + 1;
			char operator = getRandomOperator();

			// 문제 출력
			System.out.print("문제: " + num1 + " " + operator + " " + num2 + " = ");

			// 학생의 답 입력
			double studentAnswer = sc.nextDouble();

			// 정답 확인 및 출력
			if (checkAnswer(num1, num2, operator, studentAnswer)) {
				System.out.println("정답입니다!");
				cnt1++;
			} else {
				System.out.println("오답입니다. 정답은 " + calculateAnswer(num1, num2, operator) + "입니다.");
				cnt2++;
			}

		}
		LoginDAO dao = new LoginDAO();
		dao.record(name, cnt1, cnt2);
		System.out.println("문제를 다 풀었군요! 칭찬 스티커 한 개 받아가세요~");
		System.out.println("맞은 갯수 : " + cnt1 + "틀린 갯수 : " + cnt2);
	}

	// 랜덤 연산자 생성
	private static char getRandomOperator() {
		char[] operators = { '+', '-', '*', '/' };
		Random random = new Random();
		int index = random.nextInt(operators.length);
		return operators[index];
	}

	// 정답 확인
	private static boolean checkAnswer(int num1, int num2, char operator, double studentAnswer) {
		double correctAnswer = calculateAnswer(num1, num2, operator);
		return Math.abs(correctAnswer - studentAnswer) < 0.0001; // 부동소수점 비교를 위해 작은 오차 허용
	}

	// 정답 계산
	private static double calculateAnswer(int num1, int num2, char operator) {
		switch (operator) {
		case '+':
			return num1 + num2;
		case '-':
			return num1 - num2;
		case '*':
			return num1 * num2;
		case '/':
			return num1 / num2; // 나눗셈은 소수점까지 고려
		default:
			throw new IllegalArgumentException("올바르지 않은 연산자입니다.");
		}

	}

}
