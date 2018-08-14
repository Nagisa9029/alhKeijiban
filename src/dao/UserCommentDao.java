package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.UserComment;
import exception.SQLRuntimeException;

public class UserCommentDao {

	public List<UserComment> getUserComments(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("comments.id, ");
			sql.append("comments.text, ");
			sql.append("comments.user_id, ");
			sql.append("comments.branch_name, ");
			sql.append("comments.position_name, ");
			sql.append("users.name, ");
			sql.append("comments.post_id, ");
			sql.append("comments.created_date ");
			sql.append("FROM comments ");
			sql.append("INNER JOIN users ");
			sql.append("ON comments.user_id = users.id ");
			//sql.append("ORDER BY created_date DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserComment> ret = toUserCommentList(rs);
			return ret;
		} catch (SQLException e) {
            throw new SQLRuntimeException(e);
        } finally {
            close(ps);
        }
	}

	private List<UserComment> toUserCommentList(ResultSet rs)
			throws SQLException {

		List<UserComment> ret = new ArrayList<UserComment>();
		try {
			while (rs.next()) {
				int id = rs.getInt("comments.id");
				String text = rs.getString("comments.text");
				int userId = rs.getInt("comments.user_id");
				String name = rs.getString("users.name");
				int postId = rs.getInt("comments.post_id");
				String branchName = rs.getString("comments.branch_name");
				String positionName = rs.getString("comments.position_name");
				Timestamp createdDate = rs.getTimestamp("comments.created_date");

				UserComment comment = new UserComment();
				comment.setId(id);
				comment.setText(text);
				comment.setUserId(userId);
				comment.setName(name);
				comment.setPostId(postId);
				comment.setBranchName(branchName);
				comment.setPositionName(positionName);
				comment.setCreatedDate(createdDate);

				ret.add(comment);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
