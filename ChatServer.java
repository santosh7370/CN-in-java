import java.io.IOException; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.net.ServerSocket; 
import java.net.Socket; 
import java.util.HashSet; 
import java.util.Set; 
public class ChatServer { 
 private static final int PORT = 5550; 
 private static Set<OutputStream> clients = new HashSet<>(); 
 public static void main(String[] args) { 
 try { 
 ServerSocket serverSocket = new ServerSocket(PORT); 
 System.out.println("TCP Chat Server is running and waiti ng for clients..."); 
 while (true) { 
 Socket clientSocket = serverSocket.accept(); 
 System.out.println("New client connected: " + clientSocket); 
 OutputStream clientOutput = clientSocket.getOutputStream(); 
 clients.add(clientOutput); 
 // Create a new thread to handle each client 
 new Thread(() -> handleClient(clientSocket)).start(); 
 } 
 } catch (IOException e) { 
 e.printStackTrace(); 
 } 
 } 
 private static void handleClient(Socket clientSocket) { 
 try { 
 InputStream clientInput = clientSocket.getInputStream(); 
 byte[] buffer = new byte[1024]; 
 while (true) { 
 int bytesRead = clientInput.read(buffer); 
 if (bytesRead == -1) { 
 break; // Client disconnected 
 } 
 String message = new String(buffer, 0, bytesRead); 
 System.out.println("Received message from client: " + message); 
 // Broadcast the message to all connected clients 
 broadcast(message); 
 } 
 } catch (IOException e) { 
 e.printStackTrace(); 
 } finally { 
 try { 
 clientSocket.close(); 
 } catch (IOException e) { 
 e.printStackTrace(); 
 } 
 } 
 } 
 private static void broadcast(String message) { 
 for (OutputStream clientOutput : clients) { 
 try { 
 clientOutput.write(message.getBytes()); 
 } catch (IOException e) { 
 e.printStackTrace(); 
 } 
 } 
 } 
}