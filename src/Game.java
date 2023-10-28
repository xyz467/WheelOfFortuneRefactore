/**
 * Abstract class game contains the code to loop through a set of games and recording the results.
 */
public abstract class Game {
    /**
     * Play method plays a game and returns a GameRecord
     * @return GameRecord
     */
    public abstract GameRecord play();

    /**
     * playNext method asks if the next game should be played
     * @return boolean
     */
    public abstract boolean playNext();

    /**
     * playAll method plays a set of games and records and returns an AllGamesRecord object for the set.
     * @return AllGamesRecord object for the set
     */
    public AllGamesRecord playAll(){
        AllGamesRecord allGamesRecord = new AllGamesRecord();
        do{
            GameRecord record = play();
            allGamesRecord.add(record);
        }
        while(playNext());
        return allGamesRecord;
    }
}
