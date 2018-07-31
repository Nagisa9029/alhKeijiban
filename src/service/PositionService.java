package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Position;
import dao.PositionDao;

public class PositionService {
	private static final int LIMIT_NUM = 100;

	public List<Position> getPosition() {

		Connection connection = null;
		try {
			connection = getConnection();

			PositionDao userDao = new PositionDao();
			List<Position> ret = PositionDao.getPositions(connection, LIMIT_NUM);

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
