import buffer.StringBuffer;
import editor.Editor;
import frontend.AST.Statement;
import frontend.Parser.Parser;
import runtime.Environment;
import runtime.Interpreter;
import server.RuntimeServer;
import utils.ObjectPrinter;

import java.nio.file.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        new RuntimeServer(8080);
        new client.Main();
    }
}