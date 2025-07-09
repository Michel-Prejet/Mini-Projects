
/**
 * EmailValidator.java
 * 
 * A simple program that prompts the user for an email adress then checks
 * its validity according to the following rules:
 * - No whitespace
 * - Exactly one '@' symbol, not at the start or end
 * - At least one '.' symbol, not at the start or end
 * - Domain cannot exceed three characters
 * 
 * Author: Michel Préjet
 * Date: July 8th, 2025
 */

import java.util.Scanner;

public class EmailValidator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String email = "";
        boolean valid = false;
        boolean firstIteration = true;

        while (!valid) {
            // Prompt user
            if (firstIteration) {
                System.out.print("Enter the email address to be validated: ");
            } else {
                System.out.print("Re-enter the email address to be validated: ");
            }

            email = scan.nextLine().trim();

            // Perform all validation checks
            boolean noSpace = noWhitespace(email);
            boolean structure = noSpace && hasValidStructure(email);
            boolean domain = structure && hasValidDomainLength(email);

            valid = domain;
            firstIteration = false;
        }

        System.out.println(email + " is a valid email address.");
        scan.close();
        System.out.println(" *** PROGRAM TERMINATED SUCCESSFULLY ***");
    }

    /**
     * Checks whether the given string contains any whitespace characters.
     * 
     * @param email the string to search through
     * @return true if the string contains whitespace; false otherwise
     */
    public static boolean noWhitespace(String email) {
        for (int i = 0; i < email.length(); i++) {
            if (Character.isWhitespace(email.charAt(i))) {
                System.out.println("[Error] Email address cannot contain spaces.");
                return false;
            }
        }
        return true;
    }

    /**
     * Validates that the given string contains exactly one '@' symbol, and at
     * least one '.' symbol, and neither appears at the start or end.
     *
     * @param email the string to validate
     * @return true if the structure is valid; false otherwise
     */
    public static boolean hasValidStructure(String email) {
        // Check that there is exactly one '@'
        if (numChars(email, '@') != 1) {
            System.out.println("[Error] Email address must contain exactly one '@' symbol.");
            return false;
        }

        // Check that there is at least one '.'
        if (numChars(email, '.') < 1) {
            System.out.println("[Error] Email address must contain at least one '.' symbol.");
            return false;
        }

        // Check that '@' is not at the start or end
        if (email.startsWith("@") || email.endsWith("@")) {
            System.out.println("[Error] '@' cannot appear at the start or the end of the email address.");
            return false;
        }

        // Check that '.' is not at the start or end
        if (email.startsWith(".") || email.endsWith(".")) {
            System.out.println("[Error] '.' cannot appear at the start or the end of the email address.");
            return false;
        }

        return true;
    }

    /**
     * Checks that the length of a given email's domain (e.g. com) does not
     * exceed three characters.
     * 
     * @param email the email (string) to validate
     * @return true if the domain does not exceed three characters; false otherwise
     */
    public static boolean hasValidDomainLength(String email) {
        int dotIndex = email.lastIndexOf(".");
        if (email.length() - dotIndex > 4) {
            System.out.println("[Error] Domain of email address cannot exceed three characters.");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns the number of times a target character appears in a string.
     * 
     * @param text   the string to search through
     * @param target the character to be counted
     * @return the number of occurrences of target in text
     */
    public static int numChars(String text, char target) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) {
                count++;
            }
        }
        return count;
    }
}
