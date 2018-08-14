package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Comment;
import exception.SQLRuntimeException;

public class CommentDao {

	public void insert(Connection connection, Comment comment) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
			sql.append("text, ");
			sql.append("user_id, ");
			sql.append("post_id, ");
			sql.append("branch_name, ");
			sql.append("position_name, ");
			sql.append("created_date, ");
			sql.append("updated_date");
			sql.append(") VALUES (");
			sql.append("?, ");//text
			sql.append("?, ");//user_id
			sql.append("?, ");//post_id
			sql.append("?, ");//branch_name
			sql.append("?, ");//position_name
			sql.append("CURRENT_TIMESTAMP, ");//created_date
			sql.append("CURRENT_TIMESTAMP");//updated_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, comment.getText());
			ps.setInt(2, comment.getUserId());
			ps.setInt(3, comment.getPostId());
			ps.setString(4, comment.getBranchName());
			ps.setString(5, comment.getPositionName());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	public void delete(Connection connection, Comment comment) {

		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM comments WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, comment.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}
