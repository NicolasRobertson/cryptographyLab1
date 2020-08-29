

public class VigenereCipher{
    public static void main(String args0[]){
        String testString = makeValid("INVALID TEXT!!!");
        String testKey = "loud";
        System.out.println(testString);
        String encryptTestString = encrypt(testString, testKey);
        System.out.println(encryptTestString);
    }

    public static String encrypt(String message, String key){
        String encryptedMessage = "";

        for(int i=0; i<message.length(); i++){
            char character = message.charAt(i);
            int messageValue = (int) character - 97;
            character = key.charAt(i%key.length());
            int keyValue = ((int) character - 97);
            System.out.println(keyValue);
            int encryptedCharValue = ((messageValue + keyValue)%26) + 97;

            encryptedMessage += Character.toString((char)encryptedCharValue);
        }
        return encryptedMessage;
    }
    
    private static char loopKey(String key,int index){
        int keyIndex = index % key.length();
        return key.charAt(keyIndex);
        
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
}
