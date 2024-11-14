package kz.mediation.api.mediation_executor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


// This ChatGpt will be used for AudioToText and TextToAudio conversion
//@Service
@Slf4j
@RequiredArgsConstructor
public class OpenAiService {
//    private final RestTemplate restTemplate;
//    @Value("${openai.api.api-key}")
//    private String apiKey;
//
//    public void fetchAndSaveSpeech() {
//        String apiUrl = "https://api.openai.com/v1/audio/speech";
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + apiKey);
//        headers.set("Content-Type", "application/json");
//        String requestJson = """
//                {
//                    "model": "tts-1",
//                    "input": "Today is a wonderful day to build something people love!",
//                    "voice": "alloy"
//                }
//                """;
//        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
//        ResponseEntity<Resource> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, Resource.class);
//        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
//            try (InputStream inputStream = response.getBody().getInputStream();
//                 BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("speech.mp3"))) {
//                byte[] buffer = new byte[4096];
//                int bytesRead;
//                while ((bytesRead = inputStream.read(buffer)) != -1) {
//                    outputStream.write(buffer, 0, bytesRead);
//                }
//                System.out.println("Audio file has been saved as 'speech.mp3'");
//            } catch (IOException e) {
//                e.printStackTrace();
//                // Handle exceptions
//            }
//        } else {
//            // Handle non-successful response
//            System.out.println("Failed to fetch audio: " + response.getStatusCode());
//        }
//    }
}
