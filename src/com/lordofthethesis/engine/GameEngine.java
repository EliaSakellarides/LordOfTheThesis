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
    // Audio manager per musiche di sottofondo
    private AudioManager audioManager;
    // Intro cinematica
    private boolean inCinematicIntro;
    private int introStep;
    private String[] introTexts;
    
    public GameEngine() {
        this.allRooms = new HashMap<>();
        this.gameLog = new ArrayList<>();
        this.gameRunning = false;
        this.gameWon = false;
        this.narrativeMode = true; // ATTIVA MODALITÀ NARRATIVA
        this.storyChapters = new ArrayList<>();
        this.currentChapter = 0;
        this.audioManager = new AudioManager();
        this.inCinematicIntro = false;
        this.introStep = 0;
        
        // Testi dell'intro cinematica (come nel film!)
        this.introTexts = new String[]{
            "═══════════════════════════════════════════════════════════\n" +
            "           🌋 IL SIGNORE DEGLI ANELLI 🌋\n" +
            "              LA COMPAGNIA DELLA TESI\n" +
            "═══════════════════════════════════════════════════════════\n\n" +
            "\"Il mondo è cambiato.\n" +
            "Lo sento nell'acqua.\n" +
            "Lo sento nella terra.\n" +
            "Lo odoro nell'aria.\n\n" +
            "Molto di ciò che era è andato perduto,\n" +
            "poiché ora non vive più nessuno che lo ricordi.\"\n\n" +
            "Scrivi 'avanti' per continuare...",
            
            "\"Tutto ebbe inizio con la forgiatura delle Grandi Tesi.\n\n" +
            "💍 Tre furono donate agli Elfi,\n" +
            "   immortali, saggi e giusti.\n\n" +
            "💍 Sette ai Signori dei Nani,\n" +
            "   scavatori nelle profondità delle montagne.\n\n" +
            "💍 E nove, nove tesi furono donate agli Uomini,\n" +
            "   che sopra ogni cosa desiderano il potere.\"\n\n" +
            "Scrivi 'avanti' per continuare...",
            
            "\"Ma tutti furono ingannati,\n" +
            "perché fu creata un'altra Tesi.\n\n" +
            "🌋 Nelle fiamme del Monte Fato,\n" +
            "il Signore Oscuro Sauron forgiò in segreto\n" +
            "una TESI SUPREMA per controllarle tutte.\n\n" +
            "E in questa Tesi versò la sua crudeltà,\n" +
            "la sua malvagità e la sua volontà\n" +
            "di dominare ogni forma di vita.\"\n\n" +
            "Scrivi 'avanti' per continuare...",
            
            "═══════════════════════════════════════════════════════════\n" +
            "              💍 LA TESI UNICA 💍\n" +
            "═══════════════════════════════════════════════════════════\n\n" +
            "    UNA TESI PER DOMINARLE TUTTE\n" +
            "    UNA TESI PER TROVARLE\n" +
            "    UNA TESI PER GHERMIRLE\n" +
            "    E NEL BUIO INCATENARLE\n\n" +
            "Questa Tesi ha il potere di dare la laurea con lode...\n" +
            "Ma può anche corrompere chi la possiede.\n\n" +
            "Deve essere distrutta. A Mordor, dove fu forgiata.\"\n\n" +
            "Scrivi 'avanti' per iniziare il viaggio..."
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
        addLog("Digita 'avanti' per iniziare il primo enigma.");
        addLog("");
    }

    private void createStoryChapters() {
        storyChapters.clear();
        
        // CAPITOLO 1: INTRO - SAURON E LA TESI
        storyChapters.add(new Level(
            "cap1_sauron",
            "Prologo - Sauron forgia la Tesi",
            "� Nelle fiamme del Monte Fato, Sauron forgia la TESI UNICA!\n\n" +
            "💍 'UNA TESI PER DOMINARLE TUTTE' 💍\n\n" +
            "Questa tesi ha il potere di dare la laurea con lode...\n" +
            "Ma corrompe chi la possiede!\n\n" +
            "Deve essere distrutta dove fu creata: a Mordor.\n\n" +
            "❓ Per iniziare il viaggio, quanto fa 1 + 1?",
            Arrays.asList("2", "due"),
            "La somma più semplice!"
        ));
        
        // CAPITOLO 2: LA CONTEA
        storyChapters.add(new Level(
            "cap2_contea",
            "La Contea - Casa tua",
            "� Sei nella tranquilla Contea, tra verdi colline.\n\n" +
            "Hai appena finito di scrivere la tua TESI.\n" +
            "Ma c'è qualcosa di strano... sembra avere un potere oscuro.\n\n" +
            "Il tuo relatore (un certo Gandalf) ti ha avvertito:\n" +
            "'Questa tesi è pericolosa! Devi portarla a Gran Burrone!'\n\n" +
            "❓ Prima di partire: qual è il colore dell'erba?",
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
            "💍 'Lascio tutto a te, caro cugino!'\n" +
            "'Prenditi cura della TESI. È più importante di quanto pensi.'\n\n" +
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
        
        // CAPITOLO 8: VERSO MORDOR
        storyChapters.add(new Level(
            "cap8_viaggio",
            "Il Viaggio verso Mordor",
            "⛰️ La Compagnia inizia il viaggio verso Mordor!\n\n" +
            "Attraversate foreste, montagne, fiumi.\n" +
            "Aragorn (il tuo relatore) ti guida:\n" +
            "'La strada è lunga, ma ce la farai!'\n\n" +
            "Gandalf ti incoraggia:\n" +
            "'La TESI sarà distrutta, e tu sarai libero!'\n\n" +
            "Giorni di marcia... notti sotto le stelle...\n" +
            "La Torre di Sauron si avvicina all'orizzonte.\n\n" +
            "❓ Per mantenerti concentrato: quanto fa 6 + 2?",
            Arrays.asList("8", "otto"),
            "Somma!"
        ));
        
        // CAPITOLO 9: ALLE PORTE DI MORDOR
        storyChapters.add(new Level(
            "cap9_mordor",
            "Mordor - Il Cancello Nero",
            "🌋 Eccoci. MORDOR.\n\n" +
            "Il cielo è rosso fuoco. L'aria è irrespirabile.\n" +
            "La Torre di Sauron si erge minacciosa.\n" +
            "L'Occhio ti cerca...\n\n" +
            "Aragorn: 'Io e la Compagnia attaccheremo il Cancello.'\n" +
            "'Tu entra in Mordor mentre siamo distratti!'\n\n" +
            "Gandalf: 'Vai! È il tuo momento!'\n\n" +
            "🗡️ La battaglia ha inizio!\n" +
            "Mentre i tuoi amici combattono, tu corri verso il Monte Fato.\n\n" +
            "❓ Per trovare la forza: quanto fa 3 + 3?",
            Arrays.asList("6", "sei"),
            "Somma semplice per l'ultimo sforzo!"
        ));
        
        // CAPITOLO 10: MONTE FATO - FINALE
        storyChapters.add(new Level(
            "cap10_finale",
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
            "Alzi la TESI sopra la testa...\n\n" +
            "❓ Per completare il viaggio, scrivi: FINE",
            Arrays.asList("fine", "FINE", "Fine"),
            "Scrivi la parola 'fine'!"
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
        
        // Aggiunta oggetti - OGGETTI LOTR
        Item tesi = new Item("tesi", "La tua TESI - l'Unico Anello del Sapere! Deve essere distrutta a Mordor!", true, true);
        contea.addItem(tesi);
        
        Item lembas = new Item("lembas", "Pane degli Elfi. Un morso ti sazia per giorni di studio!", true);
        lothlorien.addItem(lembas);
        
        Item spada = new Item("spada", "Pungolo, la spada elfica che brilla quando ci sono deadline vicine!", true);
        granBurrone.addItem(spada);
        
        Item palantir = new Item("palantir", "Una sfera veggente. Mostra il futuro della tua carriera accademica!", true);
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
        
        // Salvataggio riferimenti - MAPPA LOTR CON NUOVE STANZE
        allRooms.put("intro", intro);
        allRooms.put("bagend", bagEnd);
        allRooms.put("spettri", spettri);
        allRooms.put("incontro con granpasso relatore", incontroRelatore);
        allRooms.put("concilio", concilio);
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
            switch (action) {
                case "avanti":
                case "continua":
                case "prosegui":
                    return startNextChapter();
                    
                case "rispondi":
                    if (target.isEmpty()) {
                        return "Devi scrivere una risposta! Usa: rispondi [risposta]";
                    }
                    return answerChapter(target);
                    
                case "aiuto":
                    return getNarrativeHelpText();
                    
                case "dove":
                case "stato":
                    return getProgressStatus();
                    
                case "inventario":
                case "zaino":
                    return player.getInventoryString();
                    
                case "esci":
                case "quit":
                    gameRunning = false;
                    return "Grazie per aver giocato a Il Signore degli Anelli!";
                    
                default:
                    return "Comando non riconosciuto. Usa 'avanti' per il prossimo capitolo, 'rispondi [risposta]' per rispondere, 'aiuto' per info.";
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
        
        // Cambia stanza in base al capitolo
        updateRoomByChapter(currentChapter);
        
        String msg = "\n═══════════════════════════════════════════════════════════\n" +
                     "   CAPITOLO " + (currentChapter + 1) + " / " + storyChapters.size() + ": " + chapter.getTitle() + "\n" +
                     "═══════════════════════════════════════════════════════════\n\n" +
                     chapter.getPrompt() + "\n\n" +
                     "💡 Usa: rispondi [risposta]\n" +
                     "═══════════════════════════════════════════════════════════";
        
        addLog(msg);
        return msg;
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
    
    private void updateRoomByChapter(int chapterIndex) {
        // Cambia la stanza corrente in base al capitolo per aggiornare l'immagine
        String[] roomKeys = {
            "intro",                             // Cap 1: Sauron forgia la Tesi
            "contea",                            // Cap 2: La Contea
            "bagend",                            // Cap 3: Festa di Bilbo
            "spettri",                           // Cap 4: Nazgûl
            "incontro con granpasso relatore",   // Cap 5: Il Relatore salva
            "granburrone",                       // Cap 6: Rivendell
            "concilio",                          // Cap 7: Concilio
            "contea",                            // Cap 8: Verso Mordor (placeholder)
            "contea",                            // Cap 9: Mordor (placeholder)
            "contea"                             // Cap 10: Monte Fato (placeholder)
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
            "contea.wav",       // Cap 8: Placeholder
            "contea.wav",       // Cap 9: Placeholder
            "contea.wav"        // Cap 10: Placeholder
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
               "📖 avanti         - Inizia il prossimo capitolo\n" +
               "✍️  rispondi [risposta] - Rispondi all'enigma\n" +
               "❓ aiuto          - Mostra questo messaggio\n" +
               "📊 dove/stato     - Mostra progresso nella storia\n" +
               "🎒 inventario     - Mostra il tuo zaino\n" +
               "🚪 esci           - Esci dal gioco\n\n" +
               "═══════════════════════════════════════════════════════════\n" +
               "        Segui la storia del Signore degli Anelli!\n" +
               "        Risolvi enigmi per avanzare nel viaggio!\n" +
               "═══════════════════════════════════════════════════════════";
    }
    
    private String getProgressStatus() {
        return "═══════════════════════════════════════════════════════════\n" +
               "           📊 PROGRESSO NEL VIAGGIO 📊\n" +
               "═══════════════════════════════════════════════════════════\n\n" +
               "👤 Giocatore: " + player.getName() + "\n" +
               "📍 Capitolo: " + (currentChapter + 1) + " / " + storyChapters.size() + "\n" +
               "🏆 Punteggio: " + player.getScore() + " punti\n" +
               "📍 Posizione: " + player.getCurrentRoom().getName() + "\n\n" +
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
        inCinematicIntro = true;
        introStep = 0;
        // Imposta subito l'immagine di Sauron
        updateRoomByChapter(0);
    }
    
    public boolean isInCinematicIntro() {
        return inCinematicIntro;
    }
}
