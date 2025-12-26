package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;


import java.util.concurrent.Callable;


@CommandLine.Command(
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.",
        mixinStandardHelpOptions = true, // <-- включает -h и -V
        version = "gendiff 1.0"
)
public class App implements Callable<Integer> {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        return 0;
    }
}
