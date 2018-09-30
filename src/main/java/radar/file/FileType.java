package radar.file;

import radar.file.processors.NoOpFileProcessor;
import radar.file.processors.PomFileProcessor;

public enum FileType {

    JAVA, PY, JS, // Source code files
    POM, // Maven
    APP_YML, BOOT_YML, // Spring Boot/Cloud
    ;

    private static final NoOpFileProcessor NO_OP_FILE_PROCESSOR = new NoOpFileProcessor();
    private static final PomFileProcessor POM_FILE_PROCESSOR = new PomFileProcessor();

    public FileProcessor getFileProcessor() {

        switch (this) {
            case JAVA: return NO_OP_FILE_PROCESSOR;
            case PY: return NO_OP_FILE_PROCESSOR;
            case JS:  return NO_OP_FILE_PROCESSOR;
            case POM: return POM_FILE_PROCESSOR;
            case APP_YML: return NO_OP_FILE_PROCESSOR;
            case BOOT_YML: return NO_OP_FILE_PROCESSOR;
            default: throw new RuntimeException("Processor not implemented for file type: " + this);
        }
    }
}
