import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class VigenereCipher {
    public static void main(String args0[]) throws FileNotFoundException {
        ArrayList<String> keys = getKeys();
        ArrayList<String> solutions = new ArrayList<>();
        
        File aaronAndJoe = new File("AaronAndJoe.txt");
        File cassieAndJacob = new File("CassieAndJacob.txt");
        File elena = new File("elena.txt");
        File taylorAndAaron = new File("TaylorAndAaron.txt");
        File melissaAndAnanya = new File("MelissAndAnanya.txt");

        //Solved Encryptions
       // System.out.println(textToEncrypt);
        String CassieAndJacobText = getTextFromFile(cassieAndJacob);
        String AaronAndJoeText = getTextFromFile(aaronAndJoe);
        String elenaText = getTextFromFile(elena);
        String TaylorAndAaron = getTextFromFile(taylorAndAaron);
        String MelissaAndAnanya = getTextFromFile(melissaAndAnanya);

        ArrayList<String> solvedDecryptions = new ArrayList<>();
        CassieAndJacobText = decrypt(CassieAndJacobText, "rolled");
        AaronAndJoeText = decrypt(AaronAndJoeText, "swamp");
        elenaText = decrypt(elenaText, "oscar");
        TaylorAndAaron = decrypt(TaylorAndAaron,"united");
        MelissaAndAnanya = decrypt(MelissaAndAnanya, "quasar");

        solvedDecryptions.add(CassieAndJacobText);
        solvedDecryptions.add(AaronAndJoeText);
        solvedDecryptions.add(elenaText);
        solvedDecryptions.add(TaylorAndAaron);
        solvedDecryptions.add(MelissaAndAnanya);
        printAll(solvedDecryptions);
       
       //Our text to Encrypt
       File file = new File("hobbit.txt");
       String textToEncrypt = getTextFromFile(file);
       textToEncrypt = encrypt(textToEncrypt, "daunting");
       // System.out.println(textToEncrypt);
       // System.out.println(decrypt(textToEncrypt, "daunting"));

       
    
       /* solutions.clear();
        solutions = decryptALL(keys,MelissaAndAnanya);
   
        solutions = lookForCommonWords(solutions); */
    /*    System.out.println(solutions.size() + " Solutions remain");
        printAll(solutions);
        System.out.println(keys.get(22311));*/


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
            if(list.get(i).contains("the") && list.get(i).contains("in") && list.get(i).contains("and") && list.get(i).contains("ing")
            && list.get(i).contains("cryptography") ){
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
