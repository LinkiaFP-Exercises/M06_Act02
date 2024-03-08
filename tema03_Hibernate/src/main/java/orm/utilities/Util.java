package orm.utilities;

import java.util.Scanner;

public class Util {
    private final Scanner scanner;

    public Util() {
        scanner = new Scanner(System.in);
    }

    public int pideEntero(String enunciado) {
        while (true) {
            try {
                System.out.print(enunciado);
                return Integer.parseInt(scanner.next());
            } catch (Exception e) {
                System.out.println("El valor entrado no es válido!");
            }
        }
    }

    public void close() {
        scanner.close();
    }
}
