package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.UserPost;
import exception.SQLRuntimeException;

public class UserPostDao {

	public List<UserPost> getUserPosts(Connection connection, String dateStr, String dateEnd, String category, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("posts.user_id, ");
			sql.append("posts.id, ");
			sql.append("posts.title, ");
			sql.append("posts.text, ");
			sql.append("posts.category, ");
			sql.append("users.name, ");
			sql.append("posts.created_date ");
			sql.append("FROM posts ");
			sql.append("INNER JOIN users ");
			sql.append("ON posts.user_id = users.id ");
			if (category == null ) {
				sql.append("WHERE posts.created_date BETWEEN '" + dateStr + " 00:00:00' AND '" + dateEnd + " 23:59:59'" );
			} else {
				sql.append("WHERE posts.created_date BETWEEN '" + dateStr + " 00:00:00' AND '" + dateEnd + " 23:59:59' AND posts.category LIKE '%" + category + "%' ");
			}
			sql.append("ORDER BY created_date DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserPost> ret = toUserPostList(rs);
			return ret;
		} catch (SQLException e) {
            throw new SQLRuntimeException(e);
        } finally {
            close(ps);
        }
	}

	private List<UserPost> toUserPostList(ResultSet rs)
			throws SQLException {

		List<UserPost> ret = new ArrayList<UserPost>();
		try {
			while (rs.next()) {
				String name = rs.getString("users.name");
				int id = rs.getInt("posts.id");
				int userId = rs.getInt("posts.user_id");
				String title = rs.getString("posts.title");
				String text = rs.getString("posts.text");
				String category = rs.getString("posts.category");
				Timestamp createdDate = rs.getTimestamp("posts.created_date");

				UserPost post = new UserPost();
				post.setName(name);
				post.setId(id);
				post.setUserId(userId);
				post.setTitle(title);
				post.setText(text);
				post.setCategory(category);
				post.setCreatedDate(createdDate);

				ret.add(post);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
