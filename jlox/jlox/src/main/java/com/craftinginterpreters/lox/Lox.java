/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.craftinginterpreters.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {
    private static boolean hadError = false;

    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String source = new String(bytes, Charset.defaultCharset());
        run(source);

        // Indicate an error in the exit code.
        if (hadError) {
            System.exit(65);
        }
    }

    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (;;) {
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null) break;
            run(line);
            hadError = false; // Reset error flag for each line in the prompt.
        }
    }

    private static void run(String source) {
        LoxScanner scanner = new LoxScanner(source);
        List<LoxToken> tokens = scanner.scanTokens();

        // For now, just print the tokens.
        for (LoxToken token : tokens) {
            System.out.println(token);
        }
    }

    static void error(int line, String message) {
        report(line, "", message);
    }

    private static void report(int line, String where, String message) {
        System.err.println("[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }

    // Placeholder LoxScanner class
    private static class LoxScanner {
        public LoxScanner(String source) {
        }

        public List<LoxToken> scanTokens() {
            // TODO: Implement tokenization logic
            return null;
        }
    }

    // Placeholder LoxToken class
    private static class LoxToken {
        // TODO: Define token properties and methods
    }
}

