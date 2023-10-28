import java.util.Objects;

/**
 * SmallWordPlayer class is an AI player that will first guess vowels based on one or two letters words being present,
 * then it will guess in order of priority guesses based on the most frequently used letters in English.
 */
public class SmallWordPlayer implements WheelOfFortunePlayer{


    protected StringBuilder previousGuesses;
    protected StringBuilder priorityGuesses;
    private String currentPhrase;
    private String oneLetterWords;
    private String twoLetterWords;
    private int iteration;

    /**
     * Constructor, initializes StringBuilder previousGuesses and StringBuilder priority guesses and int iteration.
     */
    public SmallWordPlayer(){
        previousGuesses = new StringBuilder();
        this.iteration = 0;
        this.priorityGuesses = new StringBuilder("eariotnslcudpmhgbfywkvxzjq");
        oneLetterWords = "ai";
        twoLetterWords = "eo";
    }

    /**
     * setCurrentPhrase method passes in the currentPhrase as a paramater, so that the AI can determine if any
     * one or two letters words are present.
     * @param currentPhrase
     */
    @Override
    public void setCurrentPhrase(String currentPhrase){
        this.currentPhrase = currentPhrase;
    }

    /**
     * nextGuess method first checks for one letter words, and will guess a then i. Then it checks for two letter words
     * where it will guess e or o. Then it will guess the order of priority guesses based on their frequency in the
     * english langugae.
     * @return char guess
     */
    @Override
    public char nextGuess(){
        String oneLetterWords = "ai";
        String twoLetterWords = "eo";
        int index;
        for (int i = 0; i < currentPhrase.length() - 2; i++) { //loop checking for 1-letter words
            if (currentPhrase.charAt(i) == ' ' && currentPhrase.charAt(i + 2) == ' ') {
                char guess = oneLetterWords.charAt(0);
                int charFrequency = 0; //variable to see if a letter has been guessed before.
                for (int j = 0; j < this.previousGuesses.length(); j++) {
                    if (this.previousGuesses.charAt(j) == guess)
                        charFrequency++;
                }
                if (charFrequency == 0) {
                    this.previousGuesses.append(guess);
                    index = this.priorityGuesses.toString().indexOf(guess);
                    //deleting the letter from priority guesses so a letter isn't guessed twice.
                    this.priorityGuesses.deleteCharAt(index);
                    System.out.print("The bot guessed letter: ");
                    System.out.println(guess);
                    return guess;
                } else {
                    guess = oneLetterWords.charAt(1);
                    charFrequency = 0; //variable to see if a letter has been guessed before.
                    for (int j = 0; j < this.previousGuesses.length(); j++) {
                        if (this.previousGuesses.charAt(j) == guess) {
                            charFrequency++;
                        }
                    }
                    if (charFrequency == 0) {
                        this.previousGuesses.append(guess);
                        index = this.priorityGuesses.toString().indexOf(guess);
                        //deleting the letter from priority guesses so a letter isn't guessed twice.
                        this.priorityGuesses.deleteCharAt(index);
                        System.out.print("The bot guessed letter: ");
                        System.out.println(guess);
                        return guess;
                    }
                }

            }
        }
        for(int i = 0; i < currentPhrase.length() -3; i++) { //loop checking for 2-letter words
            if(currentPhrase.charAt(i) == ' ' && currentPhrase.charAt(i+3) == ' ') {
                char guess = twoLetterWords.charAt(0);
                int charFrequency = 0; //variable to see if a letter has been guessed before.
                for (int j = 0; j < this.previousGuesses.length(); j++) {
                    if (this.previousGuesses.charAt(j) == guess)
                        charFrequency++;
                }
                if (charFrequency == 0) {
                    this.previousGuesses.append(guess);
                    index = this.priorityGuesses.toString().indexOf(guess);
                    //deleting the letter from priority guesses so a letter isn't guessed twice.
                    this.priorityGuesses.deleteCharAt(index);
                    System.out.print("The bot guessed letter: ");
                    System.out.println(guess);
                    return guess;
                }
                else {
                    guess = twoLetterWords.charAt(1);
                    charFrequency = 0; //variable to see if a letter has been guessed before.
                    for (int j = 0; j < this.previousGuesses.length(); j++) {
                        if (this.previousGuesses.charAt(j) == guess) {
                            charFrequency++;
                        }
                    }
                    if (charFrequency == 0) {
                        this.previousGuesses.append(guess);
                        index = this.priorityGuesses.toString().indexOf(guess);
                        //deleting the letter from priority guesses so a letter isn't guessed twice.
                        this.priorityGuesses.deleteCharAt(index);
                        System.out.print("The bot guessed letter: ");
                        System.out.println(guess);
                        return guess;
                    }
                }

            }
        }

        System.out.print("The bot guessed letter: ");
        char guess = this.priorityGuesses.charAt(iteration); //the bot will guess the most common letters in order.
        this.iteration++; //increment the counter to guess the next most common letter next turn.
        System.out.println(guess);
        return guess;
    }

    /**
     * playerId method returns player Id
     * @return String playerId
     */
    @Override
    public String playerId(){
        return "Small Word Player";
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
     * toString returns the String algorithm for player
     * @return String
     */
    @Override
    public String toString() {
        return "SmallWordPlayer{" +
                " Uses this string to guess: " + priorityGuesses +
                " If a one letter word is present: player will guess a then i. If a two letter word is present" +
                " player will guess e then o." +
                '}';
    }

    /**
     * equals method compares the algorithms of two small word players.
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmallWordPlayer that = (SmallWordPlayer) o;
        return Objects.equals(priorityGuesses, that.priorityGuesses) && Objects.equals(oneLetterWords, that.oneLetterWords) && Objects.equals(twoLetterWords, that.twoLetterWords);
    }

}
