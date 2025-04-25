GFC
===
(c) 2006-2025<br>
written by Gustaf Stechmann and Alex Knorr <br>
placed in the public domain.<br>

You have a game written against the Java ME / MIDP API?

With this wrapper library, you can make your app run on regular desktop Java SE!

Simply change your import statements from<br>
javax.microedition.* <br>
to<br>
gfc.microedition.* <br>
and you're ready to recompile.

You then need to configure the app.properties file with the specific parameters for your MIDlet,
and now you are ready to run.

Note: Only a subset of MIDP functionality is implemented -- in particular, that which is
relevant for games. We assume you want to just paint on a Canvas and not use the any
of the other UI elements. We may or may not add more funtionality in the future.

This code was written in Visual Studio Code with the Oracle Java extension.

Original MIDP documentation:
https://docs.oracle.com/javame/config/cldc/ref-impl/midp2.0/jsr118/index.html

Also thrown in are some utility classes (gfc.util)
and some stand-alone tools (gfc.tools) that you might find usefull.
