package com.epam.task4.commands;

import com.epam.task4.commands.util.Command;
import com.epam.task4.services.HistoryService;

public class GetLastFiveOrdersCommand implements Command {
    private HistoryService historyService;

    public GetLastFiveOrdersCommand(HistoryService historyService) {
        this.historyService = historyService;
    }

    @Override
    public void execute() {
        System.out.println(historyService.getHistoryAsString());
    }
}
