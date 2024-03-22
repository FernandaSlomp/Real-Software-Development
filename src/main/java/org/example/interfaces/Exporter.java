package org.example.interfaces;

import org.example.model.SummaryStatistics;

public interface Exporter {
    String export(SummaryStatistics summaryStatistics);
}
