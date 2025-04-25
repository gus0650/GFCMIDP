// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RecordComparator.java

package gfc.microedition.rms;


public interface RecordComparator
{

    public abstract int compare(byte abyte0[], byte abyte1[]);

    public static final int EQUIVALENT = 0;
    public static final int FOLLOWS = 1;
    public static final int PRECEDES = -1;

}
