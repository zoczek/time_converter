package main;

import java.time.LocalTime;

public class CommandLineOutputTimeWriter implements OutputTimeWriter {
    @Override
    public void writeOutput(LocalTime output) {
        System.out.println(output);
    }
}
