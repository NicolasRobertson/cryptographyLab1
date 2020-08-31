public class VigenereCipher{
    public static void main(String args0[]){
        String testString = "Sonic";
        String testKey = "bluebird";
        testString = makeValid(testString);
        System.out.println(testString);
        String encryptTestString = encrypt(testString, testKey);
        System.out.println(encryptTestString);
        String decryptTestString = decrypt(encryptTestString, testKey);
        System.out.println(decryptTestString);
    }

    public static String encrypt(String message, String key){
        String encryptedMessage = "";

        for(int i=0; i<message.length(); i++){
            char character = message.charAt(i);
            int messageValue = (int) character - 97;
            character = key.charAt(i%key.length());
            int keyValue = ((int) character - 97);
            int encryptedCharValue = ((messageValue + keyValue)%26) + 97;

            encryptedMessage += Character.toString((char)encryptedCharValue);
        }

        return encryptedMessage;
    }

    public static String decrypt(String message, String key){
        String decryptedMessage = "";

        for (int i=0; i<message.length(); i++){
            char character = message.charAt(i);
            int messageValue = (int) character - 19;
            character = key.charAt(i%key.length());
            int keyValue = ((int) character - 97);
            int decryptedCharValue = ((messageValue - keyValue)%26) + 97;

            decryptedMessage += Character.toString((char)decryptedCharValue);
        }

        return decryptedMessage;
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
