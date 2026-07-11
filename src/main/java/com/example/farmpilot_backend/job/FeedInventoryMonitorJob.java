package com.example.farmpilot_backend.job;

import com.example.farmpilot_backend.entity.FeedInventory;
import com.example.farmpilot_backend.publisher.AlertPublisher;
import com.example.farmpilot_backend.repository.FeedInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FeedInventoryMonitorJob {
    private final FeedInventoryRepository feedInventoryRepository;
    private final AlertPublisher alertPublisher;
    private static final BigDecimal CRITICAL_THRESHOLD_KG = new BigDecimal("100.00");

    @Scheduled(cron = "0 0 9 * * ?")
    @Transactional(readOnly = true)
    public void checkCriticalFeedLevels() {
        log.info("Starting daily feed inventory monitor job...");
        List<FeedInventory> criticalInventories = feedInventoryRepository.findByNumberInKgLessThan(CRITICAL_THRESHOLD_KG);

        if (criticalInventories.isEmpty()) {
            log.info("All feed stocks are above the critical threshold.");
            return;
        }

        for (FeedInventory inventory : criticalInventories) {
            String feedName = inventory.getName();
            BigDecimal currentAmount = inventory.getNumberInKg();
            String farmName = inventory.getFarm() != null ? inventory.getFarm().getName() : "Unknown Farm";

            log.warn("SUPPLY ALERT: Critical low stock for '{}' at farm '{}'! Only {} kg left. Urgent restock required.",
                    feedName, farmName, currentAmount);

            String alertMessage = String.format("SUPPLY ALERT: Critical low stock for '%s' at farm '%s'! Only %s kg left. Urgent restock required.",
                    feedName, farmName, currentAmount);
            alertPublisher.publishAlert(alertMessage);
        }

        log.info("Feed inventory monitor job finished. Found {} items requiring restock.", criticalInventories.size());
    }
}