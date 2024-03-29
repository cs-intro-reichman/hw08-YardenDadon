/** Represnts a list of musical tracks. The list has a maximum capacity (int),
 *  and an actual size (number of tracks in the list, an int). */
class PlayList {
    private Track[] tracks;  // Array of tracks (Track objects)   
    private int maxSize;     // Maximum number of tracks in the array
    private int size;        // Actual number of tracks in the array

    /** Constructs an empty play list with a maximum number of tracks. */ 
    public PlayList(int maxSize) {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }

    /** Returns the maximum size of this play list. */ 
    public int getMaxSize() {
        return maxSize;
    }
    
    /** Returns the current number of tracks in this play list. */ 
    public int getSize() {
        return size;
    }

    /** Method to get a track by index */
    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    }
    
    /** Appends the given track to the end of this list. 
     *  If the list is full, does nothing and returns false.
     *  Otherwise, appends the track and returns true. */
    public boolean add(Track track) {
        if (size == maxSize){return false;}
        else{
            tracks[size] = track; //if playlist is size = 0, puts song at index = 0
            size ++; //Cause theres a song in it now
            return true;}
    }

    /** Returns the data of this list, as a string. Each track appears in a separate line. */
    //// For an efficient implementation, use StringBuilder.
    public String toString() {
        StringBuilder strB = new StringBuilder();
        for (int i = 0; i < size; i++) {
            strB.append(tracks[i]);
            strB.append("\n");
        }
        return strB.toString();
    }

    /** Removes the last track from this list. If the list is empty, does nothing. */
     public void removeLast() {
        if (size != 0) {
            tracks[size] = null; //Removes the track
            size--; //Playlist is one song smaller
        }
    }
    
    /** Returns the total duration (in seconds) of all the tracks in this list.*/
    public int totalDuration() {
        int duration = 0;
        for (int i = 0; i < size; i++){
            duration += tracks[i].getDuration();
        }
        return duration;
    }

    /** Returns the index of the track with the given title in this list.
     *  If such a track is not found, returns -1. */
    public int indexOf(String title) {
        for (int i = 0; i < size; i++){
            if (title.equals(tracks[i].getTitle())){
                return i;
            }
        }
        return -1;
    }

    /** Inserts the given track in index i of this list. For example, if the list is
     *  (t5, t3, t1), then just after add(1,t4) the list becomes (t5, t4, t3, t1).
     *  If the list is the empty list (), then just after add(0,t3) it becomes (t3).
     *  If i is negative or greater than the size of this list, or if the list
     *  is full, does nothing and returns false. Otherwise, inserts the track and
     *  returns true. */
    public boolean add(int i, Track track) { //i = where i want the song in the playlist
        if (i < 0 || i > maxSize || size == maxSize){ //index of negative, bigger than playlist, at maxSize
            return false;
        }
        if (size == 0|| i == size){ //if empty or adding song to the end of the playlist
            add(track); //Side Affect: size++
        } else{
            for (int variable = size; variable > i; variable--){ //If adding a song in index2, all songs above move one up
                tracks[variable] = tracks[variable - 1];
            }
            tracks[i] = track; //add the songs in the hole left at index i
            size++;
        }
        return true;
       }
    
     
    /** Removes the track in the given index from this list.
     *  If the list is empty, or the given index is negative or too big for this list, 
     *  does nothing and returns -1. */
    public void remove(int i) {
        if (size == 0 || i < 0 || i > maxSize){
            return;
        }else {
            tracks[i] = null; //make a hole
            for (int variable = i; variable < size; variable++){ //Starting at the hole (i), every song above goes down 1
            tracks[variable] = tracks[variable + 1];
            }
            size--; //shrink playlist
        }
    }

    /** Removes the first track that has the given title from this list.
     *  If such a track is not found, or the list is empty, or the given index
     *  is negative or too big for this list, does nothing. */
    public void remove(String title) {
        if (indexOf(title) != -1){ //if "title" is in the playlist
            remove(indexOf(title)); //remove it
        }
    }

    /** Removes the first track from this list. If the list is empty, does nothing. */
    public void removeFirst() {
       if (size != 0){
        remove(0);
       }
    }
    
    /** Adds all the tracks in the other list to the end of this list. 
     *  If the total size of both lists is too large, does nothing. */
    //// An elegant and terribly inefficient implementation.
    public void add(PlayList other) {
        for (int i = size, j = 0; i < size + other.size; i++, j++){
            if (size + other.size <= maxSize - j){
                add(i, other.getTrack(j));
            }
        }
    }

    /** Returns the index in this list of the track that has the shortest duration,
     *  starting the search in location start. For example, if the durations are 
     *  7, 1, 6, 7, 5, 8, 7, then min(2) returns 4, since this is the index of the 
     *  minimum value (5) when starting the search from index 2.  
     *  If start is negative or greater than size - 1, returns -1.
     */
    private int minIndex(int start) {
        if (start < 0 || start > size - 1){
            return -1;
        } else {
            int minTemp = tracks[start].getDuration(); //seconds of the chosen song
            int indexTemp = start;
            for (int i = start; i < size; i++){
              //  if (tracks[i].isShorterThan(tracks[i+1]) == true && tracks[i].getDuration() < minTemp){
                if (tracks[i].getDuration() < minTemp){
                    minTemp = tracks[i].getDuration();
                    indexTemp = i;
                }
            }
            return indexTemp;
        }     
    }

    /** Returns the title of the shortest track in this list. 
     *  If the list is empty, returns null. */
    public String titleOfShortestTrack() {
        return tracks[minIndex(0)].getTitle();
    }

    /** Sorts this list by increasing duration order: Tracks with shorter
     *  durations will appear first. The sort is done in-place. In other words,
     *  rather than returning a new, sorted playlist, the method sorts
     *  the list on which it was called (this list). */
    public void sortedInPlace() {
        // Uses the selection sort algorithm,  
        // calling the minIndex method in each iteration.
        Track temp = new Track("", "", 0);
        for (int i = 0; i < size; i++){
            temp = tracks[minIndex(i)];
            tracks[minIndex(i)] = tracks[i];
            tracks[i] = temp;
            //System.out.println(minIndex(i));
            // System.out.println(tracks[0]);
            // System.out.println(tracks[1]);
            // System.out.println(tracks[2]);
            // System.out.println(tracks[3]);
            // System.out.println(tracks[4]);
            // System.out.println("");
       }
    }
}
