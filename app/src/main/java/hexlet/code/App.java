package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;


import java.util.concurrent.Callable;


@CommandLine.Command(
        name = "gendiff",
        description = """
                Compares two configuration files and shows a difference.
                      filepath1         path to first file
                      filepath2         path to second file
                """,
        mixinStandardHelpOptions = true, // <-- включает -h и -V
        version = "gendiff 1.0"
)
public class App implements Callable<Integer> {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    /**
     * Entry point of the CLI application logic.
     * <p>
     * This method is invoked by picocli after all command-line arguments
     * have been successfully parsed and validated.
     * <p>
     * It generates a difference between two configuration files and prints
     * the result to standard output.
     *
     * @return exit code of the application:
     *         {@code 0} if the difference was generated successfully,
     *         non-zero value otherwise
     * @throws Exception if an error occurs during file reading or comparison
     */
    @Override
    public Integer call() throws Exception {
        var result = Differ.generate(filepath1, filepath2, format);
        System.out.println(result);
        return 0;
    }

    @Option(
            names = {"-f", "--format"},
            description = "output format [default: stylish] (stylish, plain)",
            defaultValue = "stylish"
    )
    private String format;

    @CommandLine.Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @CommandLine.Parameters(index = "1", description = "path to second file")
    private String filepath2;

}
