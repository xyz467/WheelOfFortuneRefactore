import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *  WheelOfFortuneAIGame() class creates three different AI players to automatically play a Wheel of Fortune game. The three players
 *  each have a different strategy and each players plays all three phrases. The scores of all the bots will be listed
 *  in the output, and the top two scores will the listed. The overall average score, and the average score of each
 *  bot will also be listed.
 */
public class WheelOfFortuneAIGame extends WheelOfFortune {
    private List<WheelOfFortunePlayer> players;
    private int numGames;
    private int gamesCounter;
    private WheelOfFortunePlayer currentPlayer;

    /**
     * Default Constructor. Sets the player to the basic player and only that player plays all the phrases.
     */
    public WheelOfFortuneAIGame(){
        super();
        players = new ArrayList<>();
        players.add(new BasicPlayer());
        this.numGames = phrases.size();
        gamesCounter = 0;
    }

    /**
     * Constructor where player is parameter and that player plays all the phrases.
     * @param player
     */
    public WheelOfFortuneAIGame(WheelOfFortunePlayer player){
        super();
        players = new ArrayList<>();
        players.add(player);
        this.numGames = phrases.size();
        gamesCounter = 0;
    }

    /**
     * Constructor where a list of WheelOfFortunePlayers is the paramater, and all players in the list will
     * play all the phrases.
     * @param players
     */
    public WheelOfFortuneAIGame(List<WheelOfFortunePlayer> players){
        super();
        this.players = players;
        this.numGames = players.size()* phrases.size();
        this.currentPlayer = players.get(0);
        gamesCounter = 0;
    }

    /**
     * Calls the currentPlayers nextGuess method. This depends on the algorithm of the players, returns the
     * guess as a character.
     */
    @Override
    public char getGuess(){
        char guess = currentPlayer.nextGuess();
            return guess;
        }

    /**
     * Play method allows the AI player to guess 10 times(can be adjusted) using there unique algorithm from nextGuess. Every miss
     * is counted and a perfect score is 10 points. The score will be 10 - misses.
     * @return GameRecord(score, "playerID")
     */
    @Override
    public GameRecord play(){
        int misses = 0;
        int allowedMisses = 10; //allowed misses
        System.out.println("Welcome to the Wheel of Fortune Game!");
        System.out.println("Below will be a hidden phrase with letters represented with an *");
        System.out.print("The game will continue until you guess the wrong letter ");
        System.out.print(allowedMisses);
        System.out.println(" times");
        randomPhrase();
        generateHiddenPhrase();
        currentPlayer.setCurrentPhrase(currentPhrase);
        int unguessedLettersRemaining = hiddenPhrase.length(); //initializing to above 0.
        while (misses < allowedMisses && unguessedLettersRemaining != 0) {
            unguessedLettersRemaining = 0;
            char guess = getGuess();
            boolean match = processGuess(guess);
            if (!match) {
                misses++;
            }
            for (int i = 0; i < currentPhrase.length(); i++) {
                if (hiddenPhrase.charAt(i) == '*') {
                    unguessedLettersRemaining++;
                }
            }
            System.out.print("Hidden Phrase: ");
            System.out.println(hiddenPhrase);
            if (unguessedLettersRemaining == 0) {
                System.out.println("Congrats, you guessed the correct phrase!");
            }
            else if (misses == allowedMisses) {
                System.out.println("Game over, you ran out of attempts");
            }
            else {
                System.out.println("You have " + (allowedMisses - misses) + " misses remaining");
            }
        }
        return new GameRecord(allowedMisses - misses, currentPlayer.playerId());
    }

    /**
     * playNext method determines if the AI will play another game or if a new AI player will play the game.
     * Each AI player will play until they have played all of the phrases, the phrases will be reset, and this
     * will continue until each AI has played every phrase.
     * @return boolean
     */
    @Override
    public boolean playNext(){
        numGames--;
        if(numGames >0) {
            if(phrases.size() == 0){
                readPhrases(); //need to regenerate phrases for the new player
            }
            gamesCounter++;
            currentPlayer = players.get((gamesCounter/players.size())); // could put logic in above if statement to increment, but this works
            currentPlayer.reset();
            return (numGames > 0);
        }
        else
            return false;
    }

    /**
     * toString method prints list of players and number of games to be played
     * @return String
     */
    @Override
    public String toString() {
        return "WheelOfFortuneAIGame{" +
                "players=" + players +
                ", numGames=" + numGames +
                '}';
    }

    /**
     * equals method compares the contents of an AI game
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WheelOfFortuneAIGame that = (WheelOfFortuneAIGame) o;
        return numGames == that.numGames && gamesCounter == that.gamesCounter && Objects.equals(players, that.players);
    }


    public static void main(String [] args){
        ArrayList<WheelOfFortunePlayer> players = new ArrayList<WheelOfFortunePlayer>(3);
        BasicPlayer basicPlayer = new BasicPlayer();
        PriorityPlayer priorityPlayer = new PriorityPlayer();
        SmallWordPlayer smallWordPlayer = new SmallWordPlayer();
        players.add(basicPlayer);
        players.add(priorityPlayer);
        players.add(smallWordPlayer);
        WheelOfFortuneAIGame game = new WheelOfFortuneAIGame(players);
        AllGamesRecord record = game.playAll();
        System.out.println(record);
    }
}
