package mapper;

import beans.UserBean;

public interface UserMapper {
	/**
	 * 查询并计算指定ID的节点的度
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public UserBean countUser(int id) throws Exception;
}
