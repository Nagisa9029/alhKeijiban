package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.User;
import exception.SQLRuntimeException;

public class UserDao {

	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users ( ");
			sql.append("account");
			sql.append(", name");
			sql.append(", password");
			sql.append(", is_stopped");
			sql.append(", branch_id");
			sql.append(", position_id");
			sql.append(", created_date");
			sql.append(", updated_date");
			sql.append(") VALUES (");
			sql.append("?"); //account
			sql.append(", ?"); //name
			sql.append(", ?"); //password
			sql.append(", ?"); //is_stopped
			sql.append(", ?"); //branch_id
			sql.append(", ?"); //position_id
			sql.append(", CURRENT_TIMESTAMP"); // created_date
			sql.append(", CURRENT_TIMESTAMP"); // updated_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getAccount());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			ps.setInt(4, user.getIsStopped());
			ps.setInt(5, user.getBranchId());
			ps.setInt(6, user.getPositionId());

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
