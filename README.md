# 🧮 Calculator_JAVA (Java Swing)

Una calcolatrice desktop sviluppata in Java con interfaccia grafica Swing.
Il progetto include un **motore di parsing ricorsivo personalizzato** per la gestione di espressioni matematiche complesse, supporto completo alla tastiera globale e un sistema di temi dinamici (Dark/Light).

---

## ✨ Caratteristiche Principali

* **Parsing Ricorsivo Personalizzato:** Gestione avanzata di espressioni matematiche complesse con precedenza degli operatori (`*`, `/`, `+`, `-`), parentesi, moltiplicazione implicita e segni unari.
* **Input Globale da Tastiera:** Mappatura completa degli input tramite `InputMap` e `ActionMap`, permettendo l'uso della tastiera indipendentemente dal focus sul campo di testo.
* **Temi Dinamici (Dark/Light):** Sistema di tematizzazione live per cambiare stile visivo all'interfaccia in tempo reale con un click.
* **Gestione Errori Robusta:** Sistema dedicato con popup informativi e feedback visivo per divisione per zero, errori nelle parentesi e formattazione numerica non valida.
* **UI Clean & Responsive:** Formattazione intelligente dei risultati (azzeramento dei decimali superflui) e layout intuitivo.

---

## 🛠️ Tech Stack & Requisiti

* **Linguaggio:** Java SE 17+
* **GUI Framework:** Java Swing (Event-Driven Architecture)
* **Dipendenze:** Nessuna libreria esterna (100% Native Java)

---

## 🗂️ Struttura del Progetto

Il progetto segue una struttura modulare e orientata agli oggetti con separazione delle responsabilità:

```text
├── Main.java              # Entry point dell'applicazione
├── | src/calculator/
    ├── CalcolatriceGUI.java   # Interfaccia grafica (Swing) e gestione eventi
    ├── MathEngine.java        # Entry point/Facade per la logica di calcolo
    ├── ExpressionParser.java  # Parser ricorsivo a discesa per le espressioni
    ├── StringParser.java      # Base parser per l'analisi dei caratteri
    ├── MyErrorManager.java    # Gestione centralizzata e dialoghi di errore
    ├── Theme.java             # Gestione dei colori e dei temi (Dark/Light)
    └── UIElement.java         # Componente per l'applicazione dinamica del tema
