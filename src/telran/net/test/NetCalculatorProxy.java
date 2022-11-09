package telran.net.test;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;

import telran.net.NetworkHandler;

public class NetCalculatorProxy implements Calculator, Closeable {
	private NetworkHandler networkHandler;
	
	NetCalculatorProxy(NetworkHandler networkHandler){
		this.networkHandler = networkHandler;
	}
	@Override
	public void close() throws IOException {
		
		networkHandler.close();
	}
	@Override
	public double add(double op1, double op2) {
		Double[] requestData = {op1, op2};
		return networkHandler.send("add", requestData);
	}

	@Override
	public double subtract(double op1, double op2) {
		Double[] requestData = {op1, op2};
		
		return networkHandler.send("subtract", requestData);
	}

	@Override
	public double divide(double op1, double op2) {
		Double[] requestData = {op1, op2};
		
		return networkHandler.send("divide", requestData);
	}

	@Override
	public double multiply(double op1, double op2) {
		Double[] requestData = {op1, op2};
		
		return networkHandler.send("multiply", requestData);
	}

}