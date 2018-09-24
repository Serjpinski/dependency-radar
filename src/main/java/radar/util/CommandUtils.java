package radar.util;

import java.io.*;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class CommandUtils {

    public static void execute(String command) {

        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(command);
            Process process = processBuilder.start();
            Executors.newSingleThreadExecutor().submit(new StreamGobbler(process.getInputStream(), System.out::println));
            process.waitFor();
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class StreamGobbler implements Runnable {

        private InputStream inputStream;
        private Consumer<String> consumer;

        StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(consumer);
        }
    }
}
