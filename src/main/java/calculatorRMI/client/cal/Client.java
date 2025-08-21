package calculatorRMI.client.cal;

import calculatorRMI.server.cal.CalculatorInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try{
            // Lấy thanh ghi từ server dựa trên IP và port của server
            Registry registry = LocateRegistry.getRegistry("localhost",1099);

            // Lấy ra object
            CalculatorInterface calculator = (CalculatorInterface) registry.lookup("CalculatorService");

            // Thực thi
            Scanner scanner = new Scanner(System.in);
            while (true){
                System.out.println("============= Pick an operation =============");
                System.out.println("1. Add");
                System.out.println("2. Subtract");
                System.out.println("3. Multiply");
                System.out.println("4. Divide");
                System.out.println("5. Exit");
                System.out.println("=============================================");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                if (choice >= 5) {
                    System.out.println("Exiting...");
                    break;
                }
                System.out.print("Enter first number: ");
                double num1 = scanner.nextDouble();
                System.out.print("Enter second number: ");
                double num2 = scanner.nextDouble();

                double result = 0;
                switch (choice) {
                    case 1:
                        result = calculator.add(num1, num2);
                        System.out.println("Result: " + num1 + " + " + num2 + " = " + result);
                        break;
                    case 2:
                        result = calculator.subtract(num1, num2);
                        System.out.println("Result: " + num1 + " - " + num2 + " = " + result);
                        break;
                    case 3:
                        result = calculator.multiply(num1, num2);
                        System.out.println("Result: " + num1 + " * " + num2 + " = " + result);
                        break;
                    case 4:
                        if (num2 == 0) {
                            System.out.println("Error: Division by zero is not allowed.");
                        } else {
                            result = calculator.divide(num1, num2);
                            System.out.println("Result: " + num1 + " / " + num2 + " = " + result);
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        continue;
                }


            }

        }catch (Exception e){
            System.out.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
