// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RecordEnumeration.java

package gfc.microedition.rms;


// Referenced classes of package javax.microedition.rms:
//            InvalidRecordIDException, RecordStoreNotOpenException, RecordStoreException

public interface RecordEnumeration
{

    public abstract int numRecords();

    public abstract byte[] nextRecord()
        throws InvalidRecordIDException, RecordStoreNotOpenException, RecordStoreException;

    public abstract int nextRecordId()
        throws InvalidRecordIDException;

    public abstract byte[] previousRecord()
        throws InvalidRecordIDException, RecordStoreNotOpenException, RecordStoreException;

    public abstract int previousRecordId()
        throws InvalidRecordIDException;

    public abstract boolean hasNextElement();

    public abstract boolean hasPreviousElement();

    public abstract void reset();

    public abstract void rebuild();

    public abstract void keepUpdated(boolean flag);

    public abstract boolean isKeptUpdated();

    public abstract void destroy();
}
