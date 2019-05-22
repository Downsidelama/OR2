import static java.util.Arrays.stream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseHandler {

	private static DatabaseHandler instance;

	private Connection connection;

	private DatabaseHandler() throws Exception {
		String user = "abc";
		String password = "";
		String url = "jdbc:sqlite:sqlite_test.db";
		
		loadDbDriver("org.sqlite.JDBC");
		
		this.connection = DriverManager.getConnection(url, user, password);

		

		createTables();
	}

	public static DatabaseHandler getInstance() throws Exception {
		if (instance == null) {
			instance = new DatabaseHandler();
		}
		return instance;
	}

	private void loadDbDriver(String driverClassName) throws Exception {
		try {
			Class.forName(driverClassName);
		} catch (Exception e) {
			System.err.println("ERROR: failed to load JDBC driver.");
			throw e;
		}
	}

	private void createTables() throws SQLException {
		try (Statement stat = connection.createStatement()) {
			stat.executeUpdate("drop table if exists song");
			stat.executeUpdate(
					"create table song (id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(80), notes TEXT, lyrics TEXT)");
		}
	}

	public void saveMusic(Music music) throws SQLException {
		if (music.getId() != -1) {
			try (PreparedStatement saveMusic = connection
					.prepareStatement("UPDATE song SET title = ?, notes = ?, lyrics = ? WHERE id = ?;")) {
				insertMusicAttributes(music, saveMusic);
				saveMusic.executeUpdate();
			}
		} else {
			try (PreparedStatement saveMusic = connection.prepareStatement("INSERT INTO song (title, notes, lyrics) VALUES(?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS)) {
				insertMusicAttributes(music, saveMusic);
				saveMusic.executeUpdate();
				ResultSet rs = saveMusic.getGeneratedKeys();
				music.setId(rs.getInt(1));
			}
		}
	}

	private void insertMusicAttributes(Music music, PreparedStatement saveMusic) throws SQLException {
		saveMusic.setString(1, music.getTitle());
		saveMusic.setString(2,
				music.getNotes().stream().map(p -> p.getP1() + ":" + p.getP2()).collect(Collectors.joining(";")));
		saveMusic.setString(3, music.getLyrics().stream().map(l -> l.toString()).collect(Collectors.joining(" ")));
	}

	public Music loadMusic(int id) throws SQLException {
		Music music = null;
		try (PreparedStatement getMusic = connection
				.prepareStatement("SELECT id, title, notes, lyrics FROM song WHERE id = ?;")) {
			getMusic.setInt(1, id);
			ResultSet rs = getMusic.executeQuery();

			if (rs.next()) {
				music = new Music(rs.getString("title"));
				music.setLyrics(Arrays.asList(rs.getString("lyrics").split(" ")));
				music.clearNotes(false);

				List<Pair<String, String>> collect = stream(rs.getString("notes").split(";")).map(notes -> notes.split(":")).map(split -> new Pair<>(split[0], split[1])).collect(Collectors.toList());
				music.addNotes(collect);
			}

		}
		return music;
	}
}
