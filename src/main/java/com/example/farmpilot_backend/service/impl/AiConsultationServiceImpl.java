package com.example.farmpilot_backend.service.impl;

import com.example.farmpilot_backend.service.AiConsultationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient; // Додали загублений імпорт
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AiConsultationServiceImpl implements AiConsultationService {
    private final ChatClient chatClient;

    public AiConsultationServiceImpl(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultSystem("Ти — професійний агро-експерт, досвідчений ветеринар та консультант з управління фермою. " +
                        "Твоя мета — допомагати фермеру вирішувати проблеми зі здоров'ям тварин, розрахунком кормів та управлінням. " +
                        "Відповідай українською мовою: чітко, структуровано, професійно. " +
                        "Якщо користувач ставить питання, яке взагалі не стосується сільського господарства, фермерства чи тварин, " +
                        "ввічливо відхили запит і нагадай, що ти спеціалізуєшся лише на агро-тематиці.")
                .build();
    }

    @Override
    public String getConsultation(String userMessage) {
        log.info("Відправка запиту до Ollama: {}", userMessage);

        try {
            String response = this.chatClient.prompt()
                    .user(userMessage)
                    .call()
                    .content();

            log.info("Успішно отримано відповідь від AI-консультанта.");
            return response;

        } catch (Exception e) {
            log.error("Помилка під час генерації відповіді ШІ", e);
            return "Вибачте, наразі AI-консультант недоступний. Перевірте, чи запущена Ollama локально.";
        }
    }
}