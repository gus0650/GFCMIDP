GFC
===
(c) 2006-2025<br>
written by Gustaf Stechmann and Alex Knorr <br>
placed in the public domain.<br>

You have a game written against the Java 2 Micro Edition / MIDP (J2ME)?

With this wrapper library, you can make your J2ME game run on regular desktop Java SE!

Simply change your import statements from<br>
javax.microedition.* <br>
to<br>
gfc.microedition.* <br>
and you're ready to recompile.

You then need to configure the app.properties file with the specific parameters for your MIDlet,
and now you are ready to run.

The wrapper re-implements most classes found in MIDP. The implementation is not complete, but should be sufficient for most games.

The Font class uses bitmap fonts. An example font is provided.

This code was written in Visual Studio Code with the Oracle Java extension.
Load "TestMIDlet" and in VSCode it should run out of the box. (F5)

Original MIDP documentation:
https://docs.oracle.com/javame/config/cldc/ref-impl/midp2.0/jsr118/index.html

Also included for your enjoyment are
gfc.util - utility classes
gfc.tools - and some stand-alone tools
gfc.widgets - high level UI classes
gfc.graphics - animation, palette manipulation, tilemaps