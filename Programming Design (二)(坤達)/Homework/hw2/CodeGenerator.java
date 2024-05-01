import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;


public class CodeGenerator {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.println("請輸入mermaid檔案名稱");           
        }
        else {
            // get input
            String fileName = args[0];
            String mermaidCode = "";

            FileReader mermaidCodeReader = new FileReader();
            // Parser mermaidParser = new Parser();

            mermaidCode = mermaidCodeReader.Read(fileName);


            // for (String line : lines){
            //     System.out.println(line);
            // }

            Map<String, List<String>> classInfo = new HashMap<>();
            classInfo = Parser.MermaidCodeParser(mermaidCode);

            // for (String key : classInfo.keySet()){
            //     System.out.print("public class " + key + " {\n");
            //     for (String output : classInfo.get(key)){
            //         System.out.println(output);
            //     }
            //     System.out.println("}");
            // }

            mermaidCodeReader.Write(classInfo);
        }
    }
}

class FileReader{

    public String Read(String fileName) {

		// 讀取文件
        // System.out.println("File name: " + fileName); 

        String mermaidCode = "";

        try {
            mermaidCode = Files.readString(Paths.get(fileName));
            return mermaidCode;
        } catch (IOException e) {
            System.err.println("無法讀取文件 " + fileName);
            e.printStackTrace();
            return "this code is not mine, I don't what to return";
        }
    }
    
    public void Write(Map<String, List<String>> classInfo){

        // Write to files
        for (String className : classInfo.keySet()){
            try {
                String fileName = className + ".java";
                File file = new File(fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                bw.write("public class " + className + " {\n");
                for (String content : classInfo.get(className)){
                    bw.write(content);
                    bw.newLine(); // Add a new line after writing each content
                }
                bw.write("}");
                bw.close(); // Close the BufferedWriter after writing all content
                // System.out.println("Java class has been generated: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Parser {

    public static boolean IsMethod(String str){
        return str.contains("(");
    }

    public static String SymbolConvertion(char chr){
        return chr == '+' ? "public" : "private";
    }

    public static String ConvertFirstCharToLower(String str){
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    public static String GetParseString(String line){

        int symbolIndex = 0, len = line.length();

        if (line.contains("+")){
            symbolIndex = line.indexOf("+");
        }else if (line.contains("-")){
            symbolIndex = line.indexOf("-");
        }

        return line.substring(symbolIndex, len).trim();

    }

    public static String GetReturnType(String parseString){
        
        int len = parseString.length();
        if (parseString.substring(parseString.indexOf(")") + 1, len).trim().length() == 0){
            return "void";
        }else{
            return parseString.substring(parseString.indexOf(")") + 1, len).trim();
        }
        

    }

    public static String AttributeParser(String parseString){

        int len = parseString.length();
        String symbol = SymbolConvertion(parseString.charAt(0));

        String variableType = parseString.substring(1, parseString.indexOf(" "));
        String variableName = parseString.substring(parseString.indexOf(" ") + 1, len).trim();

        return  "    " + symbol + " " + variableType + " " + variableName + ";";

    }

    public static String SetMethod(String parseString){

        String returnType = GetReturnType(parseString);

        String methodDeclaration = parseString.substring(1, parseString.indexOf(")")+1);

        String symbol = SymbolConvertion(parseString.charAt(0));
        String variableName = parseString.substring(parseString.indexOf("set") + 3, parseString.indexOf("("));
        variableName = ConvertFirstCharToLower(variableName);

        return "    " + symbol + " " + returnType + " " + methodDeclaration + " {\n"
        + "        this." + variableName + " = " + variableName + ";\n" + "    }";


    }

    public static String GetMethod(String parseString){

        String returnType = GetReturnType(parseString);

        String methodDeclaration = parseString.substring(1, parseString.indexOf(')')+1);

        String symbol = SymbolConvertion(parseString.charAt(0));
        String variableName = parseString.substring(parseString.indexOf("get") + 3, parseString.indexOf("("));
        variableName = ConvertFirstCharToLower(variableName);

        return "    " + symbol + " " + returnType + " " + methodDeclaration + " {\n"
        + "        return " + variableName + ";\n" + "    }";

    }

    public static String GeneralMethod(String parseString){

        String returnType = GetReturnType(parseString);

        String methodDeclaration = parseString.substring(1, parseString.indexOf(')')+1);
        String methodStatement = "";

        switch(returnType){
            case "int" : 
                methodStatement = "{return 0;}";
                break;
            case "boolean" : 
                methodStatement = "{return false;}";
                break;
            case "void" :
                methodStatement = "{;}";
                break;
            case "String" :
                methodStatement = "{return \"\";}";
                break;

        }

        String symbol = SymbolConvertion(parseString.charAt(0));
        return "    " + symbol + " " + returnType + " " + methodDeclaration + " " + methodStatement;

    }

    public static String MethodParser(String parseString){

        if (parseString.contains("get")){
            return GetMethod(parseString);
        }else if (parseString.contains("set")){
            return SetMethod(parseString);
        }else{
            return GeneralMethod(parseString);
        }

    }

    public static Map<String, List<String>> MermaidCodeParser(String classDiagram){

        // StringBuilder javaCoBuilder = new StringBuilder();
        String[] tempLines = classDiagram.split("\n");
        String[] lines = Arrays.copyOfRange(tempLines, 1, tempLines.length);
        String defultClassName = "";
        
        // ArrayList<String> classesName = new ArrayList<String>(3);

        Map<String, List<String>> classInfo = new HashMap<>();

        for (String line : lines){
            // System.out.println(line);

            if (line.contains("class ")){

                line = line.trim();
                // System.out.println(line);

                String className = line.substring(line.indexOf("class") + 5, line.length());
                className = className.trim();
                // System.out.println(className);

                if (line.contains("{")){
                    className = className.substring(0, className.indexOf(" "));
                    defultClassName =  className;
                }
                // System.out.println(className);

                List<String> classMenbers = new ArrayList<String>();
                classInfo.put(className, classMenbers);

            }else if (line.contains("+") || line.contains("-")){

                // =================================================================================================

                // System.out.println(classInfo.keySet());
                
                boolean isFindClass = false;
                
                for (String className : classInfo.keySet()){

                    // System.out.println(className);
                    // System.out.println(line);
                    // System.out.println(line.trim().indexOf(className));

                    if (line.trim().indexOf(className) == 0){
                        isFindClass = true;

                        // System.out.println(className);
                        // System.out.println(line);

                        String parseString = GetParseString(line);
                        // System.out.println(parseString);

                        String classMember = IsMethod(parseString) ? MethodParser(parseString) : AttributeParser(parseString);
                        classInfo.get(className).add(classMember);
                        // System.out.println(classMember);
                    }
                }

                if (isFindClass == false){
                    String parseString = GetParseString(line);
                    
                    String classMember = IsMethod(parseString) ? MethodParser(parseString) : AttributeParser(parseString);
                    // System.out.println(defultClassName + "    " + parseString + "    " + classMember); // 你媽 分析出來也是對的
                    // System.out.println(defultClassName); // defultClassName 是對的
                    // System.out.println(defultClassName + "    " + classInfo.containsKey(defultClassName)); // key 就在裡面 幹
                    classInfo.get(defultClassName).add(classMember); // ***就這行在搞 tc5有兩個東西死add不進去 fuck***
                    // System.out.println("add completed."); //媽的 就add completed 為甚麼沒有add 進去
                }
                
            }
        }
        return classInfo;
    }
}