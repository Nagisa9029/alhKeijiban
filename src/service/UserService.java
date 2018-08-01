package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.IndexUser;
import beans.User;
import dao.IndexUserDao;
import dao.UserDao;
import utils.CipherUtil;

public class UserService {

	public boolean getUser(String userAccount) {

	    Connection connection = null;
	    try {
	        connection = getConnection();

	        UserDao userDao = new UserDao();
	        boolean accountCheck = userDao.getUser(connection, userAccount);

	        commit(connection);

	        return accountCheck;
	    } catch (RuntimeException e) {
	        rollback(connection);
	        throw e;
	    } catch (Error e) {
	        rollback(connection);
	        throw e;
	    } finally {
	        close(connection);
	    }
	}

	public void register(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);

			UserDao userDao = new UserDao();
			userDao.insert(connection, user);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	private static final int LIMIT_NUM = 1000;

	public List<IndexUser> getUser() {

		Connection connection = null;
		try {
			connection = getConnection();

			IndexUserDao userDao = new IndexUserDao();
			List<IndexUser> ret = userDao.getIndexUsers(connection, LIMIT_NUM);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<IndexUser> getShowUser(int userId) {

		Connection connection = null;
		try {
			connection = getConnection();

			IndexUserDao userDao = new IndexUserDao();
			List<IndexUser> ret = userDao.getShowUser(connection, userId);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public void update(User user) {

		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println(user.getPassword());

			if (user.getPassword().length() ==0 ){
				user.setPassword(null);
			}else{
				String encPassword = CipherUtil.encrypt(user.getPassword());
				user.setPassword(encPassword);
			}
			//String encPassword = CipherUtil.encrypt(user.getPassword());
			//user.setPassword(encPassword);

			UserDao userDao = new UserDao();
			userDao.update(connection, user);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}


}
