package main;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.*;
import mapper.ScoreMapper;
import util.DBTools;

public class CardScore {
	public static void main(String[] args) throws FileNotFoundException {
		// 借记卡19
		PrintStream txt3 = new PrintStream("./Jjk19OutD.txt");
		PrintStream out3 = System.out;
		System.setOut(txt3);
		System.out
				.println("PARTY_ID\tAGT_NUM\tCURR_TERM_MAIN_ACCT_AGT_NUM\tscore");
		countJjc("借记卡协议");
		System.setOut(out3);
		System.out.println("借记卡计算完成");
		// 贷记卡16位+贷记卡5位
		PrintStream txt4 = new PrintStream("./DjkOutD.txt");
		PrintStream out4 = System.out;
		System.setOut(txt4);
		System.out.println("PARTY_ID\tAGT_NUM\tscore");
		countAgt("贷记卡协议");
		System.setOut(out4);
		System.out.println("贷记卡计算完成");
		// 存款账户
		PrintStream txt1 = new PrintStream("./AccountOutD.txt");
		PrintStream out1 = System.out;
		System.setOut(txt1);
		System.out.println("PARTY_ID\tAGT_NUM\tscore");
		countAgt("存款账户协议");
		System.setOut(out1);
		System.out.println("存款账户计算完成");
		// 贷款账户
		PrintStream txt2 = new PrintStream("./LoanOutD.txt");
		PrintStream out2 = System.out;
		System.setOut(txt2);
		System.out.println("PARTY_ID\tLOAN_ACCT_NUM_AGT_NUM\tscore");
		countLoan("贷款账户协议");
		System.setOut(out2);
		System.out.println("贷款账户计算完成");

		// 借记卡介数
		PrintStream txt5 = new PrintStream("./JjcBetween.txt");
		PrintStream out5 = System.out;
		System.setOut(txt5);
		System.out
				.println("PARTY_ID\tAGT_NUM\tCURR_TERM_MAIN_ACCT_AGT_NUM\tscore");
		betweenJjc();
		System.setOut(out5);
		System.out.println("借记卡介数计算完成");
		// 贷记卡介数
		PrintStream txt6 = new PrintStream("./DjcBetween.txt");
		PrintStream out6 = System.out;
		System.setOut(txt6);
		System.out.println("PARTY_ID\tAGT_NUM\tscore");
		betweenDjc();
		System.setOut(out6);
		System.out.println("贷记卡介数计算完成");
		// 存款账户介数
		PrintStream txt7 = new PrintStream("./AccountBetween.txt");
		PrintStream out7 = System.out;
		System.setOut(txt7);
		System.out.println("PARTY_ID\tAGT_NUM\tscore");
		betweenAccount();
		System.setOut(out7);
		System.out.println("存款账户介数计算完成");
		// 贷款账户介数
		PrintStream txt8 = new PrintStream("./LoanBetween.txt");
		PrintStream out8 = System.out;
		System.setOut(txt8);
		System.out.println("PARTY_ID\tLOAN_ACCT_NUM_AGT_NUM\tscore");
		betweenLoan();
		System.setOut(out8);
		System.out.println("贷款账户介数计算完成");
	}

	/**
	 * 计算各类节点的点度中心性
	 * 
	 * @param String
	 */
	private static void countJjc(String label) {
		SqlSession session = DBTools.getSession();
		ScoreMapper mapper = session.getMapper(ScoreMapper.class);
		try {
			List<Jjc> jjc = mapper.countJjc(label);
			for (Jjc s : jjc) {
				System.out.println(s.getPARTY_ID() + '\t' + s.getAGT_NUM()
						+ '\t' + s.getCURR_TERM_MAIN_ACCT_AGT_NUM() + '\t'
						+ s.getScore());
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}
	}

	private static void countAgt(String label) {
		SqlSession session = DBTools.getSession();
		ScoreMapper mapper = session.getMapper(ScoreMapper.class);
		try {
			List<AgtNum> agtNum = mapper.countAgt(label);
			for (AgtNum s : agtNum) {
				System.out.println(s.getPARTY_ID() + '\t' + s.getAGT_NUM()
						+ '\t' + s.getScore());
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}
	}

	private static void countLoan(String label) {
		SqlSession session = DBTools.getSession();
		ScoreMapper mapper = session.getMapper(ScoreMapper.class);
		try {
			List<Loan> loan = mapper.countLoan(label);
			for (Loan s : loan) {
				System.out.println(s.getPARTY_ID() + '\t'
						+ s.getLOAN_ACCT_NUM_AGT_NUM() + '\t' + s.getScore());
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}
	}

	/**
	 * 计算各类节点的中介中心性
	 * 
	 */
	private static void betweenJjc() {
		SqlSession session = DBTools.getSession();
		ScoreMapper mapper = session.getMapper(ScoreMapper.class);
		try {
			List<Jjc> jjc = mapper.betweenJjc();
			for (Jjc s : jjc) {
				System.out.println(s.getPARTY_ID() + '\t' + s.getAGT_NUM()
						+ '\t' + s.getCURR_TERM_MAIN_ACCT_AGT_NUM() + '\t'
						+ s.getScore());
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}
	}

	private static void betweenDjc() {
		SqlSession session = DBTools.getSession();
		ScoreMapper mapper = session.getMapper(ScoreMapper.class);
		try {
			List<AgtNum> djc = mapper.betweenDjc();
			for (AgtNum s : djc) {
				System.out.println(s.getPARTY_ID() + '\t' + s.getAGT_NUM()
						+ '\t' + s.getScore());
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}
	}

	private static void betweenAccount() {
		SqlSession session = DBTools.getSession();
		ScoreMapper mapper = session.getMapper(ScoreMapper.class);
		try {
			List<AgtNum> account = mapper.betweenAccount();
			for (AgtNum s : account) {
				System.out.println(s.getPARTY_ID() + '\t' + s.getAGT_NUM()
						+ '\t' + s.getScore());
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}
	}

	private static void betweenLoan() {
		SqlSession session = DBTools.getSession();
		ScoreMapper mapper = session.getMapper(ScoreMapper.class);
		try {
			List<Loan> loan = mapper.betweenLoan();
			for (Loan s : loan) {
				System.out.println(s.getPARTY_ID() + '\t'
						+ s.getLOAN_ACCT_NUM_AGT_NUM() + '\t' + s.getScore());
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}
	}
}