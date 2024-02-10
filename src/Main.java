import frontend.AST.AST;
import frontend.AST.Statement;
import frontend.Parser.Parser;
import runtime.Environment;
import runtime.Interpreter;
import runtime.RuntimeValue;
import utils.ObjectPrinter;

import java.nio.file.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        run("./Malu/script.malu");
    }

    public static void run(String filename) {
        Parser parser = new Parser();
        Environment env = Environment.createGlobalEnvironment();

        String input = "malu()";
//        try {
//            input = new String(Files.readAllBytes(Paths.get(filename)));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Statement program = parser.produceAST(input);
        RuntimeValue result = Interpreter.evaluate(program, env);

        System.out.println(ObjectPrinter.deserializeObjectToString(program));
        System.out.println(ObjectPrinter.deserializeObjectToString(result));
    }
}