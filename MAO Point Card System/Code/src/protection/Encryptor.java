package protection;

/**
 * Created by Sudarshan on 5/19/2016.
 */
public class Encryptor {
    private static final int SHIFT_ASCII = 3;

    public static String encrypt(String toEncrypt){
        char[] data = toEncrypt.toCharArray();
        String toReturn = "";

        for(char c: data){
            char newChar = (char)((int)(c) + SHIFT_ASCII);
            toReturn+=newChar;
        }

        return toReturn;
    }

    public static String decrypt(String toEncrypt){
        char[] data = toEncrypt.toCharArray();
        String toReturn = "";

        for(char c: data){
            char newChar = (char)((int)(c) - SHIFT_ASCII);
            toReturn+=newChar;
        }

        return toReturn;
    }
}
