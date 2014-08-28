import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Gui {

	private String title;
	private JFrame frame = new JFrame();
	private JTextArea sendMessage = new JTextArea();
	private JTextArea showMessage = new JTextArea();
	private JScrollPane sendMessageScrollPane = new JScrollPane
			(sendMessage, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JScrollPane showMessageScrollPane = new JScrollPane
			(showMessage, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JButton sendButton = new JButton("Send");
	
	
	public Gui(){
		
	}
	public Gui(String windowName){
		setTitle(windowName);
		showGui();
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public JTextArea getSendMessage() {
		return sendMessage;
	}
	public void setSendMessage(JTextArea sendMessage) {
		this.sendMessage = sendMessage;
	}
	public JTextArea getShowMessage() {
		return showMessage;
	}
	public void setShowMessage(JTextArea showMessage) {
		this.showMessage = showMessage;
	}
	public JButton getSendButton() {
		return sendButton;
	}
	public void setSendButton(JButton sendButton) {
		this.sendButton = sendButton;
	}
	
	public ActionListener buttonAction(){
		ActionListener actionListener = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          getShowMessage().setText(getShowMessage().getText() + getSendMessage().getText()+"\n");
	          getSendMessage().setText(null);
	        }
	      };
		return actionListener;
	}

	private void  showGui(){
		frame.setTitle(title);
	    frame.setLayout(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    sendMessage.setLineWrap(true);
	    sendMessage.setBounds(10, 350, 300, 100);
	    sendMessageScrollPane.setBounds(10, 350, 379, 100);
	   
	    showMessage.setEditable(false);
	    showMessage.setLineWrap(true);
	    showMessage.setBounds(10, 10, 450, 300);
	    showMessageScrollPane.setBounds(10, 10, 450, 300);

	    sendButton.setBounds(400, 370, 70, 50);
	    
	    frame.add(sendButton);
	    frame.add(sendMessageScrollPane);
	    frame.add(showMessageScrollPane);
	    frame.pack();
	    frame.setSize(500, 500);
	    frame.setVisible(true);
	}

}
