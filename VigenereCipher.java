//package cryptographyLab1;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class VigenereCipher {
    public static void main(String args0[]) throws FileNotFoundException {
        ArrayList<String> keys = getKeys();
        ArrayList<String> keysUsed = new ArrayList<>();
        ArrayList<String> solutions = new ArrayList<>();
        String textToEncrypt =  "I was walking in the forest";;
        textToEncrypt = encrypt(textToEncrypt, "apples");
        String textToDecrypt = "bxvkegwwtcdhnpoyqdgtvewfvdxaocxauosakdkmxarxxwzaaaqqjhvtkliuszvo" + 
        "tyhhxqellzkdomplsiqfroigagzkmwfbptspruytqefxvxhlmbbrtllvtfehlxzrhhtkzbifeeeslprthagkdtk" + 
        "ovvrltcssdhomprkpxabxwhdxzefeswxicuqbzowinlljlfcontzzuumvciepwwksytcnpqgnhvgezlcgavjyzre" + 
        "lbeubifogxvjigdfysticeyxmvlrxuwidhncsslqaovbwbtkrfxjhwtbcxafrhqsbtgrklfejlpotzqnhzkdtvfz" + 
        "mguzdhowqntrnlrxwggpvfuwonlbugwksxleiwlvjupcpemntgzwcmsewjiwjocmmwgnhosvtbl";

        String dumb = segmentText(textToDecrypt, 8);
        String decryptedText = decrypt(textToDecrypt, "gbnufiul");
        System.out.println(decryptedText);

        for(int i = 0;i<keys.size();i++){
            solutions.add(decrypt(textToDecrypt, keys.get(i)));
            keysUsed.add(keys.get(i));
        }
        System.out.println(solutions.size() + " Solutions remain");
        solutions = lookForCommonWords(solutions);
        System.out.println(solutions.size() + " Solutions remain");
        for(int i = 0; i < solutions.size();i++){
            System.out.println(solutions.get(i));
        }
        System.out.println(keys.get(19680));
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
            if(list.get(i).contains("the") && (list.get(i).contains("in") && (list.get(i).contains("and")) && (list.get(i).contains("of")) && list.get(i).contains("to")) ){
                validtext.add(list.get(i));
                System.out.println(i);
            }
        }
        return validtext;
    }

    public static String segmentText(String textToSegment, int lengthOfSegment){
        String[] textSegments = new String[lengthOfSegment];
        
        for(int i=0; i<textToSegment.length(); i++){
            textSegments[i%lengthOfSegment] += textToSegment.charAt(i);
        }
        for(int i=0; i<textSegments.length; i++){
            System.out.print(i + " " + textSegments[i] + "\n");
        }
        return "";
    }
}
