package cryptographyLab1;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class VigenereCipher {
    public static void main(String args0[]) throws FileNotFoundException {
        String testString = "Sonic is cool";
        String testKey = "grep";
        testString = makeValid(testString);
        System.out.println(testString);
        String encryptTestString = encrypt(testString, testKey);
        System.out.println(encryptTestString);
        String decryptTestString = decrypt(encryptTestString, testKey);
        System.out.println(decryptTestString);
        String story = "I was walking in the forest";
//////////////////////////////////////////////////////////////
        ArrayList<String> keys = getKeys();
        ArrayList<String> solutions = new ArrayList<>();
        String textToEncrypt = story;
        textToEncrypt = encrypt(textToEncrypt, "haircut");
        
        String decryptedText;
        for(int i = 0;i<keys.size();i++){
            decryptedText = decrypt(textToEncrypt, keys.get(i));
            solutions.add(decrypt(textToEncrypt, keys.get(i)));
        }
        System.out.println(solutions.size());
        solutions = lookForCommonWords(solutions);
        System.out.println(solutions.size());
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

    private static char loopKey(String key, int index) {
        int keyIndex = index % key.length();
        return key.charAt(keyIndex);
    }

    public static ArrayList<String> getKeys() throws FileNotFoundException {
        File file = new File("cryptographyLab1\\goodwords.txt");
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
    public static ArrayList<String> lookForCommonWords(ArrayList<String> list){
        ArrayList<String> validtext = new ArrayList<>();
        for(int i = 0;i<list.size();i++){
            if(list.get(i).contains("the")){
                validtext.add(list.get(i));
            }
        }
        return validtext;
    }
}
