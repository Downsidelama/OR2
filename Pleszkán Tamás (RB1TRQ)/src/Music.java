import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Music {
    private List<Pair<String, String>> notes;
    private List<String> lyrics;
	private String title;
	private int id = -1;
	private DatabaseHandler database;

    Music() {
        this.notes = new ArrayList<>();
        this.lyrics = new ArrayList<>();
        try {
			this.database = DatabaseHandler.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
        try {
			this.database.saveMusic(this);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
	Music(String title) {
		this();
		this.setTitle(title);
	}
	
	private void load() {
		Music music = null;
		try {
			music = database.loadMusic(this.id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.notes = music.notes;
		this.lyrics = music.lyrics;
		this.title = music.title;		
	}
	
	private void save() {
		try {
			database.saveMusic(this);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    void addNote(String note, String length) {
        synchronized (this) {
            this.notes.add(new Pair<>(note, length));
            save();
        }
    }
    
    void addNotes(List<Pair<String, String>> notes) {
    	for(Pair<String, String> note : notes) {
    		addNote(note.getP1(), note.getP2());
    	}
    }

    void addLyrics(String lyrics) {
        synchronized (this) {
            this.lyrics = Arrays.asList(lyrics.split(" "));
            save();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringOfMusic = new StringBuilder("Notes: ");
        for (Pair<String, String> note : notes) {
            stringOfMusic.append(note.getP1()).append(" ").append(note.getP2()).append(" ");
        }
        stringOfMusic = new StringBuilder(stringOfMusic.toString().trim());
        stringOfMusic.append(",\r\nLyrics: ").append(lyrics);
        return stringOfMusic.toString();
    }
    
    void clearNotes(boolean clear) {
    	this.notes.clear();
    }

    void clearNotes() {
        this.notes.clear();
        save();
    }

    List<Pair<String, String>> getNotes() {
        return notes;
    }

    String getLyricsAt(int lyricsIndex) {
        return this.lyrics.size() > lyricsIndex ? this.lyrics.get(lyricsIndex) : "???";
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		save();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<String> getLyrics() {
		return new ArrayList<>(this.lyrics);
	}
	
	public void setLyrics(List<String> lyrics) {
		this.lyrics = lyrics;
		save();
	}
}
