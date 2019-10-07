package converters;

import com.fasterxml.jackson.databind.util.StdConverter;
import utils.StringUtils;

public class FilenameConverter extends StdConverter<String, String> {

    /**
     * Used to convert the incoming filename back into the file name if it as an extension.
     *
     * @param value the name of the file.
     * @return the input filename, if it has extension, null otherwise.
     */
    @Override
    public String convert(String value) {
        return StringUtils.fileNameExtensionExtractor(value).isPresent() ? value : null;
    }
}
