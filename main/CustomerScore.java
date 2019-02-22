package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mapper.ScoreMapper;

import org.apache.ibatis.session.SqlSession;

import util.DBTools;
import beans.AgtNum;
import beans.Jjc;
import beans.Loan;

public class CustomerScore {
	public static void main(String[] args) {
		SqlSession session = DBTools.getSession();
		ScoreMapper mapper = session.getMapper(ScoreMapper.class);
		try {
			Map<String, Integer> degree = new HashMap<String, Integer>();
			Map<String, Integer> betweenness = new HashMap<String, Integer>();

			/**
			 * 点度中心性计算
			 */
			List<Jjc> jjc = mapper.countJjc("借记卡协议");
			System.out.println("借记卡degree查询完成");
			List<AgtNum> djc = mapper.countAgt("贷记卡协议");
			System.out.println("贷记卡degree查询完成");
			List<AgtNum> account = mapper.countAgt("存款账户协议");
			System.out.println("存款账户degree查询完成");
			List<Loan> loan = mapper.countLoan("贷款账户协议");
			System.out.println("贷款账户degree查询完成");
			for (Jjc s : jjc) {
				if (degree.isEmpty() || !degree.containsKey(s.getPARTY_ID())) {
					degree.put(s.getPARTY_ID(), s.getScore());
				} else {
					degree.put(s.getPARTY_ID(),
							degree.get(s.getPARTY_ID()) + s.getScore());
				}
			}
			jjc.clear();
			System.out.println("借记卡degree计算完成");
			for (AgtNum s : djc) {
				if (degree.isEmpty() || !degree.containsKey(s.getPARTY_ID())) {
					degree.put(s.getPARTY_ID(), s.getScore());
				} else {
					degree.put(s.getPARTY_ID(),
							degree.get(s.getPARTY_ID()) + s.getScore());
				}
			}
			djc.clear();
			System.out.println("贷记卡degree计算完成");
			for (AgtNum s : account) {
				if (degree.isEmpty() || !degree.containsKey(s.getPARTY_ID())) {
					degree.put(s.getPARTY_ID(), s.getScore());
				} else {
					degree.put(s.getPARTY_ID(),
							degree.get(s.getPARTY_ID()) + s.getScore());
				}
			}
			account.clear();
			System.out.println("存款账户degree计算完成");
			for (Loan s : loan) {
				if (degree.isEmpty() || !degree.containsKey(s.getPARTY_ID())) {
					degree.put(s.getPARTY_ID(), s.getScore());
				} else {
					degree.put(s.getPARTY_ID(),
							degree.get(s.getPARTY_ID()) + s.getScore());
				}
			}
			loan.clear();
			System.out.println("贷款账户degree计算完成");
			/**
			 * 打印输出点度中心性文件
			 */
			BufferedWriter outDegree = new BufferedWriter(new FileWriter(
					new File("/mnt/sdo1/tju_1/zz_algo/Degree/customerDegree.txt")));
			outDegree.write("PARTY_ID\tDegree\n");
			Set<String> de = degree.keySet();
			for (String s : de) {
				outDegree.write(s + '\t' + degree.get(s) + '\n');
			}
			outDegree.close();
			System.out.println("点度中心性计算完成");

			/**
			 * 中介中心性计算
			 */
			jjc = mapper.betweenJjc();
			System.out.println("借记卡betweenness查询完成");
			djc = mapper.betweenDjc();
			System.out.println("贷记卡betweenness查询完成");
			account = mapper.betweenAccount();
			System.out.println("存款账户betweenness查询完成");
			loan = mapper.betweenLoan();
			System.out.println("贷款账户betweenness查询完成");
			for (Jjc s : jjc) {
				if (betweenness.isEmpty()
						|| !betweenness.containsKey(s.getPARTY_ID())) {
					betweenness.put(s.getPARTY_ID(), s.getScore());
				} else {
					betweenness.put(s.getPARTY_ID(),
							betweenness.get(s.getPARTY_ID()) + s.getScore());
				}
			}
			System.out.println("借记卡betweenness计算完成");
			for (AgtNum s : djc) {
				if (betweenness.isEmpty()
						|| !betweenness.containsKey(s.getPARTY_ID())) {
					betweenness.put(s.getPARTY_ID(), s.getScore());
				} else {
					betweenness.put(s.getPARTY_ID(),
							betweenness.get(s.getPARTY_ID()) + s.getScore());
				}
			}
			System.out.println("贷记卡betweenness计算完成");
			for (AgtNum s : account) {
				if (betweenness.isEmpty()
						|| !betweenness.containsKey(s.getPARTY_ID())) {
					betweenness.put(s.getPARTY_ID(), s.getScore());
				} else {
					betweenness.put(s.getPARTY_ID(),
							betweenness.get(s.getPARTY_ID()) + s.getScore());
				}
			}
			System.out.println("存款账户betweenness计算完成");
			for (Loan s : loan) {
				if (betweenness.isEmpty()
						|| !betweenness.containsKey(s.getPARTY_ID())) {
					betweenness.put(s.getPARTY_ID(), s.getScore());
				} else {
					betweenness.put(s.getPARTY_ID(),
							betweenness.get(s.getPARTY_ID()) + s.getScore());
				}
			}
			System.out.println("贷款账户betweenness计算完成");
			/**
			 * 打印输出
			 */
			BufferedWriter outBetweenness = new BufferedWriter(new FileWriter(
					new File("/mnt/sdo1/tju_1/zz_algo/Degree/customerBetweenness.txt")));
			outBetweenness.write("PARTY_ID\tBetweenness\n");
			Set<String> be = degree.keySet();
			for (String s : be) {
				outBetweenness.write(s + '\t' + betweenness.get(s) + '\n');
			}
			outBetweenness.close();
			System.out.println("中介中心性计算完成");

			session.commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}
		System.out.println("Game Over!");
	}
}
