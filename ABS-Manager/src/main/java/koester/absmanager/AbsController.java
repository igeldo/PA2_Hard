//package koester.absmanager;
//
//import org.springframework.ai.ollama.OllamaChatModel;
//import org.springframework.ai.openai.OpenAiChatModel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//public class AbsController {
//
//    private final OllamaChatModel ollamaChatModel;
//    private final OpenAiChatModel openAiChatModel;
//    private boolean fileContentUsed = false; // Neues Flag zum Prüfen, ob Dateiinhalt bereits verwendet wurde
//    private String fileContent = ""; // Variable zum Speichern des Datei-Inhalts
//
//    @Autowired
//    public AbsController(OllamaChatModel ollamaChatModel, OpenAiChatModel openAiChatModel) {
//        this.ollamaChatModel = ollamaChatModel;
//        this.openAiChatModel = openAiChatModel;
//    }
//
//    @GetMapping("/ai/simple")
//    public Map<String, String> generate(@RequestParam(value = "value1") String value1,
//                                        @RequestParam(value = "value2") String value2) {
//        // Pfad zur txt-Datei
//        String filePath = "/Users/kevinkoster/Documents/FH-Dortmund/Master/Master-Studienarbeit/SpringAiDualChat/Antibiotika Leitfaden.txt";
//
//        if (!fileContentUsed) { // Nur beim ersten Aufruf den Inhalt der Datei lesen
//            try {
//                // Dateiinhalt lesen und speichern
//                fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
//                fileContentUsed = true; // Flag setzen, damit die Datei nicht erneut geladen wird
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // Kombiniere die zwei Dropdown-Werte mit dem Dateiinhalt (wenn vorhanden)
//        String combinedMessage;
//
//        if (!fileContent.isEmpty()) {
//            // Text aus der Datei mit den Dropdown-Werten kombinieren
//            combinedMessage = fileContent + "\n" + "Ich brauche die passenden Antibiotika aus der Liste mit Therapie. : " + value1 + " and " + value2;
//        } else {
//            // Nur die Dropdown-Werte verwenden, wenn die Datei nicht gelesen wurde
//            combinedMessage = "Ich brauche die passenden Antibiotika aus der Liste mit Therapie. : " + value1 + " and " + value2;
//        }
//
//        // Aufruf der Modelle mit der kombinierten Nachricht
//        String ollamaResponse = ollamaChatModel.call(combinedMessage);
//        String openAiResponse = openAiChatModel.call(combinedMessage);
//
//        // Antworten in eine Map speichern
//        Map<String, String> response = new HashMap<>();
//        response.put("ollama", ollamaResponse);
//        response.put("openai", openAiResponse);
//
//        return response;
//    }
//}