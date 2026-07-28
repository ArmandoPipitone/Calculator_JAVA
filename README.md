# 🧮 Calculator_JAVA (Java Swing)

Applicazione desktop per una calcolatrice matematica sviluppata in **Java 17+** con interfaccia grafica **Swing**.
Il progetto include un Parser a Discesa Ricorsiva (**Recursive Descent Parser**) scritto da zero per la valutazione immediata di espressioni matematiche complesse, supporto input da tastiera, moltiplicazione implicita e passaggio dinamico tra temi (Dark / Light).
Durante lo sviluppo si è considerata la possibilità di espandere la calcolatrice con nuove funzioni e relativi pulsanti, nonché dei temi, in modo semplice.

---

## ✨ Caratteristiche Principali

* **Recursive Descent Math Engine:** Valutazione di espressioni matematiche tramite analisi sintattica basata su grammatica formale (in modo da avere una gestione nativa della precedenza degli operatori, parentesi annidate e numeri decimali).
* **Moltiplicazione Implicita:** Riconoscimento automatico del prodotto sottinteso, ad esempio `5(2+3)` o `2.5(4)`.
* **Global Key Bindings:** Mappatura dell'input da tastiera tramite `InputMap` e `ActionMap` a livello di `JRootPane`, garantendo la cattura dei tasti indipendentemente dal focus dei componenti.
* **Tema Dinamico Dark/Light:** Cambio di tema istantaneo in runtime con aggiornamento dell'albero dei componenti (`SwingUtilities.updateComponentTreeUI`).
* **Gestione Centralizzata degli Errori:** Sistema di diagnostica personalizzato (`MyErrorManager`) per la gestione e segnalazione puntuale degli errori (divisione per zero, parentesi non bilanciate, sintassi o numeri non validi).
* **UI Clean & Responsive:** Formattazione intelligente dei risultati (azzeramento dei decimali superflui) e layout intuitivo.

---

## 📐 Architettura del Parser (Grammatica LL)

Il motore matematico `MathEngine` delega l'analisi alla classe `ExpressionParser` (estensione di `StringParser`), che valuta la stringa in un singolo passaggio senza librerie esterne. L'analisi rispetta la seguente grammatica formale:

```text
expression = term (('+' | '-') term)*
term       = factor (('*' | '/') factor)*
factor     = ('+' | '-') factor | '(' expression ')' | number
```

## 🛠️ Tech Stack & Requisiti

* **Linguaggio:** Java SE 17+
* **GUI Framework:** Java Swing (Event-Driven Architecture)
* **Dipendenze:** Nessuna libreria esterna (100% Native Java)

---

## 🗂️ Struttura del Progetto

Il progetto segue una struttura modulare e orientata agli oggetti con separazione delle responsabilità:

```text
Calcolatrice_Java/
│
├── Main.java                      # Entry point principale con inizializzazione sull'Event Dispatch Thread (EDT)
└── src/
    └── calculator/
        ├── CalcolatriceGUI.java   # Interfaccia GUI Swing, Key Bindings e gestione Temi (Theme, UIElement)
        ├── StringParser.java      # Classe base astratta per l'analisi del flusso di caratteri
        ├── ExpressionParser.java  # Implementazione del Parser a Discesa Ricorsiva
        ├── MathEngine.java        # Interfaccia pubblica per l'avvio della valutazione dell'espressione
        └── MyErrorManager.java    # Gestore e visualizzatore di popup di errore (JOptionPane)
```
