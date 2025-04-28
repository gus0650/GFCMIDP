GFC MIDP Wrapper
================
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

The wrapper re-implements most classes found in MIDP. The implementation is not 100% complete, but should be sufficient for most games.

The Font class uses bitmap fonts.

Fullscreen mode is supported.

Gamepad controls will be supported (but are not yet).

Alt-F4 closes the window.

This code was written in Visual Studio Code with the Oracle Java extension.
Load "TestMIDlet" and it should run out of the box. (F5)

Original MIDP documentation:
https://docs.oracle.com/javame/config/cldc/ref-impl/midp2.0/jsr118/index.html

This software is 90% complete -- see TODO file for what is mising.