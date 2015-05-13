In diesem Video möchte ich zeigen, wie wir aus einem
JSP File auf das HttpSession-Object zugreifen können und
uns die Daten, z.B. einer Liste aus Strings in das
JSP holen, darüber iterieren und die Liste ausgeben lassen.

Los geht's wieder mit einem Servlet, das die Post und Get
Anfragen der Clients entgegen nimmt und die eingegeben
Zutaten für unser Rezept in einer Liste für uns
organisiert.


Damit sind wir durch mit dieser Lektion, wir haben ein Servlet geschrieben, das doGet() und doPost()
Methoden implementiert, werden diese aufgerufen, reichen diese den Aufruf an processRequest() weiter.
In processRequest() wird vom httpSession-Object die Zutatenliste abgerufen, exisitiert diese noch nicht,
wird sie initialisiert und dann mit der eingegeben Zutat befüllt. Existiert sie schon, dann wird 
einfach nur die weitere Zutat angefügt.

Dieses Session-Object brauchen wir, da wir unsere Übertragung auf HTTP aufsetzen und HTTP ein zustandloses
Protokoll ist, d.h. ohne die Sitzungsverwaltung würde der Server z.B. die Zutat "300g weiche Butter" auf die
Liste schreiben, wenn wir dann z.B. 1/2 Backpulver hinzufügen wollten, würde der Server glauben, wir sind
ein neuer Client und erzeugt eine neue Liste und schreibt hier das 1/2 Backpulver drauf usw.

Durch die httpSession umgehen wir das und können uns über eine Sitzung hinweg (Browsersitzung, bis der
Browser geschlossen wird) also die verschiedenen Zutaten merken und auf unserer JSP Datei anzeigen lassen.

Code landet wie üblich auf GitHub, Link zum Repository ist in der Videobeschreibung zu finden.

Viel Spaß und vielen Dank für's anschauen!
