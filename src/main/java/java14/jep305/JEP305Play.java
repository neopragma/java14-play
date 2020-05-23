package java14.jep305;

public class JEP305Play {

    String assignStringIfObjectIsString_IfElse(Object obj) {
        if (obj instanceof String s) {
            return s;
        } else {
            return "object is not a String";
        }
    }

    String assignStringIfObjectIsString_TernaryOperator(Object obj) {
        return obj instanceof String s ? s : "object is not a String";
    }

    Pokemon assignInstanceOrDefault(Object obj) {
        return obj instanceof Pokemon p ? p : new NullPokemon();
    }
}
