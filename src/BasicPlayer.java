import java.util.Objects;

/**
 * BasicPlayer class is an AI player that will simply guess the order of the alphabet.
 */
public class BasicPlayer implements WheelOfFortunePlayer{

    protected StringBuilder previousGuesses;
    protected String basicGuess;
    private int iteration;
    private String currentPhrase;

    /**
     * Constructor, initializes StringBuilder basicGuess and StringBuilder priority guesses and int iteration.
     */
    public BasicPlayer(){
        previousGuesses = new StringBuilder();
        this.iteration = 0;
        this.basicGuess = "abcdefghijklmnopqrstuvwxyz";
    }

    /**
     * setCurrentPhrase method passes in the currentPhrase as a paramater.
     * @param currentPhrase
     */
    @Override
    public void setCurrentPhrase(String currentPhrase){
        this.currentPhrase = currentPhrase;
    }

    /**
     * nextGuess method simply guesses the order of the alphabet and iterates through that.
     * @return char guess
     */
    @Override
    public char nextGuess(){
        char guess = basicGuess.charAt(iteration);
        this.iteration++;
        System.out.print("The bot guessed letter: ");
        System.out.println(guess);
        return guess;
    }
    /**
     * playerId method returns player Id
     * @return String playerId
     */
    @Override
    public String playerId(){
        return "Basic Player";
    }
    /**
     * reset method resets the iteration, priorityGuesses, and previousGuesses before each new phrase.
     */
    @Override
    public void reset(){
        this.iteration = 0;
        this.basicGuess = "abcdefghijklmnopqrstuvwxyz";
        previousGuesses = new StringBuilder();
    }

    /**
     * toString method returns the String algorithm for basic player
     * @return String
     */
    @Override
    public String toString() {
        return "BasicPlayer{" +
                " Uses this string to guess: " + basicGuess + '\'' +
                '}';
    }

    /**
     * equals method compares the algorithm for two basic players
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicPlayer that = (BasicPlayer) o;
        return Objects.equals(basicGuess, that.basicGuess);
    }

}
