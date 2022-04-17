public class Calculator {
    public static int calculated(int num1, int num2, char op){
        switch (op){
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            case '/': return num1 / num2;
        }
        return -1;
    }
}
