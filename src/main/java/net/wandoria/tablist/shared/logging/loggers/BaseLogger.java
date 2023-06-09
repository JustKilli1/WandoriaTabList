package net.wandoria.tablist.shared.logging.loggers;

import net.wandoria.tablist.shared.logging.ILogger;
import net.wandoria.tablist.shared.logging.LogLevel;
import net.wandoria.tablist.shared.logging.output.IOutputPrinter;

import java.util.List;

public class BaseLogger implements ILogger {

    private String loggerName;
    private List<IOutputPrinter> outputPrinter;

    public BaseLogger(String loggerName, List<IOutputPrinter> outputPrinter) {
        this.loggerName = loggerName;
        this.outputPrinter = outputPrinter;
    }

    public BaseLogger(String loggerName, IOutputPrinter outputPrinter) {
        this(loggerName, List.of(outputPrinter));
    }

    @Override
    public void log(LogLevel logLevel, List<String> message, Exception ex) {
        notifyPrinter(logLevel, message, ex);
    }

    @Override
    public void log(LogLevel logLevel, List<String> message) {
        log(logLevel, message, null);
    }

    @Override
    public void log(LogLevel logLevel, String message, Exception ex) {
        log(logLevel, List.of(message), ex);
    }

    @Override
    public void log(LogLevel logLevel, String message) {
        log(logLevel, List.of(message), null);
    }

    @Override
    public void log(LogLevel logLevel, Exception ex) {
        log(logLevel, (List<String>) null, ex);
    }

    @Override
    public String getName() {
        return loggerName;
    }
    /**
     * Notify all present OutputPrinter
     * @param level Level of the Message
     * @param message The Message that gets send to the OutputPrinter
     * */
    private void notifyPrinter(LogLevel level, List<String> message, Exception ex) {
        outputPrinter.forEach(printer -> printer.print(level, printer.format(level, loggerName, message, ex)));
    }

}
