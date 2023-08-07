import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_-+=[]{}|\\:;\"'<>,.?/";

    public static void main(String[] args) {
        int passwordLength = 12; // Change this to set the desired password length
        String generatedPassword = generatePassword(passwordLength);
        System.out.println("Generated Password: " + generatedPassword);
    }

    public static String generatePassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        // Define the character pool from which the password characters will be selected
        String characterPool = UPPER_CASE + LOWER_CASE + NUMBERS + SPECIAL_CHARACTERS;

        // At least one character from each category
        password.append(getRandomCharacter(UPPER_CASE, random));
        password.append(getRandomCharacter(LOWER_CASE, random));
        password.append(getRandomCharacter(NUMBERS, random));
        password.append(getRandomCharacter(SPECIAL_CHARACTERS, random));

        // Fill the rest of the password with random characters from the pool
        for (int i = 4; i < length; i++) {
            password.append(getRandomCharacter(characterPool, random));
        }

        // Shuffle the generated password to mix up the characters
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(length);
            char temp = password.charAt(i);
            password.setCharAt(i, password.charAt(randomIndex));
            password.setCharAt(randomIndex, temp);
        }

        return password.toString();
    }

    private static char getRandomCharacter(String characterPool, SecureRandom random) {
        int randomIndex = random.nextInt(characterPool.length());
        return characterPool.charAt(randomIndex);
    }
}
