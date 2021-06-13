package parsing;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;

public class OutputLoader {
    /**
     * un obiect de tipul OutputLoader.
     */
    private static final OutputLoader OUTPUT_LOADER = new OutputLoader();
    /**
     * @return instanta
     */
    public static OutputLoader getInstance() {
        return OUTPUT_LOADER;
    }

    /**
     * @param path   calea catre fisierul de output
     * @param output clasa care contine o lista cu consumatori si una cu
     *               distibuitori in stadiul final , dupa simualre
     * @throws IOException
     */
    public void putData(
            final String path,
            final Output output)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(path), output);
    }
}
