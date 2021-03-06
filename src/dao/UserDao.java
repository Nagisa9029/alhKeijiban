package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.User;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class UserDao {

	public boolean getUser(Connection connection, String account) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE account = '" + account + "'";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);

			if(userList.size() == 0){
				return true;
			}
			return false;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String account = rs.getString("account");
				String name = rs.getString("name");
				String password = rs.getString("password");
				int isStopped = rs.getInt("is_stopped");
				int branchId = rs.getInt("branch_id");
				int positionId = rs.getInt("position_id");
				//String branch = rs.getString("branches.name");
				//String position = rs.getString("positions.name");
				Timestamp createdDate = rs.getTimestamp("created_date");
				Timestamp updatedDate = rs.getTimestamp("updated_date");

				User user = new User();
				user.setId(id);
				user.setAccount(account);
				user.setName(name);
				user.setPassword(password);
				user.setIsStopped(isStopped);
				user.setBranchId(branchId);
				user.setPositionId(positionId);
				//user.setBranchName(branch);
				//user.setPositionName(position);
				user.setCreatedDate(createdDate);
				user.setUpdatedDate(updatedDate);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

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

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public User getUser(Connection connection, String account, String password) {

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
			sql.append("positions.name, ");
			sql.append("users.created_date, ");
			sql.append("users.updated_date ");
			sql.append("FROM users ");
			sql.append("INNER JOIN branches ");
			sql.append("ON users.branch_id = branches.id ");
			sql.append("INNER JOIN positions ");
			sql.append("ON users.position_id = positions.id ");
			sql.append("WHERE users.account = ? AND users.password = ?; ");

			ps = connection.prepareStatement(sql.toString());
			//String sql = "SELECT * FROM users WHERE account = ? AND password = ?";

			//ps = connection.prepareStatement(sql);
			ps.setString(1, account);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList1(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUserList1(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String account = rs.getString("account");
				String name = rs.getString("name");
				String password = rs.getString("password");
				int isStopped = rs.getInt("is_stopped");
				int branchId = rs.getInt("branch_id");
				int positionId = rs.getInt("position_id");
				String branch = rs.getString("branches.name");
				String position = rs.getString("positions.name");
				Timestamp createdDate = rs.getTimestamp("created_date");
				Timestamp updatedDate = rs.getTimestamp("updated_date");

				User user = new User();
				user.setId(id);
				user.setAccount(account);
				user.setName(name);
				user.setPassword(password);
				user.setIsStopped(isStopped);
				user.setBranchId(branchId);
				user.setPositionId(positionId);
				user.setBranchName(branch);
				user.setPositionName(position);
				user.setCreatedDate(createdDate);
				user.setUpdatedDate(updatedDate);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void loginCreate(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			String sql = "UPDATE users SET login_date = CURRENT_TIMESTAMP WHERE id =" + id ;

			ps = connection.prepareStatement(sql);

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void update(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET ");
			sql.append("account = ?, ");
			sql.append("name = ?, ");
			sql.append("is_stopped = ?, ");
			sql.append("branch_id = ?, ");
			sql.append("position_id = ?, ");
			if(user.getPassword() != null){
				sql.append("password = ?, ");
			}
			sql.append("updated_date = CURRENT_TIMESTAMP ");
			sql.append("WHERE ");
			sql.append("id = ? ");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getAccount());
			ps.setString(2, user.getName());
			ps.setInt(3, user.getIsStopped());
			ps.setInt(4, user.getBranchId());
			ps.setInt(5, user.getPositionId());
			if(user.getPassword() != null){
				ps.setString(6, user.getPassword());
				ps.setInt(7, user.getId());
			}else{
				ps.setInt(6, user.getId());
			}

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
