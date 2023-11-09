# Faktoren der 12-Faktor-App

In diesem Dokument werden die acht der zwölf Faktoren der 12-Faktor-App-Methode erläutert, die in unserem Microservice-Projekt implementiert wurden.

## I. Codebase
Eine Codebase mit Versionsverfolgung, mehrere Deployments möglich.
- Unser Projekt verwendet Git für die Versionsverfolgung und ist so strukturiert, dass es leicht über verschiedene Stages (Development, Staging, Production) hinweg deployt werden kann.

## II. Abhängigkeiten
Explizit deklarieren und isolieren von Abhängigkeiten.
- Die Abhängigkeiten werden in der `pom.xml`-Datei (für Maven) explizit deklariert und durch Maven verwaltet, sodass keine impliziten Abhängigkeiten erforderlich sind.

## III. Konfiguration
Speichere Konfiguration in der Umgebung.
- Konfigurationen werden außerhalb des Codes in Umgebungsvariablen gespeichert, die über `application.properties` und Docker-Configs eingelesen werden.

## IV. Unterstützende Dienste
Behandle unterstützende Dienste als angebundene Ressourcen.
- Die PostgreSQL-Datenbank wird als angebundener Service behandelt und über Umgebungsvariablen in der Docker-Konfiguration spezifiziert.

## V. Build, release, run
Strenge Trennung zwischen Build- und Laufzeit.
- Maven wird verwendet, um das Build zu erstellen, während Docker für den Release- und Run-Prozess genutzt wird, was eine klare Trennung ermöglicht.

## VI. Prozesse
Führe die App als einen oder mehrere stateless Prozesse aus.
- Die Spring Boot-Anwendung ist stateless gestaltet und speichert keine Benutzerdaten zwischen Sitzungen.

## VII. Portbindung
Exportiere Dienste über Portbindung.
- Die Anwendung ist so konfiguriert, dass sie über den Port 8080 auf Anfragen hört, wodurch sie als eigenständiger Dienst fungiert.

## VIII. Nebenläufigkeit
Skaliere die App horizontal über den Prozessmodell.
- Durch den Einsatz von Docker kann die Anwendung horizontal skaliert werden, indem mehrere Container-Instanzen betrieben werden.

## X. Dev/prod-Parität
Halte Entwicklung und Produktion möglichst ähnlich.
- Die Nutzung von Docker gewährleistet, dass die Entwicklungsumgebung der Produktionsumgebung so ähnlich wie möglich ist.

## XI. Logs
Behandle Logs als Ereignisströme.
- Logs werden durch die Konfiguration von Spring Boot und Logback als Ereignisströme behandelt und können an einen Log-Aggregator gesendet werden.

## XII. Admin-Prozesse
Admin-/Managementaufgaben als einmalige Prozesse.
- Administrative Aufgaben können als einmalige Prozesse innerhalb des Docker-Containers ausgeführt werden.

## Nicht umgesetzte Faktoren:
Folgende Faktoren wurden nicht explizit umgesetzt oder hier nicht dokumentiert:

## IX. Einwegigkeit
- Da es keine Informationen über die Verwendung von Einweg-Datenstores oder ähnlichen Komponenten gibt, wird dieser Faktor als nicht umgesetzt betrachtet.

Es sollte angemerkt werden, dass die Implementierung der nicht umgesetzten Faktoren von den spezifischen Anforderungen des Projekts abhängen würde und im Kontext des Projektumfangs und der -ziele betrachtet werden sollte.
