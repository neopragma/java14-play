package java14.jep305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.*;

public class JEP305PlayTest {

    private JEP305Play testSubject;

    @BeforeEach
    void instantiateTestSubject() {
        testSubject = new JEP305Play();
    }

    @Test
    void itReturnsStringValueIfObjectIsAStringUsingIfElse() {
        assertEquals("alpha", testSubject.assignStringIfObjectIsString_IfElse("alpha"));
    }

    @Test
    void itReturnsMessageIfObjectIsNotAStringUsingIfElse() {
        assertEquals("object is not a String", testSubject.assignStringIfObjectIsString_IfElse(new Date()));
    }

    @Test
    void itReturnsStringValueIfObjectIsAStringUsingTernaryOperator() {
        assertEquals("alpha", testSubject.assignStringIfObjectIsString_TernaryOperator("alpha"));
    }

    @Test
    void itReturnsMessageIfObjectIsNotAStringUsingTernaryOperator() {
        assertEquals("object is not a String", testSubject.assignStringIfObjectIsString_TernaryOperator(new Date()));
    }

    @Test
    void itReturnsInstanceIfObjectIsTheExpectedType() {
        assertTypeMatches(Pokemon.class, testSubject.assignInstanceOrDefault(new Pikachu()));
    }

    @Test
    void itReturnsDefaultInstanceIfObjectIsNotTheExpectedType() {
        assertTypeMatches(NullPokemon.class, testSubject.assignInstanceOrDefault(new Date()));
    }

    @Test
    void verifyCustomAssertionWorksWhenTheTypesDoNotMatch() {
        Error thrown = assertThrows(AssertionFailedError.class, () -> {
            assertTypeMatches(java.lang.String.class, new Pikachu());
        });
        assertEquals("Expected object to be of type java.lang.String but was of type java14.jep305.Pikachu",
                thrown.getMessage());
    }

    @Test
    void verifyCustomAssertionWorksForNullReference() {
        Error thrown = assertThrows(AssertionFailedError.class, () -> {
            assertTypeMatches(java.lang.String.class, null);
        });
        assertEquals("Expected object to be of type java.lang.String but was null",
                thrown.getMessage());
    }

    private void assertTypeMatches(Class expectedType, Object actualInstance) {
        if (actualInstance == null || !expectedType.isAssignableFrom(actualInstance.getClass())) {
            StringBuilder message = new StringBuilder("Expected object to be of type ");
            message.append(expectedType.getName());
            message.append(" but was ");
            message.append(actualInstance == null ? "null" : "of type " + actualInstance.getClass().getName());
            throw new AssertionFailedError(message.toString());
        }
    }
}
