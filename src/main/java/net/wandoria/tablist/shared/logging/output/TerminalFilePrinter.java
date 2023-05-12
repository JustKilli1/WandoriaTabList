package net.wandoria.tablist.shared.logging.output;


import net.wandoria.tablist.shared.logging.LogCategory;
import net.wandoria.tablist.shared.logging.LogLevel;
import net.wandoria.tablist.shared.logging.LoggingUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TerminalFilePrinter extends FilePrinter{

    private static final String logFileName = "terminal.txt";

    public TerminalFilePrinter() {
        super(LogCategory.SYSTEM, logFileName);
    }

    @Override
    public String format(LogLevel logLevel, String loggerName, List<String> message, Exception ex) {
        String messageMSG = message == null && message.size() == 0 ? "" : LoggingUtils.getMessageStr(message, false);
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return "[" + currentDateTime.format(formatter) + "]" +
                messageMSG;
    }
}
