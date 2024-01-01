import java.io.IOException; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.net.Socket; 
import java.util.Scanner; 
public class ChatClient { 
 private static final String SERVER_IP = "localhost"; 
 private static final int SERVER_PORT = 5550; 
 public static void main(String[] args) { 
 try { 
 Socket socket = new Socket(SERVER_IP, SERVER_PORT); 
 System.out.println("Connected to TCP Chat Server"); 
 OutputStream output = socket.getOutputStream(); 
 InputStream input = socket.getInputStream(); 
 new Thread(() -> { 
 try { 
 while (true) { 
 byte[] buffer = new byte[1024]; 
 int bytesRead = input.read(buffer); 
 if (bytesRead == -1) { 
 System.out.println("Server has disconnected."); 
 System.exit(0); 
 } 
 String message = new String(buffer, 0, bytesRead); 
 System.out.println("Received message: " + message); 
 } 
 } catch (IOException e) { 
 e.printStackTrace(); 
 } 
 }).start(); 
 Scanner scanner = new Scanner(System.in); 
 while (true) { 
 System.out.print("Enter a message: "); 
 String message = scanner.nextLine(); 
 output.write(message.getBytes()); 
 } 
 } catch (IOException e) { 
 e.printStackTrace(); 
 } 
 } 
}