import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
public class RARPClient {
private static final String SERVER_IP = "localhost";
private static final int SERVER_PORT = 5570;
public static void main(String[] args) {
try {
Scanner scanner = new Scanner(System.in);
System.out.print("Enter hardware address for RARP request: ");
String hardwareAddress = scanner.nextLine();
// Send RARP request to the server
DatagramSocket socket = new DatagramSocket();
InetAddress serverAddress = InetAddress.getByName(SERVER_IP);
byte[] sendBuffer = hardwareAddress.getBytes();
DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length,
serverAddress, SERVER_PORT);
socket.send(sendPacket);
// Receive the simulated RARP response from the server
byte[] receiveBuffer = new byte[1024];
DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
socket.receive(receivePacket);
// Extract the IP address from the response
String ipAddress = new String(receivePacket.getData(), 0, receivePacket.getLength());
System.out.println("Simulated RARP resolution result: " + ipAddress);

// Close the socket
socket.close();
} catch (IOException e) {
e.printStackTrace();
}
}
}