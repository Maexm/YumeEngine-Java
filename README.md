Installationsanleitung

1. ZIP entpacken (YUMEFiles ZIP ist nicht im Repo eingechecked, deshalb bitte beim Autor anfragen!)
2. `mvn install` ausführen
2. `java -jar .\target\SuperGame-0.0.1-SNAPSHOT-jar-with-dependencies.jar` ausführen

Hinweise:
- Das Spiel ist evt. etwas laut. Pass mit der Laustärke auf!
- Das Spiel lässt sich über die ESC-Taste jederzeit beenden
- Benutz Laufzeit -> BEENDEN bitte nur dann, wenn ESC nicht funktioniert. Ansonsten lässt sich das Spiel notfalls über den Taskmanager schließen.

Folgende Bugs sind mir bereits bekannt, melde sie mir bitte nicht:

- Das Spiel lässt sich im StartScreen ganz am Anfang nicht beenden.
- Beim Verlassen der größeren Strand-Map, wird in der Konsole eine nullpointer-exception angezeigt. Das Spiel läuft dennoch ungestört weiter.