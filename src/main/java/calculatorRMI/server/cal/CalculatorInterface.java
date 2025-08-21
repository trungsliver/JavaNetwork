package calculatorRMI.server.cal;

import java.rmi.Remote;

public interface CalculatorInterface extends Remote {
    public double add(double a, double b) throws java.rmi.RemoteException;
    public double subtract(double a, double b) throws java.rmi.RemoteException;
    public double multiply(double a, double b) throws java.rmi.RemoteException;
    public double divide(double a, double b) throws java.rmi.RemoteException;
}
