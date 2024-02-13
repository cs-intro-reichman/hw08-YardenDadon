/** Represents a music track. A track has a title (String), an artist (String), 
 *  and a duration (int), in seconds. */
class Track {
    //First variables dictate all possible "inputs" from calling the Track function
    private String title;
    private String artist;
    private int duration;

    /** Constructs a track from the given values. */
    //Having Track(x, y, z) lets you give parameters from calling the function
    //this.title refers to the private title. Meaning for each object "track"
    //we make, it keeps the value that you inputted in.
    //"title" comes from input, input title gets assigned to this.title, then
    //this.title can be used throughout the specific object.
    public Track(String title, String artist, int duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    /** Returns this track's data as "artist, title, minutes:seconds".
     *  For example, "John Lennon, Imagine, 3:07" */
    public String toString() {
        //// Replace the following statement with code that returns
        //// the data of this track according to the method's documentation.
        return artist + ", " + title + ", " + formattedDuration(duration);
    }

    /** Returns this track's title. */
    public String getTitle() {
        return title;
    }
    /** Returns this track's artist. */
    public String getArtist() {
        return artist;
    }
    /** Returns this track's duration. */
    public int getDuration() {
        return duration;
    }

    /** If this track's duration is shorter than the other track's duration
     *  returns true; otherwise returns false. */
    public boolean isShorterThan(Track other) {
        return duration < other.duration;
    }

    // Returns a string that represents the totalSeconds as "minutes:seconds",
    // Where seconds is always two digits. For example, "3:17" or "12:05".
    private String formattedDuration(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return minutes + ":" + seconds;
    }
}