
import java.net.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Client {
	static Gui clientGui ;
	BufferedOutputStream bos;
	OutputStreamWriter osw;
	String sendText;
	int T1,T2,T3;
	String TimeStamp;
	static String host = "localhost";
	static int port = 19999;
	static StringBuffer process;
	static InetAddress address;
	static Socket connection;
	BufferedInputStream bis;
	InputStreamReader isr;
	int character;
	 
	public ActionListener buttonAction(){
		ActionListener actionListener = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        	if(!clientGui.getSendMessage().getText().isEmpty())
	        		clientGui.getShowMessage().setText(clientGui.getShowMessage().getText() + "\n [" + TimeStamp + "]  Client says: \n \t" + clientGui.getSendMessage().getText());
				sendText = clientGui.getSendMessage().getText() + (char) 13;
				try {
					osw.write(sendText);
					osw.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				finally {
					clientGui.getSendMessage().setText("");
				}
	        }
	      };
		return actionListener;
	}
	
	
	@SuppressWarnings("deprecation")
	public  void client() throws IOException{
		
		T1 = new java.util.Date().getHours();
		T2 = new java.util.Date().getMinutes();
		T3 = new java.util.Date().getSeconds();
		TimeStamp = T1 + ":" + T2 + ":" + T3;
		
		bos = new BufferedOutputStream(connection.getOutputStream());
		osw = new OutputStreamWriter(bos, "US-ASCII");
		clientGui.getSendButton().addActionListener(buttonAction());

		bis = new BufferedInputStream(connection.getInputStream());
		isr = new InputStreamReader(bis, "US-ASCII");
		
		process = new StringBuffer();
		while ((character = isr.read()) != 13){
			process.append( (char) character);
		}
		String dumichki = process.toString();
		if(!dumichki.isEmpty())
			clientGui.getShowMessage().setText(clientGui.getShowMessage().getText() + "\n [" + TimeStamp + "] Server says: \n \t" + dumichki);

		client();
	}
	
	public static void main(String[] args) {
		clientGui = new Gui("Client");		

		clientGui.getShowMessage().setText(clientGui.getShowMessage().getText() + "\n" + "SocketClient initialized");
		try {
			address = InetAddress.getByName(host);
			connection = new Socket(address, port);
			Client myClient = new Client();
			myClient.client();
		}
		catch (IOException f) {
			System.out.println("IOException: " + f);
		}
	}
}

