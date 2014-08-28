
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;


public class Server {
	static ServerSocket socket1;
	protected final static int port = 19999;
	static Socket connection;
	StringBuffer process;
	int T1,T2,T3;
	String TimeStamp;
	String sendText ;
	BufferedInputStream bis;
	InputStreamReader isr;
	BufferedOutputStream bos;
	OutputStreamWriter osw;
	static Gui serverGui;
	int character;
	
	
	
	public ActionListener buttonAction(){
		ActionListener actionListener = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        	if(!serverGui.getSendMessage().getText().isEmpty())
	        		serverGui.getShowMessage().setText(serverGui.getShowMessage().getText() + "\n [" + TimeStamp + "]  Server says: \n \t" + serverGui.getSendMessage().getText());
	        	sendText = serverGui.getSendMessage().getText() + (char) 13;
				try {
					osw.write(sendText);
					osw.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				finally {
					serverGui.getSendMessage().setText("");
				}
	        }
	      };
		return actionListener;
	}
	
	// @SuppressWarnings("deprecation")
	public void server() throws IOException{
		T1 = new java.util.Date().getHours();
		T2 = new java.util.Date().getMinutes();
		T3 = new java.util.Date().getSeconds();
		TimeStamp = T1 + ":" + T2 + ":" + T3;
		
		bis = new BufferedInputStream(connection.getInputStream());
		isr = new InputStreamReader(bis);
		
		process = new StringBuffer();
		while((character = isr.read()) !=  13) {
			process.append((char)character);
		}
		String dumichki = process.toString();
		if(!dumichki.isEmpty())
			serverGui.getShowMessage().setText(serverGui.getShowMessage().getText() + "\n [" + TimeStamp + "] Client says: \n \t" + dumichki);
		
		bos = new BufferedOutputStream(connection.getOutputStream());
		osw = new OutputStreamWriter(bos, "US-ASCII");
         
		serverGui.getSendButton().addActionListener(buttonAction());
		
		server();
	}
	
	public static void main(String[] args) {
		serverGui = new Gui("Server");
		try{
			socket1 = new ServerSocket(port);
			serverGui.getShowMessage().setText(serverGui.getShowMessage().getText() + "\n" + "SingleSocketServer Initialized" + "\n" );
			while (true) {
				connection = socket1.accept();
				Server myServer = new Server();
				myServer.server();
			}
		}
		catch (IOException e) {}
	}
}
