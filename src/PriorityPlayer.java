import java.util.Objects;

/**
 * SmallWordPlayer class is an AI player that will guess in order of priority guesses based on the
 * most frequently used letters in English.
 */
public class PriorityPlayer implements WheelOfFortunePlayer{
    protected StringBuilder previousGuesses;
    protected StringBuilder priorityGuesses;
    private int iteration;
    private String currentPhrase;

    /**
     * Constructor, initializes StringBuilder priorityGuesses and StringBuilder priority guesses and int iteration.
     */
    public PriorityPlayer(){
        previousGuesses = new StringBuilder();
        this.iteration = 0;
        this.priorityGuesses = new StringBuilder("eariotnslcudpmhgbfywkvxzjq");
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
     * nextGuess method simply guesses the order of the most frequently used letters in the english language
     * @return char guess
     */
    @Override
    public char nextGuess(){
        char guess = priorityGuesses.charAt(iteration);
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
        return "Priority Player";
    }
    /**
     * reset method resets the iteration, priorityGuesses, and previousGuesses before each new phrase.
     */
    @Override
    public void reset(){
        this.iteration = 0;
        this.priorityGuesses = new StringBuilder("eariotnslcudpmhgbfywkvxzjq");
        previousGuesses = new StringBuilder();
    }

    /**
     * toString method to print algorithm description
     * @return String
     */
    @Override
    public String toString() {
        return "PriorityPlayer{" +
                " Uses this string to guess: " + priorityGuesses +
                '}';
    }

    /**
     * equals method compares the algorithm for the player
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriorityPlayer that = (PriorityPlayer) o;
        return Objects.equals(priorityGuesses, that.priorityGuesses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priorityGuesses);
    }
}
