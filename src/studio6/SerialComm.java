package studio6;

import jssc.*;

public class SerialComm {

	SerialPort port;

	private boolean debug;  // Indicator of "debugging mode"
	
	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	
	

	// Constructor for the SerialComm class
	public SerialComm(String name) throws SerialPortException {
		port = new SerialPort(name);		
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600,
			SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1,
			SerialPort.PARITY_NONE);
		
		debug = false; // Default is to NOT be in debug mode
	}
		
	// TODO: Add writeByte() method from Studio 5
	public void writeByte(byte data) throws SerialPortException{
	    port.writeByte(data);
	    if(debug){
	    	System.out.println("<0x" + Integer.toHexString(data) + ">");
	    }
	}
	// TODO: Add available() method
	public boolean available(){
		try {
			if(port.getInputBufferBytesCount() > 0){
				return true;
			} else {
				return false;
			}
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false; 
	}
	// TODO: Add readByte() method		
	public byte readByte(){
		try {
			byte bytes[] = port.readBytes(1);
			if(debug){
				System.out.println(String.format("%02x", bytes[0]));
			}
			return bytes[0];
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// TODO: Add a main() method
	public static void main(String[] args){
		SerialComm port = null;
		try {
			port = new SerialComm("COM5");
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//port.setDebug(true);
		while(true){
			if(port.available()){
				System.out.println((char)port.readByte());
			}
		}
	}
	
}
