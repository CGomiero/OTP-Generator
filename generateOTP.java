import java.security.MessageDigest;
import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;


public class generateOTP{
    public static void main(String[] args) throws Exception{
        //Console input = System.console();
        String key = "800770FF00FF08012"; // key / IV

        String  app1Hash, app1Password;
        // int app1Count = 0;
        // perform the first step using the key
        generateOTP app1 = new generateOTP();
        app1Hash = app1.generateHash(key);
        app1Password = app1.generatePassword(app1Hash);
        System.out.println("First Hash: " + app1Hash);
		System.out.println("First OTP: " + app1Password);
        
    /* this for loops generate the first 20 OTPs (used for testing)
    for(int i = 1; i < 21; i++){
            app1Hash = app1.generateHash(app1Hash);
            app1Password = app1.generatePassword(app1Hash);
            app1Count++;

        System.out.println("Hash: " + app1Hash);
		System.out.println("OTP: " + app1Password);
		System.out.println("Number of passwords created: " + app1Count);
        }*/

        // checking for collision
        int collisionCount = 0;
        LinkedList<String> allPasswords = new LinkedList<>();
        allPasswords.add(app1Hash); //add the first hash
        int loopCount = 20;

        for(int i = 0; i < loopCount; i++){

            app1Hash = app1.generateHash(app1Hash); // generate hash
            app1Password = app1.generatePassword(app1Hash); // return password
            allPasswords.add(app1Password);

            System.out.println("Hash: " + app1Hash);
		    System.out.println("OTP: " + app1Password);

            // check all previous elements with the current password to check for any collisions
            for(String elements : allPasswords){
                if(elements.equals(app1Password)){
                    collisionCount++;
                }
            }
            
        }
        collisionCount -= loopCount; //substract the loopCount from collision because the added password will be equal to itself at least once
        System.out.println("Collision count for " + loopCount +": " + collisionCount);
    }

    // hash the input and return the string of the hash
    public String generateHash(String input) throws Exception{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(input.getBytes());
        byte byteData[] = md.digest();

        //convert bytes to hex format
        StringBuilder hexString = new StringBuilder(2 * byteData.length);
        for(int i = 0; i < byteData.length; i++){
            String hex = Integer.toHexString(0xff & byteData[i]);
            if(hex.length() == 1){
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    // create a password based on the input
    // the input is expected to be a hash of 64 bits 
    // the password will be created using the last 6 digits
    public String generatePassword(String hash){
        String password = "";
        for(int i = 58; i < 64; i++){
            password += hash.charAt(i);
        }
        return password;
    }
}
