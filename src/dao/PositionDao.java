package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Position;
import exception.SQLRuntimeException;

public class PositionDao {

	public List<Position> getPositions(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM positions";
			//sql.append("ORDER BY users.created_date DESC limit " + num);

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Position> ret = toPositionList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Position> toPositionList(ResultSet rs)
			throws SQLException {

		List<Position> ret = new ArrayList<Position>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				//Timestamp createdDate = rs.getTimestamp("users.created_date");
				//Timestamp updatedDate = rs.getTimestamp("users.updated_date");

				Position position = new Position();
				position.setId(id);
				position.setName(name);

				ret.add(position);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}