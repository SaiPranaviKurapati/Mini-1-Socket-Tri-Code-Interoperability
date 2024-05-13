package gash.payload;



/**
 * the builder to construct commands that both the client (BasicSocketClient)
 * and server (BasicServer) understands/accepts
 * 
 * @author gash
 * 
 */
import java.util.Formatter;
public class BasicBuilder {

	public BasicBuilder() {
// constructor
}

	public String encode(Message msg) {
		// var sb = new StringBuilder();
		// sb.append(msg.getGroup()).append(",").append(msg.getName()).append(",").append(msg.getText());

		// return sb.toString();
		
		StringBuilder r = new StringBuilder();
		r.append(msg.getGroup()).append(",");
		r.append(msg.getName()).append(",");
		r.append(msg.getText());

		// Format the payload length
		Formatter formatter = new Formatter();
		formatter.format("%04d", r.length());
		String payloadLength = formatter.toString();
		formatter.close();

		// Construct the message (header + payload)
		StringBuilder sb = new StringBuilder();
		sb.append(payloadLength).append(",").append(r);

		return sb.toString();
	}

	public Message decode(byte[] raw){
		if (raw == null || raw.length == 0)
			return null;

		String s = new String(raw);
	
		// Extract the length of the payload
		String lengthStr = s.substring(0, 4);
		int length = Integer.parseInt(lengthStr);
	
		// Get the actual payload by using the length
		String payload = s.substring(5, 5 + length);
		String[] parts = payload.split(",", 3);
	
		// Assuming parts[0] is the group, parts[1] is the name, and parts[2] is the text
		return new Message(parts[1], parts[0], parts[2]);

		//var s = new String(raw);
		//var parts = s.split(",", 3);
		// var rtn = new Message(parts[1], parts[0], parts[2]);

		//return new Message(parts[1], parts[0], parts[2]);
	}
}