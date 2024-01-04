import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class RARPServer {
private static final int PORT = 5570;
public static void main(String[] args) {
try {
DatagramSocket socket = new DatagramSocket(PORT);
System.out.println("RARP Server is running...");
while (true) {
byte[] receiveBuffer = new byte[1024];
// Receive RARP request from the client
DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
socket.receive(receivePacket);
// Extract hardware address from the request
String hardwareAddress = new String(receivePacket.getData(), 0,
receivePacket.getLength());
System.out.println("Received RARP request for hardware address: " + hardwareAddress);
// Simulate RARP resoluƟon (In this example, a simple mapping is used)
String ipAddress = resolveIpAddress(hardwareAddress);
// Send the simulated RARP response back to the client
byte[] sendBuffer = ipAddress.getBytes();
DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length,
receivePacket.getAddress(), receivePacket.getPort());
socket.send(sendPacket);
}
} catch (IOException e) {
e.printStackTrace();
}
}
private static String resolveIpAddress(String hardwareAddress) {
// In a real RARP server, this method would perform actual RARP resoluƟon.
// For simplicity, we use a simple mapping here.
switch (hardwareAddress.toLowerCase()) {

case "00:0a:95:9d:68:16":
return "192.168.1.1";
case "00:0a:95:9d:68:17":
return "192.168.1.2";
default:
return "Unknown IP Address";
}
}
}