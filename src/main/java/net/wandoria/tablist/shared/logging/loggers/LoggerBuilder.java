package net.wandoria.tablist.shared.logging.loggers;



import net.wandoria.tablist.shared.logging.ILogger;
import net.wandoria.tablist.shared.logging.output.IOutputPrinter;

import java.util.ArrayList;
import java.util.List;

public class LoggerBuilder {

    private String loggerName;
    private List<IOutputPrinter> printer;

    public LoggerBuilder(String loggerName) {
        this.loggerName = loggerName;
        printer = new ArrayList<>();
    }

    public LoggerBuilder addOutputPrinter(IOutputPrinter outputPrinter) {
        printer.add(outputPrinter);
        return this;
    }

    public ILogger build() {
        return new BaseLogger(loggerName, printer);
    }



}
