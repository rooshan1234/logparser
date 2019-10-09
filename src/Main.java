import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entry.LogEntry;
import factories.ObjectMapperFactory;
import processor.LogEntryParser;
import processor.LogEntryReader;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Logger;

public class Main {

    public static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        ObjectMapper mapper = ObjectMapperFactory.create();
        LogEntryParser parser = new LogEntryParser(mapper);

        // used to hold all parsed data
        HashMap<String, HashSet<LogEntry>> data;

        try (BufferedReader reader = new BufferedReader(new FileReader(new File("log.json")))) {
            // create a new reader with logging disabled
            LogEntryReader logEntryReader = new LogEntryReader(reader, parser);

            // read the data into a map for printing
            data = logEntryReader.read();
        } catch (IOException io) {
            // raised from BufferedReader, FileReader or new File()
            // we cannot continue if we cannot initialize the buffer or the file is not found
            throw new RuntimeException(io);
        }

        for (Map.Entry<String, HashSet<LogEntry>> entry : data.entrySet()) {
            // print out the unique file count per extension
            System.out.println(entry.getKey() + ": " + entry.getValue().size());
        }
    }
}
