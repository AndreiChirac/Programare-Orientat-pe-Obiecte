package parsing;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public final class InputLoader {
    /**
     * un obiect de tipul InputLoader.
     */
    private static final InputLoader INPUT_LOADER = new InputLoader();

    private InputLoader() {

    }

    /**
     * @return instnata
     */
    public static InputLoader getInstance() {
        return INPUT_LOADER;
    }

    /**
     * @param path calea catre fisierul de input
     * @return se realizeaza parsarea efectiva
     */
    public Input readData(final String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(path), Input.class);
    }

}
