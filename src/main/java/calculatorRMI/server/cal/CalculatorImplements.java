package calculatorRMI.server.cal;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImplements extends UnicastRemoteObject implements CalculatorInterface, Serializable {
    public CalculatorImplements() throws RemoteException {
    }

    @Override
    public double add(double a, double b) throws RemoteException {
        return a+b;
    }

    @Override
    public double subtract(double a, double b) throws RemoteException {
        return a-b;
    }

    @Override
    public double multiply(double a, double b) throws RemoteException {
        return a*b;
    }

    @Override
    public double divide(double a, double b) throws RemoteException {
        return a/b;
    }
}
