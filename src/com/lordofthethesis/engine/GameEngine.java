package com.lordofthethesis.engine;

import com.lordofthethesis.model.*;
import com.lordofthethesis.audio.AudioManager;
import java.util.*;

/**
 * Motore principale del gioco - gestisce la logica e i comandi
 * MODALITÀ NARRATIVA LOTR: progressione lineare tramite enigmi
 */
public class GameEngine {
    private Player player;
    private Room startRoom;
    private Map<String, Room> allRooms;
    private boolean gameRunning;
    private boolean gameWon;
    private List<String> gameLog;
    // Modalità narrativa LOTR (progressione lineare con enigmi)
    private boolean narrativeMode;
    private List<Level> storyChapters; // Capitoli della storia con enigmi
    private int currentChapter;
    private boolean currentChapterCompleted; // Se il capitolo corrente è stato risolto
    private boolean currentChapterStarted; // Se il capitolo corrente è già stato mostrato
    // Audio manager per musiche di sottofondo
    private AudioManager audioManager;
    // Intro cinematica
    private boolean inCinematicIntro;
    private int introStep;
    private String[] introTexts;
    // Paginazione testo lungo
    private String fullText; // Testo completo da mostrare
    private int textPage; // Pagina corrente (0, 1, 2...)
    private boolean hasMorePages; // Se ci sono altre pagine
    
    public GameEngine() {
        this.allRooms = new HashMap<>();
        this.gameLog = new ArrayList<>();
        this.gameRunning = false;
        this.gameWon = false;
        this.narrativeMode = true; // ATTIVA MODALITÀ NARRATIVA
        this.storyChapters = new ArrayList<>();
        this.currentChapter = 0;
        this.currentChapterCompleted = false;
        this.currentChapterStarted = false;
        this.audioManager = new AudioManager();
        this.inCinematicIntro = false;
        this.introStep = 0;
        this.fullText = "";
        this.textPage = 0;
        this.hasMorePages = false;
        
        // Testi dell'intro cinematica (come nel film!)
        this.introTexts = new String[]{
            "═══════════════════════════════════════════════════════════\n" +
            "           🌋 IL SIGNORE DEGLI ANELLI 🌋\n" +
            "              LA COMPAGNIA DELLA TESI\n" +
            "═══════════════════════════════════════════════════════════\n\n" +
            "Nelle fiamme del Monte Fato, il Signore Oscuro Sauron\n" +
            "forgiò in segreto una TESI SUPREMA per controllare\n" +
            "tutte le altre tesi della Terra di Mezzo.\n\n" +
            "💍 UNA TESI PER DOMINARLE TUTTE 💍\n" +
            "💍 UNA TESI PER TROVARLE 💍\n" +
            "💍 UNA TESI PER GHERMIRLE 💍\n" +
            "💍 E NEL BUIO INCATENARLE 💍\n\n" +
            "Questa Tesi ha il potere di dare la laurea con lode,\n" +
            "ma corrompe chiunque la possieda.\n\n" +
            "Deve essere distrutta. A Mordor, dove fu forgiata.\n\n" +
            "Premi INVIO per iniziare il viaggio..."
        };
    }
    
    public void initializeGame(String playerName) {
        player = new Player(playerName);
        createWorld();
        createStoryChapters(); // Crea i capitoli narrativi con enigmi
        player.setCurrentRoom(startRoom);
        gameRunning = true;
        
        // INIZIO NARRATIVA - LA FESTA DI BILBO
        addLog("═══════════════════════════════════════════════════");
        addLog("  🎂 LA FESTA DI BILBO - CONTEA 🎂");
        addLog("═══════════════════════════════════════════════════");
        addLog("");
        addLog("Sei alla festa del 111° compleanno di Bilbo Baggins!");
        addLog("Fuochi d'artificio di Gandalf illuminano il cielo.");
        addLog("Gli hobbit mangiano, bevono e festeggiano.");
        addLog("");
        addLog("Bilbo sale sul palco per un discorso...");
        addLog("");
        addLog("💍 'Cari amici, oggi compio 111 anni!'");
        addLog("'È il momento di partire. Lascio la Contea.'");
        addLog("'E lascio tutto a te, " + playerName + ".'");
        addLog("");
        addLog("Bilbo ti consegna una busta sigillata.");
        addLog("Dentro c'è la TESI - l'Anello Unico del Sapere!");
        addLog("");
        addLog("═══════════════════════════════════════════════════");
        addLog("");
        addLog("🧙‍♂️ GANDALF ti prende da parte...");
        addLog("");
        addLog("'Questa tesi è pericolosa, " + playerName + ".'");
        addLog("'È l'Unico Anello forgiato da Sauron!'");
        addLog("'Devi portarla a Gran Burrone, da Elrond.'");
        addLog("'Ma fai attenzione... gli Spettri dell'Anello ti cercano!'");
        addLog("");
        addLog("═══════════════════════════════════════════════════");
        addLog("");
        addLog("🎮 MODALITÀ NARRATIVA ATTIVA");
        addLog("Risolvi enigmi per avanzare nella storia!");
        addLog("");
    }

    private void createStoryChapters() {
        storyChapters.clear();
        
        // CAPITOLO 1: INTRO - SAURON E LA TESI
        Map<String, String> cap1Choices = new HashMap<>();
        cap1Choices.put("A", "2");
        cap1Choices.put("B", "5");
        cap1Choices.put("C", "6");
        storyChapters.add(new Level(
            "cap1_sauron",
            "Prologo - Sauron forgia la Tesi",
            "🔥 Nelle fiamme del Monte Fato, Sauron forgia la TESI UNICA! 💍 'UNA TESI PER DOMINARLE TUTTE' 💍. Questa tesi ha il potere di dare la laurea con lode... Ma corrompe chi la possiede! Deve essere distrutta dove fu creata: a Mordor.\n\n❓ Per iniziare il viaggio, quanto fa 1 + 1?",
            cap1Choices,
            "A",
            "La somma più semplice!"
        ));
        
        // CAPITOLO 2: LA CONTEA
        storyChapters.add(new Level(
            "cap2_contea",
            "La Contea - Casa tua",
            "🏡 Sei nella tranquilla Contea, tra verdi colline. Hai appena finito di scrivere la tua TESI. Ma c'è qualcosa di strano... sembra avere un potere oscuro. Il tuo relatore (un certo Gandalf) ti ha avvertito: 'Questa tesi è pericolosa! Devi portarla a Gran Burrone!'\n\n❓ Prima di partire: qual è il colore dell'erba?",
            Arrays.asList("verde", "green"),
            "Guarda fuori dalla finestra!"
        ));
        
        // CAPITOLO 3: BAG END - FESTA DI BILBO
        storyChapters.add(new Level(
            "cap3_bagend",
            "Bag End - La Festa di Bilbo",
            "🎂 Sei alla grande festa del 111° compleanno di Bilbo!\n\n" +
            "Fuochi d'artificio, cibo delizioso, hobbit che ballano.\n" +
            "Bilbo fa un discorso misterioso:\n\n" +
            "💍 'Lascio tutto a te, caro cugino! Prenditi cura della TESI. È più importante di quanto pensi. '\n" +
            "Bilbo scompare con un lampo! (Aveva l'Anello dell'Invisibilità)\n\n" +
            "Gandalf ti prende da parte:\n" +
            "'Devi partire STANOTTE. Gli Spettri ti cercano!'\n\n" +
            "❓ Quanti fuochi d'artificio ha lanciato Gandalf? (Suggerimento: tanto quanto 5+3)",
            Arrays.asList("8", "otto"),
            "Somma 5 e 3!"
        ));
        
        // CAPITOLO 4: GLI SPETTRI - INSEGUIMENTO
        storyChapters.add(new Level(
            "cap4_spettri",
            "La Strada - Inseguimento dei Nazgûl",
            "🌙 Stai fuggendo nella notte!\n\n" +
            "Improvvisamente senti un urlo terrificante:\n" +
            "🧛 'BAGGINSSSS... L'ANELLO... DOVE SEI?'\n\n" +
            "Sono i NAZGÛL - i 9 Spettri dell'Anello!\n" +
            "Cavalieri Neri al servizio di Sauron!\n\n" +
            "Ti nascondi dietro un albero, tremando.\n" +
            "Uno Spettro si avvicina... fiuta l'aria...\n\n" +
            "Il tuo cuore batte forte!\n\n" +
            "❓ Per rimanere calmo, conta: quante zampe hanno 2 cavalli?",
            Arrays.asList("8", "otto"),
            "Ogni cavallo ha 4 zampe..."
        ));
        
        // CAPITOLO 5: GRANPASSO - IL RELATORE
        storyChapters.add(new Level(
            "cap5_granpasso",
            "Locanda del Puledro Impennato - Incontro con il Relatore",
            "🏨 Arrivi alla locanda di Brea, esausto.\n\n" +
            "Un uomo incappucciato nell'angolo ti osserva.\n" +
            "Si alza e si avvicina:\n\n" +
            "⚔️ 'Mi chiamano Granpasso. Sono qui per aiutarti.'\n" +
            "'Sono il tuo RELATORE per questa tesi pericolosa.'\n\n" +
            "'Gli Spettri ti hanno quasi preso stanotte.'\n" +
            "'Ma io conosco sentieri nascosti per arrivare a Gran Burrone.'\n\n" +
            "Gandalf gli ha chiesto di proteggerti!\n\n" +
            "Granpasso (Aragorn) sarà la tua guida!\n\n" +
            "❓ 'Prima di partire,' dice Granpasso, 'rispondi: quanto fa 10 - 3?'",
            Arrays.asList("7", "sette"),
            "Sottrazione semplice!"
        ));
        
        // CAPITOLO 6: RIVENDELL - ARRIVO
        storyChapters.add(new Level(
            "cap6_rivendell",
            "Rivendell - Casa di Elrond",
            "�️ Dopo giorni di viaggio, arrivi a Rivendell!\n\n" +
            "La casa di Elrond, signore degli Elfi.\n" +
            "Architettura elfica maestosa, cascate, pace.\n\n" +
            "Elrond ti accoglie:\n" +
            "👑 'Benvenuto, Portatore della Tesi!'\n" +
            "'Qui sei al sicuro. Riposa.'\n\n" +
            "Gli Elfi ti curano dalle ferite del viaggio.\n" +
            "Granpasso è orgoglioso di te:\n" +
            "'Sei arrivato fin qui. Sei forte!'\n\n" +
            "❓ Elrond ti chiede: 'Quale lettera viene DOPO la B nell'alfabeto?'",
            Arrays.asList("c", "C", "ci"),
            "A, B, C..."
        ));
        
        // CAPITOLO 7: IL CONCILIO - DECISIONE
        storyChapters.add(new Level(
            "cap7_concilio",
            "Il Gran Concilio di Elrond",
            "�️ Il Concilio è riunito nella grande sala!\n\n" +
            "Presenti:\n" +
            "- 🧙‍♂️ Gandalf (il tuo mentore)\n" +
            "- 👑 Elrond (signore di Rivendell)\n" +
            "- ⚔️ Aragorn/Granpasso (il tuo relatore)\n" +
            "- 🧝 Legolas (elfo arciere)\n" +
            "- 🪓 Gimli (nano guerriero)\n" +
            "- 🛡️ Boromir (guerriero di Gondor)\n\n" +
            "Elrond: 'La Tesi deve essere distrutta a Mordor!'\n" +
            "Boromir: 'Usiamola come arma!'\n" +
            "Gandalf: 'NO! Corrompe chiunque!'\n\n" +
            "Tu ti alzi: 'La porterò io a Mordor.'\n" +
            "Aragorn: 'Hai il mio giuramento, ti proteggerò.'\n\n" +
            "🎯 LA COMPAGNIA DELL'ANELLO È FORMATA!\n\n" +
            "❓ Elrond chiede: 'Quanti membri ha la Compagnia?' (Tu + 8 compagni)",
            Arrays.asList("9", "nove"),
            "Conta tutti: tu + Gandalf + Aragorn + Legolas + Gimli + Boromir + 3 hobbit"
        ));
        
        // CAPITOLO 8: LE PORTE DI DURIN - MORIA
        storyChapters.add(new Level(
            "cap8_moria",
            "Le Porte di Durin",
            "⛰️🚪 La Compagnia arriva alle Porte di Durin!\n\n" +
            "Siete davanti a un'enorme parete di roccia.\n" +
            "Gandalf accende il suo bastone...\n\n" +
            "E sulla roccia appaiono simboli elfici luminosi!\n" +
            "✨ Le Porte di Durin, Signore di Moria.\n" +
            "   Parla, amico, ed entra. ✨\n\n" +
            "Gandalf: 'È un indovinello! La risposta apre la porta.'\n\n" +
            "Boromir tenta di forzare la porta: nulla.\n" +
            "Gimli prova con la sua ascia: nulla.\n\n" +
            "Gandalf riflette ad alta voce:\n" +
            "'Parla, amico, ed entra...'\n" +
            "'La risposta è nell'indovinello stesso!'\n\n" +
            "❓ Quale parola in elfico significa 'amico'?",
            Arrays.asList("mellon", "Mellon", "MELLON"),
            "La parola elfica per 'amico' è scritta nell'indovinello!"
        ));
        
        // CAPITOLO 9: GLI ARGONATH - I PILASTRI DEI RE
        storyChapters.add(new Level(
            "cap9_argonath",
            "Gli Argonath - Pilastri dei Re",
            "🗿 La Compagnia naviga sul Grande Fiume!\n\n" +
            "Improvvisamente, davanti a voi appaiono due statue ENORMI!\n" +
            "⛰️ GLI ARGONATH - I PILASTRI DEI RE! ⛰️\n\n" +
            "Due antichi re di Gondor scolpiti nella roccia.\n" +
            "Alti come montagne, maestosi, imponenti.\n\n" +
            "Aragorn si alza in piedi nella barca:\n" +
            "'Ecco i miei antenati. Un giorno riprenderò il trono.'\n\n" +
            "Gandalf: 'Stiamo entrando nelle terre pericolose.\n" +
            "Presto dovremo decidere: andare a est o ovest.'\n\n" +
            "Boromir guarda la tua TESI con desiderio...\n" +
            "La Compagnia inizia a mostrare tensione.\n\n" +
            "❓ Per mantenere l'unità: quante statue compongono gli Argonath?",
            Arrays.asList("2", "due"),
            "Conta i Pilastri dei Re!"
        ));
        
        // CAPITOLO 10: MORIA - IL BALROG
        storyChapters.add(new Level(
            "cap10_balrog",
            "Moria - La Caduta di Gandalf",
            "⛏️ La Compagnia attraversa le Miniere di Moria!\n\n" +
            "Tunnel oscuri, echi sinistri, scheletri di nani ovunque.\n" +
            "Gimli piange: 'Balin... il mio cugino è morto qui...'\n\n" +
            "Improvvisamente: FUOCO E OMBRA!\n" +
            "🔥👹 UN BALROG! UN DEMONE DELL'ANTICO MONDO! 👹🔥\n\n" +
            "Gandalf: 'CORRETE, SCIOCCHI!'\n\n" +
            "Correte verso il Ponte di Khazad-dûm!\n" +
            "Gandalf si volta: 'NON PUOI PASSARE!'\n\n" +
            "⚔️ Gandalf combatte il Balrog sul ponte!\n" +
            "Il ponte crolla... il Balrog cade negli abissi!\n\n" +
            "Ma la sua frusta afferra Gandalf! 😱\n" +
            "Gandalf: 'Fuggite, stolti!' e si lascia cadere.\n\n" +
            "💔 GANDALF È CADUTO!\n\n" +
            "❓ Nel dolore, ricordi: quanto fa 9 - 1?",
            Arrays.asList("8", "otto"),
            "Eravate 9, ora siete..."
        ));
        
        // CAPITOLO 11: AMON HEN - LA DIVISIONE
        storyChapters.add(new Level(
            "cap11_divisione",
            "Amon Hen - La Compagnia si Divide",
            "🌳 Dopo la perdita di Gandalf, arrivate ad Amon Hen.\n\n" +
            "La Compagnia è distrutta dal dolore.\n" +
            "Boromir ti prende da parte:\n\n" +
            "'Dammi la TESI! Possiamo usarla per vincere la guerra!'\n" +
            "'Gondor ha bisogno di potere! Dammela!'\n\n" +
            "Tu rifiuti. Boromir impazzisce e ti attacca!\n" +
            "Ma poi si ferma: 'Cosa sto facendo? Perdonami!'\n\n" +
            "🏹 Improvviso attacco di Orchi!\n" +
            "Boromir si sacrifica per proteggere i tuoi amici hobbit!\n\n" +
            "Aragorn: 'La Compagnia si è spezzata.'\n" +
            "'Ma tu devi continuare verso Mordor!'\n\n" +
            "Tu decidi: andrò da solo a Mordor.\n" +
            "Ma Sam ti segue: 'Non ti lascio andare da solo!'\n\n" +
            "💪 TU E SAM VERSO MORDOR! LA COMPAGNIA SI DIVIDE!\n\n" +
            "❓ Quanti hobbit vanno verso Mordor?",
            Arrays.asList("2", "due"),
            "Tu e Sam!"
        ));
        
        // CAPITOLO 12: VIAGGIO CON SAM
        storyChapters.add(new Level(
            "cap12_sam",
            "Il Viaggio con Sam",
            "⛰️ Tu e Sam camminate verso Mordor.\n\n" +
            "Giorni e giorni di marcia solitaria.\n" +
            "La TESI pesa sempre di più, fisicamente e mentalmente.\n\n" +
            "Sam ti incoraggia:\n" +
            "'Andrò con te fino alla fine, fino alla distruzione della Tesi!'\n\n" +
            "Vi nutrite di Lembas (pane elfico).\n" +
            "La Torre di Sauron si avvicina all'orizzonte.\n\n" +
            "Sam nota che sei cambiato:\n" +
            "'La Tesi ti sta consumando...'\n\n" +
            "Ma vai avanti. Per la Contea. Per i tuoi amici.\n\n" +
            "❓ Per mantenerti concentrato: quanto fa 5 + 5?",
            Arrays.asList("10", "dieci"),
            "Somma!"
        ));
        
        // CAPITOLO 13: ALLE PORTE DI MORDOR
        storyChapters.add(new Level(
            "cap13_mordor",
            "Mordor - Il Cancello Nero",
            "🌋 Eccoci. MORDOR.\n\n" +
            "Il cielo è rosso fuoco. L'aria è irrespirabile.\n" +
            "La Torre di Sauron si erge minacciosa.\n" +
            "L'Occhio ti cerca...\n\n" +
            "🦅 Improvvisamente: le AQUILE!\n" +
            "Gandalf è tornato! È rinato come Gandalf il Bianco!\n\n" +
            "Gandalf: 'La battaglia finale è iniziata!'\n" +
            "'Io e Aragorn attaccheremo il Cancello Nero!'\n" +
            "'Tu entra in Mordor mentre siamo distratti!'\n\n" +
            "🗡️ La battaglia ha inizio!\n" +
            "Mentre i tuoi amici combattono, tu e Sam correte verso il Monte Fato.\n\n" +
            "❓ Per trovare la forza: quanto fa 7 + 7?",
            Arrays.asList("14", "quattordici"),
            "Ultimo sforzo!"
        ));
        
        // CAPITOLO 14: MONTE FATO - INTERNO
        storyChapters.add(new Level(
            "cap14_montefato",
            "Monte Fato - La Scalata",
            "🌋 STAI SCALANDO IL MONTE FATO!\n\n" +
            "Lava scorre ai tuoi piedi. Fumo e cenere ovunque.\n" +
            "Il calore è insopportabile.\n\n" +
            "Sam: 'Forza! Ci siamo quasi!'\n\n" +
            "Ma la TESI pesa troppo...\n" +
            "Le gambe cedono... non ce la fai più...\n\n" +
            "Sam: 'NON POSSO PORTARE LA TESI PER TE...'\n" +
            "'MA POSSO PORTARE TE! FORZA!'\n\n" +
            "🥺 Sam ti prende sulle spalle!\n" +
            "💪 Con l'ultimo sforzo, arrivate alla Voragine del Fato!\n\n" +
            "❓ Chi è il vero eroe? Scrivi il suo nome:",
            Arrays.asList("sam", "Sam", "SAM", "samvise"),
            "Il giardiniere fedele!"
        ));
        
        // CAPITOLO 15: LA DISTRUZIONE - FINALE
        storyChapters.add(new Level(
            "cap15_finale",
            "Monte Fato - La Distruzione della Tesi",
            "🌋🔥 SEI AL MONTE FATO! 🔥🌋\n\n" +
            "Hai scalato la montagna fumante.\n" +
            "Lava ovunque. Calore insopportabile.\n\n" +
            "Davanti a te: la Voragine del Fato.\n" +
            "Dove la Tesi deve essere distrutta!\n\n" +
            "👁️ L'OCCHIO DI SAURON TI VEDE!\n" +
            "'NOOO! L'ANELLO È MIO!' urla nella tua mente.\n\n" +
            "Ma resisti! Pensi ad Aragorn, a Gandalf, alla Compagnia.\n" +
            "Pensi alla tua LAUREA!\n\n" +
            "🔥 LANCI LA TESI NELLA LAVA! 🔥\n\n" +
            "La Tesi brucia! L'Occhio di Sauron urla!\n" +
            "La Torre Oscura crolla! MORDOR STA CROLLANDO!\n\n" +
            "Sam: 'È finita! CE L'ABBIAMO FATTA!'\n\n" +
            "Ma il Monte Fato sta esplodendo intorno a voi...\n" +
            "Non c'è via d'uscita...\n\n" +
            "❓ Per completare il viaggio, scrivi: FINE",
            Arrays.asList("fine", "FINE", "Fine"),
            "Scrivi la parola 'fine'!"
        ));
        
        // CAPITOLO 16: LE AQUILE - IL SALVATAGGIO
        storyChapters.add(new Level(
            "cap16_aquile",
            "Il Salvataggio delle Aquile",
            "🦅🦅🦅 LE AQUILE! LE AQUILE STANNO ARRIVANDO! 🦅🦅🦅\n\n" +
            "Mentre tutto crolla, senti un grido nel cielo!\n\n" +
            "GWAIHIR, il Signore delle Aquile, scende in picchiata!\n" +
            "Ti afferra con Sam e vi porta via dal Monte Fato!\n\n" +
            "Volate sopra Mordor che crolla.\n" +
            "La Torre di Sauron cade in polvere.\n" +
            "L'Occhio si spegne per sempre.\n\n" +
            "Gandalf (su un'altra aquila): 'BEN FATTO, PORTATORE DELLA TESI!'\n\n" +
            "Le Aquile vi portano a Gran Burrone.\n" +
            "Sei salvo. La missione è compiuta.\n\n" +
            "❓ Quante aquile ti hanno salvato? (Tu, Sam, Gandalf = 3 aquile)",
            Arrays.asList("3", "tre"),
            "Conta le aquile che portano eroi!"
        ));
        
        // CAPITOLO 17: LA LAUREA - EPILOGO
        storyChapters.add(new Level(
            "cap17_laurea",
            "La Seduta di Laurea",
            "🎓👑 SEI TORNATO A GRAN BURRONE! 🎓👑\n\n" +
            "Tutti i tuoi amici ti aspettano:\n" +
            "- 🧙‍♂️ Gandalf il Bianco (il tuo mentore)\n" +
            "- 👑 Aragorn, ora RE di Gondor (il tuo relatore)\n" +
            "- 🧝 Legolas (il correlatore elfico)\n" +
            "- 🪓 Gimli (il correlatore nano)\n" +
            "- 🌿 Sam (il tuo migliore amico)\n\n" +
            "Elrond: 'Oggi è un giorno glorioso!'\n" +
            "'Il Portatore della Tesi ha compiuto l'impossibile!'\n\n" +
            "Aragorn ti mette una corona sul capo:\n" +
            "'Per il tuo coraggio e sacrificio...'\n" +
            "'TI PROCLAMO DOTTORE DELLA TERRA DI MEZZO!'\n\n" +
            "🎊🎉 HAI VINTO! SEI LAUREATO CON LODE! 🎉🎊\n\n" +
            "Sam sorride: 'Sapevo che ce l'avresti fatta!'\n\n" +
            "❓ Sei pronto per tornare alla Contea? Scrivi: CASA",
            Arrays.asList("casa", "CASA", "Casa"),
            "Torna a casa, eroe!"
        ));
        
        currentChapter = 0;
    }
    
    private void createWorld() {
        // Creazione delle stanze - LUOGHI DEL SIGNORE DEGLI ANELLI
        Room contea = new Room("La Contea", 
            "Sei nella tranquilla Contea, tra verdi colline e case hobbit.\n" +
            "Hai appena finito di scrivere la tua TESI, l'Unico Anello del Sapere.\n" +
            "Devi portarla a Mordor per lanciarla nelle fiamme del Monte Fato!");
        
        Room granBurrone = new Room("Gran Burrone",
            "La casa di Elrond, signore degli Elfi. Luogo di saggezza e consiglio.\n" +
            "Qui si è tenuto il Concilio per decidere il destino della TESI.\n" +
            "Chi avrà il coraggio di portarla a Mordor?");
        
        Room minieraMoria = new Room("Miniere di Moria",
            "Antiche miniere dei Nani, ora oscure e pericolose.\n" +
            "Echi di picconi risuonano nelle profondità. Senti una presenza oscura...\n" +
            "Un cartello dice: 'NON SVEGLIARE IL BALROG (professore arrabbiato)'");
        
        Room lothlorien = new Room("Lothlórien",
            "La foresta dorata degli Elfi. Pace e bellezza senza tempo.\n" +
            "La Dama Galadriel può vedere il futuro della tua tesi.\n" +
            "Gli alberi sussurrano consigli di correzioni e miglioramenti.");
        
        Room rohan = new Room("Rohan",
            "Le pianure dei Rohirrim, cavalieri valorosi.\n" +
            "Cavalli corrono liberi. Il vento porta storie di battaglie accademiche.\n" +
            "Re Théoden ti offre ospitalità e caffè forte.");
        
        Room helmsDeep = new Room("Fosso di Helm",
            "Una fortezza inespugnabile tra le montagne.\n" +
            "Qui ti prepari per l'ultima battaglia: la discussione finale della tesi.\n" +
            "Senti l'eco di precedenti studenti che hanno combattuto qui.");
        
        Room gondor = new Room("Gondor - Minas Tirith",
            "La città bianca, capitale di Gondor. Torri maestose brillano al sole.\n" +
            "Il luogo dove i grandi studiosi si riuniscono.\n" +
            "Vedi la biblioteca più grande della Terra di Mezzo.");
        
        Room camminiMorti = new Room("Sentieri dei Morti",
            "Un percorso oscuro e spaventoso. Fantasmi di studenti bocciati vagano qui.\n" +
            "Sussurrano: 'Non hai fatto abbastanza ricerche...'\n" +
            "Ma devi passare per arrivare a Mordor!");
        
        Room mordor = new Room("Mordor - Monte Fato",
            "L'OBIETTIVO FINALE! Il Monte Fato emerge davanti a te, fumante.\n" +
            "Qui devi gettare la TESI nelle fiamme per completare il tuo viaggio.\n" +
            "L'Occhio di Sauron (il professore più severo) ti osserva...\n" +
            "Questo è il momento della verità!");
        
        // Collegamenti tra stanze - IL VIAGGIO DELL'ANELLO
        contea.addExit("nord", granBurrone);
        contea.addExit("est", lothlorien);
        
        granBurrone.addExit("sud", contea);
        granBurrone.addExit("est", minieraMoria);
        granBurrone.addExit("nord", rohan);
        
        minieraMoria.addExit("ovest", granBurrone);
        minieraMoria.addExit("nord", lothlorien);
        minieraMoria.addExit("est", mordor);
        
        rohan.addExit("sud", granBurrone);
        rohan.addExit("est", helmsDeep);
        
        helmsDeep.addExit("sud", minieraMoria);
        helmsDeep.addExit("ovest", rohan);
        helmsDeep.addExit("nord", gondor);
        
        gondor.addExit("sud", helmsDeep);
        gondor.addExit("est", camminiMorti);
        
        lothlorien.addExit("ovest", contea);
        lothlorien.addExit("sud", minieraMoria);
        
        camminiMorti.addExit("ovest", gondor);
        camminiMorti.addExit("est", mordor);
        
        mordor.addExit("ovest", camminiMorti);
        mordor.addExit("sud", minieraMoria);
        
        // OGGETTI LOTR SPECIALI - Con effetti e poteri
        
        // 💍 ANELLO UNICO (Tesi) - già nel giocatore all'inizio
        Item anello = new Item("Anello", 
                               "L'Anello Unico del Potere. Ti rende invisibile ma corrompe la tua anima.",
                               true, Item.ItemType.ANELLO,
                               0,    // energia
                               2,    // corruzione +2
                               true, // invisibilità
                               false,// luce
                               0,    // difesa
                               -1);  // usi infiniti
        player.addItem(anello); // Il giocatore inizia CON l'Anello!
        
        // 🍞 LEMBAS - Pane Elfico (a Lothlorien da Galadriel)
        Item lembas1 = new Item("Lembas", 
                                "Pane degli Elfi di Lothlórien. Un morso ti sazia per giorni!",
                                true, Item.ItemType.CIBO,
                                50,   // +50 energia
                                0,    // no corruzione
                                false,// no invisibilità
                                false,// no luce
                                0,    // no difesa
                                3);   // 3 usi
        lothlorien.addItem(lembas1);
        
        Item lembas2 = new Item("Lembas", 
                                "Pane degli Elfi di Lothlórien. Un morso ti sazia per giorni!",
                                true, Item.ItemType.CIBO,
                                50, 0, false, false, 0, 3);
        lothlorien.addItem(lembas2);
        
        // 🧥 MANTELLO ELFICO - Da Galadriel (Lothlorien)
        Item mantello = new Item("Mantello", 
                                 "Mantello Elfico di Lothlórien. Ti rende invisibile senza corruzione!",
                                 true, Item.ItemType.VESTITO,
                                 0,    // no energia
                                 0,    // NO corruzione!
                                 true, // invisibilità PULITA
                                 false,// no luce
                                 0,    // no difesa
                                 -1);  // usi infiniti
        lothlorien.addItem(mantello);
        
        // 💡 FIALA DI GALADRIEL - Luce nelle tenebre (Lothlorien)
        Item fiala = new Item("Fiala", 
                             "Fiala di Galadriel con la luce di Eärendil. Scaccia le tenebre!",
                             true, Item.ItemType.LUCE,
                             0,    // no energia
                             0,    // no corruzione
                             false,// no invisibilità
                             true, // LUCE!
                             0,    // no difesa
                             -1);  // usi infiniti
        lothlorien.addItem(fiala);
        
        // ⚔️ PUNGOLO - Spada elfica (Gran Burrone)
        Item pungolo = new Item("Pungolo", 
                               "Spada elfica di Bilbo. Brilla di blu quando i nemici sono vicini!",
                               true, Item.ItemType.ARMA,
                               0,    // no energia
                               0,    // no corruzione
                               false,// no invisibilità
                               false,// no luce
                               20,   // +20 difesa
                               -1);  // usi infiniti
        granBurrone.addItem(pungolo);
        
        // Altri oggetti narrativi
        Item tesi = new Item("Tesi", "La tua TESI - documento del potere supremo! Deve essere portata a Mordor!", true, true);
        contea.addItem(tesi);
        
        Item palantir = new Item("Palantir", "Una sfera veggente. Mostra il futuro della tua carriera accademica!", true);
        gondor.addItem(palantir);
        
        // Aggiunta personaggi - PERSONAGGI LOTR
        GameCharacter gandalf = new GameCharacter("Gandalf", "Il grande mago grigio, tuo mentore e relatore");
        gandalf.addDialogue("Uno studente non è mai in ritardo, né in anticipo. Consegna precisamente quando deve!");
        gandalf.addDialogue("Tu non passi! ...Senza aver fatto le correzioni che ti ho chiesto!");
        gandalf.addDialogue("La tesi è pronta. Vai a Mordor e completa il tuo destino accademico!");
        granBurrone.addCharacter(gandalf);
        
        GameCharacter elrond = new GameCharacter("Elrond", "Signore di Gran Burrone, esperto di letteratura");
        elrond.addDialogue("Il Concilio è riunito. Abbiamo deciso: la TESI deve essere distrutta!");
        elrond.addDialogue("Solo nelle fiamme del Monte Fato può essere completato il ciclo accademico.");
        granBurrone.addCharacter(elrond);
        
        GameCharacter galadriel = new GameCharacter("Galadriel", "La Dama di Lothlórien, revisore della tesi");
        galadriel.addDialogue("Ho visto il tuo futuro... Vedo una laurea con lode!");
        galadriel.addDialogue("Ma prima devi superare la prova finale a Mordor.");
        lothlorien.addCharacter(galadriel);
        
        GameCharacter theoden = new GameCharacter("Théoden", "Re di Rohan, guerriero saggio");
        theoden.addDialogue("Rohan risponde alla chiamata! Anche noi abbiamo lottato con tesi difficili!");
        theoden.addDialogue("Prendi questo caffè. Ti darà la forza per continuare il viaggio.");
        rohan.addCharacter(theoden);
        
        GameCharacter aragorn = new GameCharacter("Aragorn", "Il re in esilio, guida sicura");
        aragorn.addDialogue("Il peso della TESI è grande, ma tu sei forte. Vai avanti!");
        aragorn.addDialogue("Gondor ti aspetta. Lì troverai aiuto per l'ultimo tratto.");
        gondor.addCharacter(aragorn);
        
        GameCharacter sauron = new GameCharacter("Sauron", "L'Occhio che tutto vede - il professore finale");
        sauron.addDialogue("VEDO TE... Hai portato la TESI fin qui. Ora gettala nel fuoco!");
        sauron.addDialogue("Solo allora il tuo viaggio accademico sarà completato!");
        mordor.addCharacter(sauron);
        
        // NUOVE STANZE PER LE IMMAGINI PERSONALIZZATE
        Room intro = new Room("intro", "🌋 Monte Fato - Prologo");
        
        Room bagEnd = new Room("bagend", "🎂 Bag End - Casa di Bilbo");
        
        Room spettri = new Room("spettri", "🧛 La Strada - Inseguimento");
        
        Room incontroRelatore = new Room("incontro con granpasso relatore", "⚔️ Locanda del Puledro Impennato");
        
        // Aggiungiamo Aragorn come relatore alla stanza
        GameCharacter granpasso = new GameCharacter("Granpasso", "Il tuo relatore - Aragorn, figlio di Arathorn");
        granpasso.addDialogue("Sono qui per proteggerti. Gli Spettri non ti prenderanno!");
        granpasso.addDialogue("Conosco sentieri nascosti. Ti guiderò a Gran Burrone in sicurezza.");
        granpasso.addDialogue("La TESI che porti è pericolosa, ma insieme ce la faremo!");
        incontroRelatore.addCharacter(granpasso);
        
        Room concilio = new Room("concilio", "🗣️ Sala del Concilio di Elrond");
        
        // NUOVE STANZE PER I 17 CAPITOLI - Immagini specifiche
        Room introsauron = new Room("introsauron", "🔥 Sauron - Il Signore Oscuro");
        Room cenaBagEnd = new Room("Cena a Bag End di Bilbo", "🎂 Cena a Bag End");
        Room obbitTronco = new Room("obbit sotto il tronco", "🌳 Hobbit nascosti dagli Spettri");
        Room granburrone = new Room("granburrone", "🏰 Gran Burrone - Rivendell");
        Room granconcilio = new Room("granconcilio", "👥 Il Gran Concilio");
        Room rivendell = new Room("rivendell", "🏰 Rivendell - Casa di Elrond");
        Room porteDurin = new Room("porte di durin", "🚪 Le Porte di Durin");
        Room argonath = new Room("argonath", "🗿 Gli Argonath - Pilastri dei Re");
        Room balrog = new Room("balrog", "🔥 Moria - Il Balrog");
        Room divisione = new Room("divisione", "🌳 Amon Hen - La Divisione");
        Room internoMonteFato = new Room("interno monte fato", "🌋 Monte Fato - Interno");
        Room tesiCheBrucia = new Room("tesi che brucia", "🔥 La Distruzione della Tesi");
        Room aquile = new Room("aquile", "🦅 Le Aquile");
        Room palazzoAule = new Room("palazzo delle aule", "🎓 Palazzo delle Aule - Università");
        
        // Salvataggio riferimenti - MAPPA LOTR CON NUOVE STANZE
        allRooms.put("intro", intro);
        allRooms.put("introsauron", introsauron);
        allRooms.put("bagend", bagEnd);
        allRooms.put("Cena a Bag End di Bilbo", cenaBagEnd);
        allRooms.put("spettri", spettri);
        allRooms.put("obbit sotto il tronco", obbitTronco);
        allRooms.put("incontro con granpasso relatore", incontroRelatore);
        allRooms.put("concilio", concilio);
        allRooms.put("granburrone", granburrone);
        allRooms.put("granconcilio", granconcilio);
        allRooms.put("rivendell", rivendell);
        allRooms.put("porte di durin", porteDurin);
        allRooms.put("argonath", argonath);
        allRooms.put("balrog", balrog);
        allRooms.put("divisione", divisione);
        allRooms.put("interno monte fato", internoMonteFato);
        allRooms.put("tesi che brucia", tesiCheBrucia);
        allRooms.put("aquile", aquile);
        allRooms.put("palazzo delle aule", palazzoAule);
        allRooms.put("contea", contea);
        allRooms.put("granburrone", granBurrone);
        allRooms.put("moria", minieraMoria);
        allRooms.put("lothlorien", lothlorien);
        allRooms.put("rohan", rohan);
        allRooms.put("helmsdeep", helmsDeep);
        allRooms.put("gondor", gondor);
        allRooms.put("camminimorti", camminiMorti);
        allRooms.put("mordor", mordor);
        
        startRoom = contea;
    }
    
    public String processCommand(String command) {
        if (command == null || command.trim().isEmpty()) {
            return "Comando non valido. Scrivi 'avanti' o 'aiuto'.";
        }
        
        String[] parts = command.trim().split("\\s+", 2);
        String action = parts[0].toLowerCase();
        String target = parts.length > 1 ? parts[1] : "";
        
        // MODALITÀ NARRATIVA - Gestione comandi
        if (narrativeMode) {
            // Se siamo nell'intro cinematica, gestisci la paginazione
            if (inCinematicIntro) {
                if (hasMorePages && (action.equals("avanti") || action.equals("continua") || action.equals("prosegui"))) {
                    // Se ci sono altre pagine dell'intro, mostra la prossima
                    nextPage();
                    String pageText = getCurrentPageText();
                    
                    // Se non ci sono più pagine, esci dall'intro
                    if (!hasMorePages) {
                        inCinematicIntro = false;
                        resetPagination();
                        return pageText + "\n\n" + startNextChapter();
                    }
                    
                    return pageText;
                } else if (!hasMorePages && (action.equals("avanti") || action.equals("continua") || action.equals("prosegui"))) {
                    // Esci dall'intro e inizia il gioco
                    inCinematicIntro = false;
                    resetPagination();
                    return startNextChapter();
                }
                return "Premi INVIO/SPAZIO o digita 'avanti' per continuare...";
            }
            
            // INPUT RAPIDO: supporto per risposte veloci con numeri/lettere singole
            // Se l'input è un numero (1,2,3) o lettera (A,B,C), trattalo come scelta
            if (action.matches("[123abc]")) {
                // Converti numero in lettera
                if (action.matches("[123]")) {
                    int num = Integer.parseInt(action);
                    action = String.valueOf((char)('A' + num - 1));
                }
                return processChoice(action.toUpperCase());
            }
            
            switch (action) {
                case "avanti":
                case "continua":
                case "prosegui":
                case "": // ENTER vuoto = avanti
                    return startNextChapter();
                    
                case "rispondi":
                    if (target.isEmpty()) {
                        return "Devi scrivere una risposta! Usa: rispondi [risposta]";
                    }
                    return answerChapter(target);
                
                case "scegli":
                    if (target.isEmpty()) {
                        return "Devi scegliere un'opzione! Usa: scegli A (o B, o C)";
                    }
                    return processChoice(target.trim().toUpperCase());
                
                // NUOVI COMANDI PER OGGETTI
                case "prendi":
                case "raccogli":
                    if (target.isEmpty()) {
                        return "Cosa vuoi prendere? Usa: prendi [nome oggetto]";
                    }
                    return takeItemFromRoom(target);
                    
                case "usa":
                case "utilizza":
                    if (target.isEmpty()) {
                        return "Cosa vuoi usare? Usa: usa [nome oggetto]";
                    }
                    return player.useItem(target);
                    
                case "inventario":
                case "zaino":
                    return player.getInventoryString();
                
                case "stato":
                case "status":
                    return player.getStatus();
                
                case "corruzione":
                    return "💍 Livello di corruzione: " + player.getCorruptionLevel() + 
                           " - " + player.getCorruptionStatus();
                    
                case "dove":
                    return getProgressStatus();
                    
                case "aiuto":
                case "help":
                    return getNarrativeHelpText();
                    
                case "esci":
                case "quit":
                    gameRunning = false;
                    return "Grazie per aver giocato a Il Signore degli Anelli!";
                    
                default:
                    // Se sembra una risposta diretta (senza "rispondi"), prova a usarla come risposta
                    if (!command.contains(" ") && command.length() <= 20) {
                        return answerChapter(command);
                    }
                    return "Comando non riconosciuto. Usa 'aiuto' per vedere i comandi disponibili.";
            }
        }
        
        // MODALITÀ CLASSICA (non usata in narrativa, ma mantenuta per compatibilità)
        String result = "";
        switch (action) {
            case "nord":
            case "sud":
            case "est":
            case "ovest":
                result = move(action);
                break;
            case "inventario":
            case "zaino":
                result = player.getInventoryString();
                break;
            case "guarda":
            case "osserva":
                result = player.getCurrentRoom().getFullDescription();
                break;
            case "aiuto":
                result = getHelpText();
                break;
            case "esci":
            case "quit":
                gameRunning = false;
                result = "Grazie per aver giocato a Lord of the Thesis!";
                break;
            default:
                result = "Comando non riconosciuto. Scrivi 'aiuto' per la lista dei comandi.";
        }
        
        addLog(result);
        return result;
    }
    
    private String move(String direction) {
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        if (nextRoom == null) {
            return "Non puoi andare in quella direzione!";
        }
        
        player.setCurrentRoom(nextRoom);
        return nextRoom.getFullDescription();
    }
    
    /**
     * Raccoglie un oggetto dalla stanza corrente (usato in modalità narrativa)
     */
    private String takeItemFromRoom(String itemName) {
        if (itemName.isEmpty()) {
            return "Cosa vuoi prendere?";
        }
        
        Item item = player.getCurrentRoom().removeItem(itemName);
        if (item == null) {
            return "❌ Non c'è nessun '" + itemName + "' qui.\n\n" +
                   "Oggetti disponibili:\n" + 
                   (player.getCurrentRoom().getItems().isEmpty() ? 
                    "  Nessun oggetto in questa stanza." : 
                    player.getCurrentRoom().getItemsString());
        }
        
        if (!item.canTake()) {
            player.getCurrentRoom().addItem(item); // rimetti l'oggetto
            return "❌ Non puoi prendere questo oggetto!";
        }
        
        if (player.addItem(item)) {
            return "✅ Hai raccolto: " + item.toString() + "\n\n" +
                   item.getDescription() + "\n\n" +
                   "💡 Usa 'usa " + itemName + "' per utilizzarlo!";
        } else {
            player.getCurrentRoom().addItem(item); // rimetti l'oggetto
            return "❌ Il tuo zaino è pieno! (Max: " + player.getInventory().size() + " oggetti)";
        }
    }
    
    private String takeItem(String itemName) {
        if (itemName.isEmpty()) {
            return "Cosa vuoi prendere?";
        }
        
        Item item = player.getCurrentRoom().removeItem(itemName);
        if (item == null) {
            return "Non c'è nessun '" + itemName + "' qui.";
        }
        
        if (!item.canTake()) {
            player.getCurrentRoom().addItem(item); // rimetti l'oggetto
            return "Non puoi prendere questo oggetto!";
        }
        
        if (player.addItem(item)) {
            return "Hai preso: " + item.getName();
        } else {
            player.getCurrentRoom().addItem(item); // rimetti l'oggetto
            return "Il tuo zaino è pieno!";
        }
    }
    
    private String useItem(String itemName) {
        if (itemName.isEmpty()) {
            return "Cosa vuoi usare?";
        }
        
        // Caso speciale: consegnare la tesi
        if (itemName.equalsIgnoreCase("tesi")) {
            if (!player.hasItem("tesi")) {
                return "Non hai la tesi con te!";
            }
            
            if (player.getCurrentRoom().getName().contains("Segreteria")) {
                gameWon = true;
                gameRunning = false;
                return "HAI VINTO!\n\n" +
                       "Con mano tremante, inserisci la tesi nella cassetta della segreteria.\n" +
                       "È fatta. Anni di studio, notti insonni, litri di caffè...\n" +
                       "Tutto culmina in questo momento.\n\n" +
                       "CONGRATULAZIONI! Hai completato Lord of the Thesis!\n" +
                       "Ora sei ufficialmente un laureando. Il mondo ti aspetta!\n\n" +
                       "Punteggio finale: " + player.getScore();
            } else {
                return "Non puoi consegnare la tesi qui! Devi andare alla Segreteria del Dipartimento!";
            }
        }
        
        Item item = player.getItem(itemName);
        if (item == null) {
            return "Non hai questo oggetto nell'inventario!";
        }
        
        // Effetti di vari oggetti
        if (itemName.equalsIgnoreCase("caffè") || itemName.equalsIgnoreCase("caffe")) {
            player.removeItem(itemName);
            player.addScore(10);
            return "Bevi il caffè. Senti l'energia scorrere nelle tue vene! (+10 punti)";
        }
        
        return "Non puoi usare questo oggetto in questo modo.";
    }
    
    private String examineItem(String itemName) {
        if (itemName.isEmpty()) {
            return "Cosa vuoi esaminare?";
        }
        
        // Caso speciale: esaminare la tesi
        if (itemName.equalsIgnoreCase("tesi")) {
            if (!player.hasItem("tesi")) {
                return "Non hai la tesi con te!";
            }
            
            return "Esamini attentamente la tua tesi di laurea...\n\n" +
                   com.lordofthethesis.graphics.PixelArtManager.getThesisInMordorLanguage() +
                   "\n\nUn documento potente, forgiato con mesi di lavoro e notti insonni.\n" +
                   "Sulla copertina sono incisi simboli in una lingua oscura... la lingua di Mordor accademico!\n" +
                   "Devi portarla alla Segreteria del Dipartimento per completare la tua missione!";
        }
        
        // Esamina oggetti nell'inventario
        Item item = player.getItem(itemName);
        if (item != null) {
            return "Esamini " + item.getName() + ":\n" + item.getDescription();
        }
        
        // Esamina oggetti nella stanza
        for (Item roomItem : player.getCurrentRoom().getItems()) {
            if (roomItem.getName().equalsIgnoreCase(itemName)) {
                return "Esamini " + roomItem.getName() + ":\n" + roomItem.getDescription();
            }
        }
        
        return "Non vedi nessun '" + itemName + "' qui.";
    }
    
    private String talkTo(String characterName) {
        if (characterName.isEmpty()) {
            return "Con chi vuoi parlare?";
        }
        
        GameCharacter character = player.getCurrentRoom().getCharacter(characterName);
        if (character == null) {
            return "Non c'è nessuno con quel nome qui.";
        }
        
        player.addScore(5);
        return character.speak();
    }
    
    private String getHelpText() {
        return "=== COMANDI DISPONIBILI ===\n\n" +
               "Movimento: nord, sud, est, ovest\n" +
               "prendi [oggetto] - Prendi un oggetto dalla stanza\n" +
               "usa [oggetto] - Usa un oggetto dall'inventario\n" +
               "esamina [oggetto] - Esamina attentamente un oggetto\n" +
               "parla [personaggio] - Parla con un personaggio\n" +
               "inventario - Mostra il contenuto del tuo zaino\n" +
               "guarda - Osserva la stanza corrente\n" +
               "aiuto - Mostra questo messaggio\n" +
               "inizia livelli - Avvia la modalita' a livelli (Q&A)\n" +
               "esci - Esci dal gioco\n\n" +
               "OBIETTIVO: Consegna la tesi alla Segreteria del Dipartimento di Informatica!\n\n" +
               "SUGGERIMENTO: Prova a 'esamina tesi' per vedere la tesi in lingua di Mordor!";
    }

    private String getLevelHelpText() {
        return "=== MODALITA' A LIVELLI ===\n" +
               "Comandi:\n" +
               "  rispondi [tua risposta] - Invia la risposta alla domanda corrente\n" +
               "  aiuto - Mostra questo messaggio\n" +
               "  esci - Esci dalla modalita' a livelli e torna al gioco normale\n\n" +
               "Scopo: rispondi correttamente alle domande per avanzare di livello.";
    }

    // VECCHIA MODALITÀ LIVELLI - Non usata in modalità narrativa
    private String startLevels() {
        // In modalità narrativa, redirigiamo a startNextChapter
        return startNextChapter();
    }
    
    // ═══════════════════════════════════════════════════════════
    // METODI MODALITÀ NARRATIVA LOTR
    // ═══════════════════════════════════════════════════════════
    
    private String startNextChapter() {
        // INTRO CINEMATICA - Mostra i 4 testi prima del primo enigma
        if (inCinematicIntro && introStep < introTexts.length) {
            String text = introTexts[introStep];
            introStep++;
            
            // Se è l'ultimo testo dell'intro, passa all'enigma
            if (introStep >= introTexts.length) {
                inCinematicIntro = false; // Fine intro
            }
            
            addLog(text);
            return text;
        }
        
        // Verifica se il capitolo corrente è stato mostrato ma non completato
        if (currentChapterStarted && !currentChapterCompleted && currentChapter < storyChapters.size()) {
            return "⚠️ Devi prima risolvere l'enigma del capitolo corrente!\n\n" +
                   "💡 Scrivi la tua risposta (esempio: 'verde' per il colore dell'erba)\n" +
                   "   oppure usa 'aiuto' per vedere i comandi disponibili.";
        }
        
        if (currentChapter >= storyChapters.size()) {
            return "🎉 HAI COMPLETATO IL SIGNORE DEGLI ANELLI! 🎉\n\n" +
                   "La TESI è stata distrutta!\n" +
                   "Sauron è sconfitto!\n" +
                   "La Terra di Mezzo è in pace.\n\n" +
                   "Torni alla Contea come eroe.\n" +
                   "Gandalf è tornato (Gandalf il Bianco)!\n" +
                   "E tu hai finalmente la tua laurea! 🎓\n\n" +
                   "CONGRATULAZIONI! 🏆";
        }
        
        Level chapter = storyChapters.get(currentChapter);
        currentChapterCompleted = false; // Il nuovo capitolo non è ancora completato
        currentChapterStarted = true; // Segna che il capitolo è stato mostrato
        
        // Cambia stanza in base al capitolo
        updateRoomByChapter(currentChapter);
        
        String msg = "\n═══════════════════════════════════════════════════════════\n" +
                     "   CAPITOLO " + (currentChapter + 1) + " / " + storyChapters.size() + ": " + chapter.getTitle() + "\n" +
                     "═══════════════════════════════════════════════════════════\n\n" +
                     chapter.getPrompt() + "\n\n";
        
        // Se ci sono scelte multiple, mostrali
        if (chapter.hasChoices()) {
            Map<String, String> choices = chapter.getChoices();
            msg += "🔘 SCELTE:\n";
            if (choices.containsKey("A")) msg += "   A) " + choices.get("A") + "\n";
            if (choices.containsKey("B")) msg += "   B) " + choices.get("B") + "\n";
            if (choices.containsKey("C")) msg += "   C) " + choices.get("C") + "\n";
            msg += "\n💡 Premi il pulsante A, B o C oppure digita 'scegli A' (o B, o C)\n";
        } else {
            msg += "💡 Usa: rispondi [risposta]\n";
        }
        msg += "═══════════════════════════════════════════════════════════";
        
        // Disabilita la paginazione per mostrare l'enigma completo
        fullText = "";
        textPage = 0;
        hasMorePages = false;
        
        addLog(msg);
        return msg; // Restituisci il testo completo senza paginazione
    }
    
    private String answerChapter(String answer) {
        if (currentChapter >= storyChapters.size()) {
            return "Hai già completato tutti i capitoli! Hai vinto! 🎉";
        }
        
        Level chapter = storyChapters.get(currentChapter);
        boolean correct = chapter.checkAnswer(answer);
        
        if (correct) {
            player.addScore(100);
            currentChapter++;
            currentChapterCompleted = true; // Marca il capitolo come completato
            currentChapterStarted = false; // Reset per il prossimo capitolo
            
            String success = "✅ RISPOSTA CORRETTA! ✅\n\n" +
                           "Capitolo completato: " + chapter.getTitle() + "\n" +
                           "(+100 punti)\n\n";
            
            if (currentChapter >= storyChapters.size()) {
                // VITTORIA FINALE!
                success += "🎆🎆🎆🎆🎆🎆🎆🎆🎆🎆🎆🎆🎆🎆🎆\n" +
                          "🌋 HAI DISTRUTTO L'ANELLO! 🌋\n" +
                          "🎆🎆🎆🎆🎆🎆🎆🎆🎆🎆🎆🎆🎆🎆🎆\n\n" +
                          "La TESI cade nella lava del Monte Fato.\n" +
                          "Sauron urla mentre la sua torre crolla.\n" +
                          "L'Occhio si spegne per sempre.\n\n" +
                          "🗡️ Aragorn diventa Re di Gondor!\n" +
                          "🧝 Gli Elfi partono per i Porti Grigi.\n" +
                          "🏡 Tu torni alla Contea come eroe!\n\n" +
                          "✨ HAI SALVATO LA TERRA DI MEZZO! ✨\n" +
                          "🎓 E hai ottenuto la tua LAUREA! 🎓\n\n" +
                          "════════════════════════════════════════\n" +
                          "      THE END - GRAZIE PER AVER GIOCATO!\n" +
                          "════════════════════════════════════════\n\n" +
                          "Punteggio finale: " + player.getScore() + " punti";
                gameWon = true;
                gameRunning = false;
            } else {
                success += "Digita 'avanti' per continuare il viaggio...";
            }
            
            addLog(success);
            return success;
        } else {
            player.addScore(-10);
            String fail = "❌ Risposta errata! ❌\n\n" +
                         "(-10 punti)\n\n" +
                         "💡 Suggerimento: " + chapter.getHint() + "\n\n" +
                         "Riprova! Usa: rispondi [risposta]";
            addLog(fail);
            return fail;
        }
    }
    
    /**
     * Gestisce le scelte narrative (A/B/C)
     */
    private String processChoice(String choice) {
        if (!choice.matches("[ABC]")) {
            return "❌ Scelta non valida! Devi scegliere A, B o C.\nUsa: scegli A (o scegli B, o scegli C)";
        }
        
        // Se il capitolo corrente ha scelte multiple, trattalo come risposta
        if (currentChapter < storyChapters.size()) {
            Level chapter = storyChapters.get(currentChapter);
            if (chapter.hasChoices()) {
                // Verifica se la scelta è corretta
                return answerChapter(choice);
            }
        }
        
        String result = "";
        
        // Scelte narrative per capitoli specifici
        if (currentChapter == 3) { // Capitolo Spettri
            switch (choice) {
                case "A":
                    result = "🏃 SCEGLI DI CORRERE!\n\n" +
                            "Corri a perdifiato nella notte!\n" +
                            "I Cavalieri Neri ti inseguono!\n" +
                            "Ma riesci a nasconderti sotto un albero cavo.\n\n" +
                            "✅ Sei salvo! (Nessuna corruzione)\n\n" +
                            "Scrivi 'avanti' per continuare...";
                    break;
                case "B":
                    result = "💍 SCEGLI DI USARE L'ANELLO!\n\n" +
                            "Indossi l'Anello per diventare invisibile!\n" +
                            "I Cavalieri Neri NON TI VEDONO!\n\n" +
                            "Ma... l'Occhio di Sauron TI HA VISTO! 👁️\n" +
                            "L'Anello ti ha corrotto un po'...\n\n" +
                            "⚠️ +1 Corruzione ⚠️\n\n" +
                            "Scrivi 'avanti' per continuare...";
                    player.addCorruption(1);
                    break;
                case "C":
                    result = "⚔️ SCEGLI DI COMBATTERE!\n\n" +
                            "Estrai la tua spada e affronti gli Spettri!\n\n" +
                            "❌ GAME OVER ❌\n\n" +
                            "Non puoi combattere i Cavalieri Neri da solo!\n" +
                            "Sei stato catturato...\n\n" +
                            "La tua avventura finisce qui.";
                    gameRunning = false;
                    break;
            }
        }
        // Esempio: quando Boromir vuole la Tesi (capitolo 11)
        else if (currentChapter == 10) { // Capitolo Divisione
            switch (choice) {
                case "A":
                    result = "🤝 SCEGLI DI DARE LA TESI A BOROMIR!\n\n" +
                            "Boromir prende la Tesi... e IMPAZZISCE!\n" +
                            "'È MIA! IL POTERE È MIO!'\n\n" +
                            "❌ GAME OVER ❌\n\n" +
                            "La Tesi ha corrotto Boromir.\n" +
                            "Sauron vince. La Terra di Mezzo è perduta.";
                    gameRunning = false;
                    break;
                case "B":
                    result = "💍 SCEGLI DI USARE L'ANELLO PER SCAPPARE!\n\n" +
                            "Indossi l'Anello e diventi invisibile!\n" +
                            "Scappi da Boromir!\n\n" +
                            "Ma l'Anello ti corrompe ancora...\n\n" +
                            "⚠️ +2 Corruzione ⚠️\n\n" +
                            "Scrivi 'avanti' per continuare...";
                    player.addCorruption(2);
                    break;
                case "C":
                    result = "🗣️ SCEGLI DI CONVINCERE BOROMIR!\n\n" +
                            "'Boromir, l'Anello ti sta corrompendo!'\n" +
                            "'Ricorda chi sei! Sei un guerriero di Gondor!'\n\n" +
                            "Boromir si ferma: 'Cosa... cosa sto facendo?'\n" +
                            "'Perdonami! Avevi ragione!'\n\n" +
                            "✅ Hai salvato Boromir! (Nessuna corruzione)\n\n" +
                            "Scrivi 'avanti' per continuare...";
                    break;
            }
        }
        // Esempio: al Monte Fato - momento decisivo!
        else if (currentChapter == 14) { // Capitolo Monte Fato interno
            switch (choice) {
                case "A":
                    result = "💍 SCEGLI DI TENERE L'ANELLO!\n\n" +
                            "'L'Anello è MIO!'\n\n" +
                            "❌ GAME OVER - FINALE CATTIVO ❌\n\n" +
                            "Sei diventato come Gollum.\n" +
                            "Sauron vince. La Terra di Mezzo cade nelle tenebre.";
                    gameRunning = false;
                    break;
                case "B":
                    result = "🌋 SCEGLI DI DISTRUGGERE LA TESI!\n\n" +
                            "Con l'ultimo sforzo, lanci la Tesi nella lava!\n\n" +
                            "🔥 LA TESI BRUCIA! 🔥\n\n" +
                            "Sauron urla! La Torre crolla!\n" +
                            "L'Occhio si spegne!\n\n" +
                            "✅ VITTORIA! ✅\n\n" +
                            "Scrivi 'avanti' per il finale...";
                    break;
                case "C":
                    result = "🤔 SCEGLI DI ESITARE...\n\n" +
                            "Non riesci a decidere...\n" +
                            "L'Anello ti chiama... 'Non distruggermi!'\n\n" +
                            "Sam: 'FORZA! FALLO ORA!'\n\n" +
                            "⚠️ +1 Corruzione ⚠️\n\n" +
                            "Scrivi 'scegli B' per distruggere la tesi!";
                    player.addCorruption(1);
                    break;
            }
        }
        else {
            result = "🤔 Non ci sono scelte da fare in questo capitolo.\n" +
                    "Usa 'avanti' per proseguire o 'rispondi [risposta]' per l'enigma.";
        }
        
        addLog(result);
        return result;
    }
    
    private void updateRoomByChapter(int chapterIndex) {
        // Cambia la stanza corrente in base al capitolo per aggiornare l'immagine
        String[] roomKeys = {
            "introsauron",                       // Cap 1: Sauron forgia la Tesi (intro specifica)
            "contea",                            // Cap 2: La Contea
            "Cena a Bag End di Bilbo",           // Cap 3: Festa di Bilbo (immagine specifica)
            "obbit sotto il tronco",             // Cap 4: Nazgûl (hobbit si nascondono)
            "incontro con granpasso relatore",   // Cap 5: Granpasso/Il Relatore
            "granburrone",                       // Cap 6: Gran Burrone/Rivendell
            "granconcilio",                      // Cap 7: Gran Concilio di Elrond
            "porte di durin",                    // Cap 8: Porte di Durin - Moria
            "argonath",                          // Cap 9: Argonath - Pilastri dei Re
            "balrog",                            // Cap 10: Balrog in Moria
            "divisione",                         // Cap 11: Divisione della Compagnia
            "contea",                            // Cap 12: Viaggio con Sam (ritorno mentale alla Contea)
            "mordor",                            // Cap 13: Alle porte di Mordor
            "interno monte fato",                // Cap 14: Monte Fato interno - Scalata
            "tesi che brucia",                   // Cap 15: Distruzione della Tesi
            "aquile",                            // Cap 16: Salvataggio delle Aquile
            "palazzo delle aule"                 // Cap 17: Seduta di Laurea (palazzo università)
        };
        
        // Musiche corrispondenti per ogni capitolo
        String[] musicFiles = {
            "intro.wav",        // Cap 1: Tema epico di Sauron
            "contea.wav",       // Cap 2: Musica pastorale della Contea
            "bagend.wav",       // Cap 3: Festa allegra di Bilbo
            "spettri.wav",      // Cap 4: Tema dark dei Nazgûl
            "granpasso.wav",    // Cap 5: Tema eroico del Relatore
            "rivendell.wav",    // Cap 6: Musica elfica di Rivendell
            "concilio.wav",     // Cap 7: Tema solenne del Concilio
            "intro.wav",        // Cap 8: Moria (tema epico)
            "rivendell.wav",    // Cap 9: Argonath (tema maestoso)
            "intro.wav",        // Cap 10: Balrog (tema epico)
            "spettri.wav",      // Cap 11: Divisione (tema drammatico)
            "contea.wav",       // Cap 12: Viaggio con Sam
            "intro.wav",        // Cap 13: Mordor (tema epico)
            "intro.wav",        // Cap 14: Monte Fato (tema epico)
            "intro.wav",        // Cap 15: Distruzione (tema epico)
            "rivendell.wav",    // Cap 16: Aquile (tema di salvezza)
            "concilio.wav"      // Cap 17: Laurea (tema trionfale)
        };
        
        // Cambia stanza
        if (chapterIndex < roomKeys.length && allRooms.containsKey(roomKeys[chapterIndex])) {
            player.setCurrentRoom(allRooms.get(roomKeys[chapterIndex]));
        }
        
        // Cambia musica
        if (chapterIndex < musicFiles.length) {
            audioManager.playMusic(musicFiles[chapterIndex]);
        }
    }
    
    private String getNarrativeHelpText() {
        return "═══════════════════════════════════════════════════════════\n" +
               "           🎮 COMANDI DISPONIBILI 🎮\n" +
               "═══════════════════════════════════════════════════════════\n\n" +
               "📖 NAVIGAZIONE:\n" +
               "   avanti / ENTER      - Avanza al prossimo capitolo\n" +
               "   [risposta]          - Risposta diretta (senza 'rispondi')\n" +
               "   rispondi [risposta] - Rispondi all'enigma\n\n" +
               "⚔️  SCELTE:\n" +
               "   1 / 2 / 3           - Scelta rapida con numeri\n" +
               "   A / B / C           - Scelta rapida con lettere\n" +
               "   scegli [A/B/C]      - Fai una scelta narrativa\n\n" +
               "🎒 OGGETTI:\n" +
               "   prendi [nome]       - Raccogli un oggetto\n" +
               "   usa [nome]          - Usa un oggetto (Lembas, Mantello, etc)\n" +
               "   inventario          - Mostra il tuo zaino\n\n" +
               "📊 STATO:\n" +
               "   stato               - Mostra energia, difesa, corruzione\n" +
               "   corruzione          - Livello di corruzione dell'Anello\n" +
               "   dove                - Progresso nella storia\n\n" +
               "❓ ALTRO:\n" +
               "   aiuto               - Mostra questo messaggio\n" +
               "   esci                - Esci dal gioco\n\n" +
               "═══════════════════════════════════════════════════════════\n" +
               "  💡 OGGETTI SPECIALI LOTR:\n" +
               "  💍 Anello - invisibilità (+2 corruzione)\n" +
               "  🍞 Lembas - pane elfico (+50 energia)\n" +
               "  🧥 Mantello - invisibilità pulita (no corruzione)\n" +
               "  💡 Fiala - luce contro le tenebre\n" +
               "  ⚔️  Pungolo - spada elfica (+20 difesa)\n" +
               "═══════════════════════════════════════════════════════════";
    }
    
    private String getProgressStatus() {
        return "═══════════════════════════════════════════════════════════\n" +
               "           📊 PROGRESSO NEL VIAGGIO 📊\n" +
               "═══════════════════════════════════════════════════════════\n\n" +
               "👤 Giocatore: " + player.getName() + "\n" +
               "📍 Capitolo: " + (currentChapter + 1) + " / " + storyChapters.size() + "\n" +
               "🏆 Punteggio: " + player.getScore() + " punti\n" +
               "� Corruzione: " + player.getCorruptionStatus() + " (" + player.getCorruptionLevel() + ")\n" +
               "�📍 Posizione: " + player.getCurrentRoom().getName() + "\n\n" +
               (currentChapter < storyChapters.size() 
                   ? "📖 Capitolo Corrente: " + storyChapters.get(currentChapter).getTitle()
                   : "✅ Hai completato tutti i capitoli!") + "\n\n" +
               "═══════════════════════════════════════════════════════════";
    }

    // VECCHIA MODALITÀ answerLevel - redirige a answerChapter
    private String answerLevel(String answer) {
        // In modalità narrativa, usiamo answerChapter
        return answerChapter(answer);
    }
    
    private void addLog(String message) {
        gameLog.add(message);
    }
    
    // Getters
    public Player getPlayer() { return player; }
    public boolean isGameRunning() { return gameRunning; }
    public boolean isGameWon() { return gameWon; }
    public List<String> getGameLog() { return gameLog; }
    public String getLastLog() { 
        return gameLog.isEmpty() ? "" : gameLog.get(gameLog.size() - 1);
    }
    
    // Ottieni chiave della stanza corrente (per renderer)
    public String getCurrentRoomKey() {
        if (player == null || player.getCurrentRoom() == null) {
            return "default";
        }
        String roomName = player.getCurrentRoom().getName();
        
        // Mappa nomi stanze LOTR alle chiavi immagini personalizzate
        if (roomName.equalsIgnoreCase("intro")) return "intro";
        if (roomName.equalsIgnoreCase("bagend")) return "bagend";
        if (roomName.equalsIgnoreCase("spettri")) return "spettri";
        if (roomName.equalsIgnoreCase("incontro con granpasso relatore")) return "incontro con granpasso relatore";
        if (roomName.equalsIgnoreCase("concilio")) return "concilio";
        
        // Mapping originali
        if (roomName.contains("Contea")) return "contea";
        if (roomName.contains("Gran Burrone")) return "granburrone";
        if (roomName.contains("Moria")) return "moria";
        if (roomName.contains("Lothlórien") || roomName.contains("Lothlorien")) return "lothlorien";
        if (roomName.contains("Rohan")) return "rohan";
        if (roomName.contains("Fosso di Helm") || roomName.contains("Helm")) return "helmsdeep";
        if (roomName.contains("Gondor") || roomName.contains("Minas Tirith")) return "gondor";
        if (roomName.contains("Sentieri dei Morti") || roomName.contains("Cammini")) return "camminimorti";
        if (roomName.contains("Mordor") || roomName.contains("Monte Fato")) return "mordor";
        
        return "default";
    }

    // Narrative mode getters (compatibilità con interfaccia precedente)
    public boolean isLevelMode() { return narrativeMode; }
    public boolean isNarrativeMode() { return narrativeMode; }
    public Level getCurrentLevel() {
        if (currentChapter < 0 || currentChapter >= storyChapters.size()) return null;
        return storyChapters.get(currentChapter);
    }
    
    // Audio controls
    public AudioManager getAudioManager() {
        return audioManager;
    }
    
    public void volumeUp() {
        audioManager.volumeUp();
    }
    
    public void volumeDown() {
        audioManager.volumeDown();
    }
    
    public void toggleMute() {
        audioManager.toggleMute();
    }
    
    // Attiva l'intro cinematica (da chiamare all'avvio del gioco)
    public void startCinematicIntro() {
        inCinematicIntro = false; // Salta l'intro, vai direttamente al primo capitolo
        introStep = 0;
        // Non serve più l'intro cinematica - il primo capitolo si avvia automaticamente
    }
    
    // Forza l'avvio del primo capitolo (chiamato dalla GUI all'inizio)
    public String forceStartFirstChapter() {
        currentChapterStarted = false; // Reset per permettere l'avvio
        currentChapterCompleted = false;
        return startNextChapter();
    }
    
    public boolean isInCinematicIntro() {
        return inCinematicIntro;
    }
    
    // Sistema di paginazione testo
    public String paginateText(String text, int maxLines) {
        String[] lines = text.split("\n");
        int linesPerPage = maxLines;
        int totalPages = (int) Math.ceil((double) lines.length / linesPerPage);
        
        if (textPage >= totalPages) {
            textPage = totalPages - 1;
        }
        
        int startLine = textPage * linesPerPage;
        int endLine = Math.min(startLine + linesPerPage, lines.length);
        
        StringBuilder page = new StringBuilder();
        for (int i = startLine; i < endLine; i++) {
            page.append(lines[i]).append("\n");
        }
        
        hasMorePages = (textPage < totalPages - 1);
        
        if (hasMorePages) {
            page.append("\n[Premi INVIO o SPAZIO per continuare...]");
        }
        
        return page.toString();
    }
    
    public boolean nextPage() {
        if (fullText.isEmpty()) {
            hasMorePages = false;
            return false;
        }
        
        // Calcola prima il numero totale di pagine
        String[] lines = fullText.split("\n");
        int linesPerPage = 12; // Ridotto da 20 a 12 per più pagine
        int totalPages = (int) Math.ceil((double) lines.length / linesPerPage);
        
        // Controlla se possiamo avanzare
        if (textPage < totalPages - 1) {
            textPage++;
            hasMorePages = (textPage < totalPages - 1);
            return true;
        }
        hasMorePages = false;
        return false;
    }
    
    public boolean previousPage() {
        if (textPage > 0) {
            textPage--;
            // Ricalcola hasMorePages
            if (!fullText.isEmpty()) {
                String[] lines = fullText.split("\n");
                int linesPerPage = 12; // Ridotto da 20 a 12 per più pagine
                int totalPages = (int) Math.ceil((double) lines.length / linesPerPage);
                hasMorePages = (textPage < totalPages - 1);
            }
            return true;
        }
        return false;
    }
    
    public void resetPagination() {
        textPage = 0;
        hasMorePages = false;
        fullText = "";
    }
    
    public boolean hasMorePages() {
        return hasMorePages;
    }
    
    // Debug: info sulla paginazione
    public String getPaginationInfo() {
        if (fullText.isEmpty()) {
            return "fullText: vuoto";
        }
        String[] lines = fullText.split("\n");
        int totalPages = (int) Math.ceil((double) lines.length / 12.0); // Cambiato da 20 a 12
        return String.format("Page %d/%d (lines: %d, hasMore: %b)", 
            textPage + 1, totalPages, lines.length, hasMorePages);
    }
    
    // Ottieni il testo della pagina corrente
    public String getCurrentPageText() {
        if (fullText.isEmpty()) {
            return "";
        }
        return paginateText(fullText, 12); // Cambiato da 20 a 12 righe per pagina
    }
}
