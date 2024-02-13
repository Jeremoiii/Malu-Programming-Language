import buffer.StringBuffer;
import editor.Editor;
import frontend.AST.Statement;
import frontend.Parser.Parser;
import runtime.Environment;
import runtime.Interpreter;
import utils.ObjectPrinter;

import java.nio.file.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
//        run("./src/scripts/test.malu");
        new Editor();
    }

//    public static void run(String filename) {
//        new StringBuffer();
//        Parser parser = new Parser();
//        Environment env = Environment.createGlobalEnvironment();
//
//        String input = "";
//        try {
//            input = new String(Files.readAllBytes(Paths.get(filename)));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Statement program = parser.produceAST(input);
//        Interpreter.evaluate(program, env);
//    }
}