package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(java.lang.System.in);
        int sourceBase;
        int targetBase;
        String prompt = "";
        while (!prompt.equals("/exit")) {

            System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit");
            prompt = scanner.nextLine();
            switch (prompt) {
                case "/exit":
                    java.lang.System.exit(0);
                    break;
                default:
                    sourceBase = Integer.valueOf(prompt.split(" ")[0]);
                    targetBase = Integer.valueOf(prompt.split(" ")[1]);
                    convert(sourceBase, targetBase, scanner);
                   // convert(false, scanner);
                    break;
            }
        }
    }
    public static void convert(int sourceBase, int targetBase, Scanner scanner) {
        if ( 37 < sourceBase || sourceBase < 2 || targetBase < 2 || targetBase > 37) {
            return;
        }
        String input = "";
        BigInteger bigInput;
        while (!input.equals("/back")) {
            System.out.printf("Enter number in base %d to convert to base %d (To go back type /back) ", sourceBase, targetBase);
            input = scanner.nextLine();
            if (input.equals("/back")) {
                return;
            }
            System.out.println("Conversion result: " + new BigInteger(input, sourceBase)
                    .toString(targetBase));
        }
    }
}

