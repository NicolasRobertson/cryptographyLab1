import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class VigenereCipher {
    public static void main(String args0[]) throws FileNotFoundException {
        ArrayList<String> keys = getKeys();
        ArrayList<String> solutions = new ArrayList<>();    

        //Solved Encryptions
       // System.out.println(textToEncrypt);
       String hobbit = "In a hole in the ground there lived a hobbit. Not a nasty, dirty, wet hole, filled with the ends of worms "+
       "and an oozy smell, nor yet a dry, bare, sandy hole with nothing in it to sit down on or to eat; it was a hobbit-hole, and that"+
       " means comfort It had a perfectly round door like a porthole, painted green, with a shiny yellow brass knob in the exact middle."+
       " The door opened on to a tube-shaped hall like a tunnel: a very comfortable tunnel without smoke, with paneled walls, and floors "+
       "tiled and carpeted, provided with polished chairs, and lots and lots of pegs for hats and coats – the hobbit was fond of visitors."+
       " The tunnel wound on and on, going fairly but not quite straight into the side of the hill – The Hill, as all the people for many "+
       "miles round called it – and many little round doors opened out of it, first on one side and then on another. No going upstairs for"+
       "the hobbit: bedrooms, bathrooms, cellars, pantries (lots of these), wardrobes (he had whole rooms devoted to clothes), kitchens,"+
       "dining-rooms, all were on the same floor, and indeed on the same passage. The best rooms were all on the left-hand side (going in),"+
       "for these were the only ones to have windows, deep-set round windows looking over his garden, and meadows beyond, sloping down to the"+ 
       "river This hobbit was a very well-to-do hobbit, and his name was Baggins. The Bagginses had lived in the neighbourhood of The Hill for" +
       "time out of mind, and people considered them very respectable, not only because most of them were rich, but also because they never had"+
       "any adventures or did anything unexpected: you could tell what a Baggins would say on any question without the bother of asking him. This" +
       "is a story of how a Baggins had an adventure, and found himself doing and saying things altogether unexpected. He may have lost the "+
       "neighbours’ respect, but he gained – well, you will see whether he gained anything in the end.";
       //Our text to Encrypt
       String textToEncrypt = makeValid(hobbit);
       textToEncrypt = encrypt(textToEncrypt, "daunting");
        System.out.println(textToEncrypt);
        System.out.println(decrypt(textToEncrypt, "daunting"));
       // System.out.println(textToEncrypt);
       // System.out.println(decrypt(textToEncrypt, "daunting"));

       
    
        solutions = decryptALL(keys,textToEncrypt);
   
        solutions = lookForCommonWords(solutions); 
        System.out.println(solutions.size() + " Solutions remain");
        printAll(solutions);


    }

    public static void printAll(ArrayList list){
        for(int i = 0; i < list.size();i++){
            System.out.println(list.get(i));
        }
    }

    public static String getTextFromFile(File file) throws FileNotFoundException {
        String text = "";
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()){
            text += scan.nextLine();
        }
        scan.close();
        return text;
    }

    public static String encrypt(String message, String key) {
        String encryptedMessage = "";
        message = makeValid(message);

        for (int i = 0; i < message.length(); i++) {
            char character = message.charAt(i);
            int messageValue = (int) character - 97;
            character = key.charAt(i % key.length());
            int keyValue = ((int) character - 97);
            int encryptedCharValue = ((messageValue + keyValue) % 26) + 97;

            encryptedMessage += Character.toString((char) encryptedCharValue);
        }

        return encryptedMessage;
    }

    public static String decrypt(String message, String key) {
        String decryptedMessage = "";

        for (int i = 0; i < message.length(); i++) {
            char character = message.charAt(i);
            int messageValue = (int) character - 19;
            character = key.charAt(i % key.length());
            int keyValue = ((int) character - 97);
            int decryptedCharValue = ((messageValue - keyValue) % 26) + 97;

            decryptedMessage += Character.toString((char) decryptedCharValue);
        }

        return decryptedMessage;
    }

    public static ArrayList<String> decryptALL(ArrayList<String> keys, String message){
        ArrayList<String> solutions = new ArrayList<>();
        for(int i = 0; i < keys.size();i++ ){
            solutions.add( decrypt(message, keys.get(i)));
        }
        return solutions;
    }
    private static char loopKey(String key, int index) {
        int keyIndex = index % key.length();
        return key.charAt(keyIndex);
    }

    public static ArrayList<String> getKeys() throws FileNotFoundException {
        File file = new File("goodwords.txt");
        Scanner scan = new Scanner(file);
        ArrayList<String> words = new ArrayList<>();
        while(scan.hasNextLine()){
            words.add(scan.nextLine());
        }
        scan.close();
        return words;
        
    }
    public static String makeValid(String inValidText){
        String validText = "";
        inValidText = inValidText.toLowerCase();

        for(int i = 0; i<inValidText.length();i++){
            if(inValidText.charAt(i) >= 97 && inValidText.charAt(i) <= 122){//only accepting lowercase letters into the text
                validText += inValidText.charAt(i);// putting the valid char into the validString
            }
        }
        
        return validText;
    }

    //The idea here was to change out the word the program is looking for with words that would likely be in the text
    // So after all the keys are put in, this functions filter out the ones that do no contain key words
    // Afterwards if needed new keywords are added to lower the amount of viable solutions.
    public static ArrayList<String> lookForCommonWords(ArrayList<String> list){
        ArrayList<String> validtext = new ArrayList<>();
        for(int i = 0;i<list.size();i++){
            String testText = list.get(i);
            if(testText.contains("the") && testText.contains("and") && testText.contains("ing")  && testText.contains("that")
            && testText.contains("have")){
                validtext.add(list.get(i));
                System.out.println(i);
            }
        }
        return validtext;
    }

    public static String[] segmentText(String textToSegment, int lengthOfSegment){
        String[] textSegments = new String[lengthOfSegment];
        
        for(int i=0; i<textToSegment.length(); i++){
            textSegments[i%lengthOfSegment] += textToSegment.charAt(i);
        }
        for(int i=0; i<textSegments.length; i++){
            System.out.print(i + " " + textSegments[i] + "\n");
        }
        return textSegments;
    }
}
