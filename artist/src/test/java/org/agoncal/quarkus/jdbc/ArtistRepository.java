package org.agoncal.quarkus.jdbc;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;

@ApplicationScoped
public class ArtistRepository {

  @Inject
  DataSource dataSource;

  public void persist(Artist artist) throws SQLException {
    Connection conn = dataSource.getConnection();
    String sql = "INSERT INTO t_artists (id, first_name, last_name, bio, created_date) VALUES (?, ?, ?, ?, ?)";
    artist.setId(Math.abs(new Random().nextLong()));

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setLong(1, artist.getId());
      ps.setString(2, artist.getFirstName());
      ps.setString(3, artist.getLastName());
      ps.setString(4, artist.getBio());
      ps.setTimestamp(5, Timestamp.from(artist.getCreatedDate()));
      ps.executeUpdate();
    }
    conn.close();
  }

  public Artist findById(Long id) throws SQLException {
    Connection conn = dataSource.getConnection();
    String sql = "SELECT id, first_name, last_name, bio, created_date FROM t_artists WHERE id = ?";
    Artist artist = new Artist();

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setLong(1, id);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        artist.setId(rs.getLong(1));
        artist.setFirstName(rs.getString(2));
        artist.setLastName(rs.getString(3));
        artist.setBio(rs.getString(4));
        artist.setCreatedDate(rs.getTimestamp(5).toInstant());
      }
    }
    conn.close();
    return artist;
  }
}
