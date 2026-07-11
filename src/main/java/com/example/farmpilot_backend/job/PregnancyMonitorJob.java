package com.example.farmpilot_backend.job;

import com.example.farmpilot_backend.entity.Pregnancy;
import com.example.farmpilot_backend.publisher.AlertPublisher;
import com.example.farmpilot_backend.repository.PregnancyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PregnancyMonitorJob {
    private final PregnancyRepository pregnancyRepository;
    private final AlertPublisher alertPublisher;
    @Scheduled(cron = "0 0 8 * * ?")
    @Transactional(readOnly = true)
    public void checkUpcomingBirths() {
        log.info("Starting daily pregnancy monitor job...");
        LocalDate targetDate = LocalDate.now().plusDays(3);
        List<Pregnancy> upcomingBirths = pregnancyRepository.findUpcomingBirths(targetDate);

        if (upcomingBirths.isEmpty()) {
            log.info("No births expected on {}.", targetDate);
            return;
        }

        for (Pregnancy pregnancy : upcomingBirths) {
            String pigTag = pregnancy.getPig().getTagNumber();
            log.warn("VET ALERT: Pig #{} is expected to give birth in 3 days (Date: {})! Please prepare the farrowing crate.",
                    pigTag, targetDate);

            String alertMessage = String.format("VET ALERT: Pig #%s is expected to give birth in 3 days (Date: %s)! Please prepare the farrowing crate.",
                    pigTag, targetDate);
            alertPublisher.publishAlert(alertMessage);
        }

        log.info("Pregnancy monitor job finished. Generated {} alerts.", upcomingBirths.size());
    }
}