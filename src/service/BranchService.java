package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Branch;
import dao.BranchDao;

public class BranchService {
	private static final int LIMIT_NUM = 100;

	public List<Branch> getBranch() {

		Connection connection = null;
		try {
			connection = getConnection();

			BranchDao userDao = new BranchDao();
			List<Branch> ret = BranchDao.getBranches(connection, LIMIT_NUM);

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

}
