package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import mapper.TransferMapper;

import org.apache.ibatis.session.SqlSession;

import util.DBTools;
import beans.OwnBean;
import beans.TransferBean;

public class TransferC {
	public static void main(String[] args) {
		SqlSession session = DBTools.getSession();
		TransferMapper mapper = session.getMapper(TransferMapper.class);
		try {
			Map<String, Integer> degree = new TreeMap<String, Integer>();
			Map<String, Integer> betweenness = new TreeMap<String, Integer>();
			Map<String, String> own = new HashMap<String, String>();

			List<OwnBean> ob = mapper.own();
			for (OwnBean s : ob) {
				own.put(s.getCardID(), s.getpID());
			}

			/**
			 * 点度中心性计算
			 */
			List<TransferBean> tb = mapper.degreeC();
			System.out.println("degree查询完成");
			for (TransferBean s : tb) {
				String pid = own.get(s.getcardID());
				if (pid == null) {
					degree.put(s.getcardID(), s.getScore());
					continue;
				}
				if (degree.isEmpty() || !degree.containsKey(pid)) {
					degree.put(pid, s.getScore());
				} else {
					degree.put(pid, degree.get(pid) + s.getScore());
				}
			}
			tb.clear();
			/**
			 * 打印输出点度中心性文件
			 */
			BufferedWriter outDegree = new BufferedWriter(
					new FileWriter(
							new File(
									"/mnt/sdo1/tju_1/zz_algo/Degree/customerDegree.csv")));
			outDegree.write("ID,Degree\n");
			Set<String> de = degree.keySet();
			for (String s : de) {
				outDegree.write(s + ',' + degree.get(s) + '\n');
			}
			outDegree.close();
			System.out.println("点度中心性计算完成");

			/**
			 * 中介中心性计算
			 */
			tb = mapper.betweenC();
			System.out.println("betweenness查询完成");
			for (TransferBean s : tb) {
				String pid = own.get(s.getcardID());
				if (pid == null) {
					betweenness.put(s.getcardID(), s.getScore());
					continue;
				}
				if (betweenness.isEmpty() || !betweenness.containsKey(pid)) {
					betweenness.put(pid, s.getScore());
				} else {
					betweenness.put(pid, betweenness.get(pid) + s.getScore());
				}
			}
			/**
			 * 打印输出
			 */
			BufferedWriter outBetweenness = new BufferedWriter(
					new FileWriter(
							new File(
									"/mnt/sdo1/tju_1/zz_algo/Degree/customerBetweenness.csv")));
			outBetweenness.write("ID,Betweenness\n");
			Set<String> be = degree.keySet();
			for (String s : be) {
				outBetweenness.write(s + ',' + betweenness.get(s) + '\n');
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
