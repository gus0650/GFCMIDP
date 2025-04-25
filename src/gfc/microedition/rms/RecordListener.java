// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RecordListener.java

package gfc.microedition.rms;


// Referenced classes of package javax.microedition.rms:
//            RecordStore

public interface RecordListener
{

    public abstract void recordAdded(RecordStore recordstore, int i);

    public abstract void recordChanged(RecordStore recordstore, int i);

    public abstract void recordDeleted(RecordStore recordstore, int i);
}
