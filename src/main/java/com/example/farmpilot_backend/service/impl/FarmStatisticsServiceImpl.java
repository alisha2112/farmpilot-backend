package com.example.farmpilot_backend.service.impl;

import com.example.farmpilot_backend.repository.FeedInventoryRepository;
import com.example.farmpilot_backend.service.FarmStatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FarmStatisticsServiceImpl implements FarmStatisticsService {
    private final FeedInventoryRepository feedInventoryRepository;

    @Override
    @Cacheable(value = "monthlyStats", key = "#farmId")
    public String generateMonthlyReport(Long farmId) {
        log.info("[БД] Формування реального звіту з PostgreSQL для ферми ID: {}", farmId);
        Double totalFeedKg = feedInventoryRepository.sumTotalFeedInKgByFarmId(farmId);
        Double totalFeedCost = feedInventoryRepository.sumTotalFeedCostByFarmId(farmId);
        log.info("Реальний звіт сформовано та готовий до збереження в Redis!");
        return String.format(
                "Статистика ферми #%d:\n- Загальні запаси корму: %.2f кг\n- Загальна вартість запасів: %.2f у.о.",
                farmId, totalFeedKg, totalFeedCost
        );
    }
}
