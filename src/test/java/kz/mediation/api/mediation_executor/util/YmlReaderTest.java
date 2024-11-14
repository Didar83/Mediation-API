package kz.mediation.api.mediation_executor.util;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

class YmlReaderTest {

    @Test
    void readYml() {
        // Given
        Path path = null;
        Map<String, Object> stringMapFromYamlFile = null;
        String pathSeparatorChar = File.separator;
        try {
            path = Paths.get("src" + pathSeparatorChar + "main" + pathSeparatorChar + "resources" + pathSeparatorChar + "communication" + pathSeparatorChar + "MediationStrings.yml");
            var fileContent = Files.readAllBytes(path);
            var yaml = new Yaml();
            stringMapFromYamlFile = yaml.load(new String(fileContent));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // When
        if (stringMapFromYamlFile == null) {
            throw new RuntimeException("Yaml file is empty");
        } else {
            stringMapFromYamlFile.forEach((key, value) -> {
                System.out.println(key + ": " + value);
            });
        }

        // Then

    }

}
