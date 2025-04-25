// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InvalidRecordIDException.java

package gfc.microedition.rms;


// Referenced classes of package javax.microedition.rms:
//            RecordStoreException

public class InvalidRecordIDException extends RecordStoreException
{

    public InvalidRecordIDException(String message)
    {
        super(message);
    }

    public InvalidRecordIDException()
    {
    }
}
