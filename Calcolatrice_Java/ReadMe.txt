Calculator (Java Swing)
La struttura del progetto segue un package layout standard per Java.
	Features:
		Keyboard input support (global bindings)
		Descent recursing Expression Parsing with parentheses
		Custom UI theme system
		Theme toggle live (Dark/Light)
		Error handling (popup windows and visual feedback)
	Tech:
		Java Swing
		Event-driven
		Custom expression parser



Sviluppatore Java – Progetto: Calcolatrice GUI Avanzata

Progettazione e sviluppo di una calcolatrice desktop utilizzando Java Swing con interfaccia grafica responsive e gestione globale della tastiera.
Implementazione di un motore di calcolo personalizzato in grado di gestire espressioni aritmetiche complesse, con supporto a:
Operatori base (+, -, *, /) e parentesi.
Numeri decimali e gestione della precedenza degli operatori (moltiplicazioni/divisioni prima di somme/differenze).
Moltiplicazione implicita e gestione di segni unari (+, -).
Implementazione di un sistema di gestione errori robusto con popup informativi per divisione per zero, errori di parentesi e formattazione numerica.
Realizzazione di un sistema di temi dinamici (dark/light mode) con applicazione coerente ai componenti GUI.
Ottimizzazione dell’interazione utente tramite InputMap e ActionMap per catturare input da tastiera in maniera globale, indipendentemente dal focus sul campo di testo.
Applicazione di principi di programmazione orientata agli oggetti e sviluppo modulare con classi separate per parsing, gestione errori e logica dell’interfaccia.
Conoscenze dimostrate in: Java SE, Swing, Event Handling, parsing ricorsivo, gestione di eccezioni, UX design minimale.

Sviluppatore Java – Calcolatrice GUI Avanzata
Progettata e implementata una calcolatrice desktop con Java Swing, supporto completo per espressioni aritmetiche complesse, parsing ricorsivo, gestione di errori e temi dark/light dinamici. Ottimizzata per input da tastiera globale, con design modulare e interfaccia intuitiva.

Descrizione breve
Calcolatrice GUI Avanzata

Calcolatrice desktop sviluppata in Java con interfaccia grafica Swing, progettata per supportare espressioni aritmetiche complesse e offrire un’esperienza utente fluida.

ReadME
Caratteristiche principali
Interfaccia grafica intuitiva con display e pulsanti chiari, layout responsive.
Supporto completo per espressioni aritmetiche, inclusi:
Operatori: +, -, *, /
Parentesi per la gestione della precedenza
Moltiplicazione implicita e segni unari (+, -)
Parsing ricorsivo personalizzato per calcoli affidabili e gestione della precedenza degli operatori.
Gestione errori robusta con messaggi popup per:
Divisione per zero
Errori di parentesi
Formattazione numerica non valida
Temi dinamici: possibilità di passare da modalità scura (dark) a chiara (light) in tempo reale.
Supporto completo alla tastiera, indipendentemente dal focus sul campo di testo, grazie a InputMap e ActionMap.
Visualizzazione pulita dei risultati, eliminando zeri superflui nei decimali.
Come usare
Compilare il progetto con Java 17+ o versione compatibile.
Avviare Main.java per eseguire l’applicazione.
Inserire numeri e operatori tramite mouse o tastiera.
Premere = o Enter per calcolare il risultato.
Usare C per cancellare, ← o Backspace per cancellare l’ultimo carattere.
Cambiare tema con il pulsante 🌙 / 🌞 accanto al display.
Struttura del progetto
CalcolatriceGUI.java – interfaccia grafica e gestione input.
MathEngine.java – punto di accesso per il calcolo delle espressioni.
ExpressionParser.java – parser ricorsivo per le espressioni matematiche.
StringParser.java – classe base per il parsing dei caratteri.
MyErrorManager.java – gestione e visualizzazione errori.
Theme.java e UIElement.java – gestione dei temi dark/light.
Main.java – entry point dell’applicazione.
Requisiti
Java SE 17+
Nessuna libreria esterna necessaria



GitHub
Calcolatrice GUI Avanzata 🖥️

Calcolatrice desktop sviluppata in Java con interfaccia grafica Swing, progettata per espressioni aritmetiche complesse e un’esperienza utente fluida e moderna.


Esempio di interfaccia grafica con tema dark.

🚀 Caratteristiche principali
Interfaccia grafica intuitiva con display e pulsanti chiari.
Supporto completo per espressioni matematiche:
Operatori: +, -, *, /
Parentesi per la gestione della precedenza
Moltiplicazione implicita e segni unari (+, -)
Parsing ricorsivo personalizzato per calcoli precisi e gestione della precedenza degli operatori.
Gestione errori avanzata con popup per:
Divisione per zero
Errori di parentesi
Formattazione numerica non valida
Temi dinamici: dark/light mode con cambio istantaneo.
Supporto completo alla tastiera (input globale indipendente dal focus).
Risultati formattati eliminando zeri superflui dopo il punto decimale.
⚡ Come usare
Compilare il progetto con Java 17+.
Eseguire Main.java.
Inserire numeri e operatori tramite mouse o tastiera.
Premere = o Enter per ottenere il risultato.
Usare C per cancellare tutto, ← o Backspace per cancellare l’ultimo carattere.
Cambiare tema con il pulsante 🌙 / 🌞 accanto al display.
Esempi di utilizzo
Input	Output
2+3*4	14
(5-2)*3	9
3//2	6 (moltiplicazione implicita X*(1/Y))
10/0	Error
🗂️ Struttura del progetto
CalcolatriceGUI.java – GUI e gestione input
MathEngine.java – punto di accesso al calcolo
ExpressionParser.java – parser ricorsivo delle espressioni
StringParser.java – classe base per parsing dei caratteri
MyErrorManager.java – gestione e popup degli errori
Theme.java e UIElement.java – gestione dei temi dark/light
Main.java – entry point dell’applicazione
✅ Requisiti
Java SE 17+
Nessuna libreria esterna necessaria


