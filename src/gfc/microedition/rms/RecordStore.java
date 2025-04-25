package gfc.microedition.rms;

import java.util.Vector;

//TODO

public class RecordStore {

	private Vector recordListener;

	private RecordStore() {
	}

	public void setRecord(int recordId, byte newData[], int offset, int numBytes)
			throws RecordStoreNotOpenException, InvalidRecordIDException,
			RecordStoreException, RecordStoreFullException {
	}

	public static void deleteRecordStore(String recordStoreName)
			throws RecordStoreException, RecordStoreNotFoundException {
	}

	public static RecordStore openRecordStore(String recordStoreName,
			boolean createIfNecessary) throws RecordStoreException,
			RecordStoreFullException, RecordStoreNotFoundException {
		return null;
	}

	public static RecordStore openRecordStore(String recordStoreName,
			String vendorName, String suiteName) throws RecordStoreException,
			RecordStoreNotFoundException {
		return null;
	}

	public void closeRecordStore() throws RecordStoreNotOpenException,
			RecordStoreException {
	}

	public void setMode(int authmode, boolean writable)
			throws RecordStoreException {
	}

	public static String[] listRecordStores() {
		return null;
	}

	public String getName() throws RecordStoreNotOpenException {
		checkOpen();
		return null;
	}

	public int getVersion() throws RecordStoreNotOpenException {
		checkOpen();
		return 0;
	}

	public int getNumRecords() throws RecordStoreNotOpenException {
		checkOpen();
		return 0;
	}

	public int getSize() throws RecordStoreNotOpenException {
		checkOpen();
		return 0;
	}

	public int getSizeAvailable() throws RecordStoreNotOpenException {
		checkOpen();
		return 0;
	}

	public long getLastModified() throws RecordStoreNotOpenException {
		checkOpen();
		return 0;
	}


	public int addRecord(byte[] data, int offset, int numBytes)
     throws RecordStoreNotOpenException,
            RecordStoreException,
            RecordStoreFullException {
		return 0;
		
	}
	
	public void deleteRecord(int recordId)
	                  throws RecordStoreNotOpenException,
	                         InvalidRecordIDException,
	                         RecordStoreException {
	}
							 
    public int getRecord(int recordId, byte buffer[], int offset)
    	throws RecordStoreNotOpenException, InvalidRecordIDException, RecordStoreException {
		return 0;
    }


	public byte[] getRecord(int recordId) 
	throws RecordStoreNotOpenException, InvalidRecordIDException, RecordStoreException {
		return null;
	}
	
	
	public RecordEnumeration enumerateRecords(RecordFilter filter,
			RecordComparator comparator, boolean keepUpdated)
			throws RecordStoreNotOpenException {
		return null;
	}

	private void notifyRecordChangedListeners(int recordId) {
		for (int i = 0; i < recordListener.size(); i++) {
			RecordListener rl = (RecordListener) recordListener.elementAt(i);
			rl.recordChanged(this, recordId);
		}

	}

	private void notifyRecordAddedListeners(int recordId) {
		for (int i = 0; i < recordListener.size(); i++) {
			RecordListener rl = (RecordListener) recordListener.elementAt(i);
			rl.recordAdded(this, recordId);
		}

	}

	private void notifyRecordDeletedListeners(int recordId) {
		for (int i = 0; i < recordListener.size(); i++) {
			RecordListener rl = (RecordListener) recordListener.elementAt(i);
			rl.recordDeleted(this, recordId);
		}

	}

	static int getInt(byte data[], int offset) {
		int r = data[offset++];
		r = r << 8 | data[offset++] & 0xff;
		r = r << 8 | data[offset++] & 0xff;
		r = r << 8 | data[offset++] & 0xff;
		return r;
	}

	static long getLong(byte data[], int offset) {
		long r = data[offset++];
		r = r << 8 | (long) data[offset++] & 255L;
		r = r << 8 | (long) data[offset++] & 255L;
		r = r << 8 | (long) data[offset++] & 255L;
		r = r << 8 | (long) data[offset++] & 255L;
		r = r << 8 | (long) data[offset++] & 255L;
		r = r << 8 | (long) data[offset++] & 255L;
		r = r << 8 | (long) data[offset++] & 255L;
		return r;
	}

	static int putInt(int i, byte data[], int offset) {
		data[offset++] = (byte) (i >> 24 & 0xff);
		data[offset++] = (byte) (i >> 16 & 0xff);
		data[offset++] = (byte) (i >> 8 & 0xff);
		data[offset] = (byte) (i & 0xff);
		return 4;
	}

	static int putLong(long l, byte data[], int offset) {
		data[offset++] = (byte) (int) (l >> 56 & 255L);
		data[offset++] = (byte) (int) (l >> 48 & 255L);
		data[offset++] = (byte) (int) (l >> 40 & 255L);
		data[offset++] = (byte) (int) (l >> 32 & 255L);
		data[offset++] = (byte) (int) (l >> 24 & 255L);
		data[offset++] = (byte) (int) (l >> 16 & 255L);
		data[offset++] = (byte) (int) (l >> 8 & 255L);
		data[offset] = (byte) (int) (l & 255L);
		return 8;
	}

	private boolean checkOpen() {
		return true;
	}
	
	boolean isOpen() {
		return true;
	}
}
