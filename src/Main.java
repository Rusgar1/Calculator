import java.util.*;

public class Main {

    public static void main(String ... args) throws Exception {
        while (true){

            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите выражение в формате: a + b, a - b, a * b, a / b.");
            String equation = scanner.nextLine();
            String[] expression = equation.split("\\s+");

            if (expression.length != 3){
                scanner.close();
                throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }

            if (expression[1].length() != 1 || !isValidIdentifier(expression[1].toCharArray()[0])) {
                scanner.close();
                throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }

            if (isRomanDigit(expression[0]) && isRomanDigit(expression[2])) {
                int a = Converter.romanToArabic(expression[0]);
                int b = Converter.romanToArabic(expression[2]);
                if (a == -1 || b == -1) {
                    throw new Exception("Используются только числа от 1 до 10");
                }
                char operator = expression[1].toCharArray()[0];
                int result = (Calculator.calculated(a, b, operator));
                if (isNegativeValue(result)) {
                    throw new Exception("т.к. в римской системе нет отрицательных чисел");
                }
                System.out.println(Converter.arabicToRoman(result));
            } else if ((isArabianDigit(expression[0]) && isDotContaining(expression[2]))
                    || (isDotContaining(expression[0]) && isArabianDigit(expression[2]))) {
                throw new Exception("Используются только ЦЕЛЫЕ числа от 1 до 10");
            } else if (isArabianDigit(expression[0]) && isArabianDigit(expression[2])) {
                int a = Integer.parseInt(expression[0]);
                int b = Integer.parseInt(expression[2]);
                if (isValueInDiap(a) && isValueInDiap(b)){
                    char operator = expression[1].toCharArray()[0];
                    System.out.println(Calculator.calculated(a, b, operator));
                } else {
                    throw new Exception("Используются только числа от 1 до 10");
                }
            } else {
                throw new Exception("т.к. используются одновременно разные системы счисления");
            }
        }
    }

    static boolean isArabianDigit(String chunk){
        return chunk.matches("\\d+");
    }

    static boolean isRomanDigit(String chunk){
        return Converter.romanToArabic(chunk) != -1 ? true : false;
    }

    static boolean isDotContaining(String chunk){
        return chunk.length() != chunk.replaceAll("\\.", "").length();
    }

    static boolean isValueInDiap(int value) {
        return value > 0 && value < 11;
    }

    static boolean isNegativeValue(int value) { //возвращает если ведичина меньше 0
        return value < 0;
    }

    static boolean isValidIdentifier(char identifier){
        return identifier == '+' ||
                identifier == '-' ||
                identifier == '*' ||
                identifier == '/';
    }
}
