package cs455.scaling.client;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Random;

public class Client {

	LinkedList<String> hashCodes;
	
	public Client(String serverHost, int serverPort, int messageRate){
		
		hashCodes = new LinkedList<String>();
	}
	
	
	

	public byte[] generateMessage(){
		byte[] msgBytes = generatePacket();
		String hash = SHA1FromBytes(msgBytes);
		
		synchronized(hashCodes){
			hashCodes.add(hash);
		}
		
		return msgBytes;
	}
	
	
	public String SHA1FromBytes(byte[] data) { 
		 MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 byte[] hash = digest.digest(data); 
		 BigInteger hashInt = new BigInteger(1, hash); 
		 
		 return hashInt.toString(16); 
		} 

	
	private byte[] generatePacket(){
		byte[] packet = new byte[8192];
		Random random = new Random();
		
		random.nextBytes(packet);
		
		return packet;
	}

}
