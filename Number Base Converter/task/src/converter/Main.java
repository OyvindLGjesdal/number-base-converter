package converter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number in decimal system: ");
        Integer inputDecimal = scanner.nextInt();
        Integer base;

        do {
            System.out.print("Enter target base: ");
            base = scanner.nextInt();
        }
        while (base!=2 && base != 8 && base != 16 );

        String result;
        if (base == 2) { result = Integer.toBinaryString(inputDecimal);}
        else if (base == 8) {
            result = Integer.toOctalString(inputDecimal);
        }
        else {
                result = Integer.toHexString(inputDecimal);
            }
        System.out.printf("Conversion result: %s",result);}

    }
