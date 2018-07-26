package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.IndexUser;
import exception.SQLRuntimeException;

public class IndexUserDao {

	public List<IndexUser> getIndexUsers(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("users.id, ");
			sql.append("users.name, ");
			sql.append("users.account, ");
			sql.append("users.password, ");
			sql.append("users.is_stopped, ");
			sql.append("users.branch_id, ");
			sql.append("branches.name, ");
			sql.append("users.position_id, ");
			sql.append("positions.name ");
			sql.append("FROM users ");
			sql.append("INNER JOIN branches ");
			sql.append("ON users.branch_id = branches.id ");
			sql.append("INNER JOIN positions ");
			sql.append("ON users.position_id = positions.id; ");
			//sql.append("ORDER BY created_date DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<IndexUser> ret = toIndexUserList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<IndexUser> toIndexUserList(ResultSet rs)
			throws SQLException {

		List<IndexUser> ret = new ArrayList<IndexUser>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String account = rs.getString("account");
				String password = rs.getString("password");
				int isStopped = rs.getInt("is_stopped");
				int branchId = rs.getInt("branch_id");
				int positionId = rs.getInt("position_id");
				String branch = rs.getString("branches.name");
				String position = rs.getString("positions.name");
				//Timestamp createdDate = rs.getTimestamp("created_date");

				IndexUser user = new IndexUser();
				user.setId(id);
				user.setName(name);
				user.setAccount(account);
				user.setPassword(password);
				user.setIsStopped(isStopped);
				user.setBranchId(branchId);
				user.setPositionId(positionId);
				user.setBranchName(branch);
				user.setPositionName(position);
				//user.setCreated_date(createdDate);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}