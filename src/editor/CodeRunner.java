package editor;

import frontend.AST.Statement;
import frontend.Parser.Parser;
import runtime.Environment;
import runtime.Interpreter;
import buffer.StringBuffer;

public class CodeRunner {
    public CodeRunner(String input) {
        new StringBuffer();
        Parser parser = new Parser();
        Environment env = Environment.createGlobalEnvironment();
        Statement program = parser.produceAST(input);
        Interpreter.evaluate(program, env);
    }

    public String getOutput() {
        return StringBuffer.getInstance().getBuffer().toString();
    }
}
