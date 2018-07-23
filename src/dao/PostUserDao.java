package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.PostUser;
import exception.SQLRuntimeException;

public class PostUserDao {

	public List<PostUser> getUserMessages(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("posts.id, ");
			sql.append("posts.title, ");
			sql.append("posts.text, ");
			sql.append("posts.category, ");
			sql.append("users.name, ");
			sql.append("posts.created_date ");
			sql.append("FROM posts ");
			sql.append("INNER JOIN users ");
			sql.append("ON posts.user_id = users.id ");
			sql.append("ORDER BY created_date DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<PostUser> ret = toPostUserList(rs);
			return ret;
		} catch (SQLException e) {
            throw new SQLRuntimeException(e);
        } finally {
            close(ps);
        }
	}

	private List<PostUser> toPostUserList(ResultSet rs)
			throws SQLException {

		List<PostUser> ret = new ArrayList<PostUser>();
		try {
			while (rs.next()) {
				String name = rs.getString("name");
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				String title = rs.getString("title");
				String text = rs.getString("text");
				String category = rs.getString("category");
				Timestamp createdDate = rs.getTimestamp("created_date");

				PostUser post = new PostUser();
				post.setName(name);
				post.setId(id);
				post.setUserId(userId);
				post.setTitle(title);
				post.setTitle(text);
				post.setTitle(category);
				post.setCreated_date(createdDate);

				ret.add(post);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
