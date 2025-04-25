package gfc.util;

import java.io.DataOutputStream;
import java.io.DataInputStream;


public interface Serializable {

	public void writeObject(DataOutputStream out);
	public void readObject(DataInputStream in);
}
