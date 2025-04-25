GFC README
==========
G Stechmann 2013

You have a game or app written against the Java ME / MIDP API?

With the GFC library, you can make your app run on regular desktop Java SE.

Simply change the import statements to 
gfc.microedition.*
instead of
javax.microedition.*
and you're ready to recompile.

Note. Only a subset of MIDP functionality is supported.


original MIDP documentation:
https://docs.oracle.com/javame/config/cldc/ref-impl/midp2.0/jsr118/index.html