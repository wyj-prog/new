import java.awt.*;

public class SCread {
    public static Color isJavaKey(String str) {
        Color color = Color.black;
        for (String s : javaPink) {
            if (str.trim().equals(s)) {
                color = Color.pink;
                break;
            }
        }
        if (color == Color.black) {
            for (String s : javaOrange) {
                if (str.trim().equals(s)) {
                    color = Color.orange;
                    break;
                }
            }
        }
        return color;
    }

    public static Color isPyKey(String str) {
        Color color = Color.black;
        for (String s : pyPink) {
            if (str.trim().equals(s)) {
                color = Color.pink;
                break;
            }
        }
        if (color == Color.black) {
            for (String s : pyOrange) {
                if (str.trim().equals(s)) {
                    color = Color.orange;
                    break;
                }
            }
        }
        return color;
    }

    public static Color isCppKey(String str) {
        Color color = Color.black;
        for (String s : cppPink) {
            if (str.trim().equals(s)) {
                color = Color.pink;
                break;
            }
        }
        if (color == Color.black) {
            for (String s : cppOrange) {
                if (str.trim().equals(s)) {
                    color = Color.orange;
                    break;
                }
            }
        }
        return color;
    }

    public static String[] javaPink = {"@author", "@version", "@date", "/**", "*", "*/", "abstract", "default", "goto", "switch", "do", "if",
            "package", "synchronized", "break", "implements", "private", "this", "else", "import", "protected", "throw", "throws",
            "case", "extends", "instanceof", "public", "transient", "catch", "return", "final", "interface", "try", "class", "finally",
            "static", "void", "const", "native", "strictfp", "volatile", "continue", "for", "new", "super", "while", "assert", "enum"};

    public static String[] javaOrange = {"void", "byte", "char", "short", "Short", "int", "long", "float", "double",
            "boolean", "String", "Float", "Double", "Long", "Integer", "Boolean"};

    public static String[] pyPink = {"and", "@author", "@version", "@date", "/**", "*", "*/", "or", "as", "assert", "async",
            "await", "class", "def", "del", "if", "elif", "else", "try", "except", "finally", "raise", "False",
            "True", "for", "while", "break", "continue", "import", "from", "global", "in", "is", "lambda", "None",
            "nonlocal", "not", "pass", "return", "with", "yield"};

    public static String[] pyOrange = {"Numbers", "String", "List", "Tuple", "Dictionary", "Set", "int", "float",
            "complex", "bool"};

    public static String[] cppPink = {"asm", "@author", "@version", "@date", "/**", "*", "*/", "auto", "break", "case", "catch",
            "class", "const", "const_cast", "continue", "default", "delete", "do", "dynamic_cast", "else", "enum", "explicit", "export",
            "for", "friend", "false", "goto", "if", "inline", "mutable", "namespace", "new", "operator", "private", "protected",
            "public", "register", "reinterpret_cast", "return", "sizeof", "static", "static_cast", "struct", "switch", "template", "this",
            "throw", "true", "try", "typedef", "typeid", "typename", "union", "unsigned", "using", "virtual", "void", "volatile",
            "wchar_t"};

    public static String[] cppOrange = {"bool", "char", "int", "float", "double", "void", "unsigned", "signed",
            "short", "long"};

}
