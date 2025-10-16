package Domain.Objects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectsQuery {
	private final ConnectionFactory factory;

	public ObjectsQuery(ConnectionFactory factory) {
		this.factory = factory;
	}

	public List<ObjectDTO> getAllObjects() throws SQLException {
		String sql = "SELECT id, name, type_id FROM objects ORDER BY id";
		List<ObjectDTO> list = new ArrayList<>();
		Connection connection = null;
		try {
			connection = factory.open();
			try (PreparedStatement ps = connection.prepareStatement(sql);
				 ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					ObjectDTO dto = new ObjectDTO();
					dto.id = rs.getInt("id");
					dto.object_name = rs.getString("name");
					dto.type_id = rs.getInt("type_id");
					list.add(dto);
				}
			}
		} finally {
			factory.close(connection);
		}
		return list;
	}
}
