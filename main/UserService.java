package main;

import org.apache.ibatis.session.SqlSession;

import beans.UserBean;
import mapper.UserMapper;
import util.DBTools;

public class UserService {
	public static void main(String[] args) {
		// insertUser();
		// deleteUser(1);
		// selectUserById(1);
		for(int i=0;i<30;i++) {
			countUser(i);
		}
	}

	/**
	 * 查询所有用户
	 */
	private static void countUser(int id) {
		SqlSession session = DBTools.getSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		try {
			UserBean movie = mapper.countUser(id);
			if(movie.getTitle() != null) {
				System.out.println(movie.toString());
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}
	}
}
