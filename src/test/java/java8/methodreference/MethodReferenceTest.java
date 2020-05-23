package java8.methodreference;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Playing with StackOverflow question:
 * https://stackoverflow.com/questions/61968869/assert-throws-doesnt-work-for-method-reference
 *
 * Examples here suggest it will work:
 * https://www.programcreek.com/java-api-examples/?class=org.junit.jupiter.api.Assertions&method=assertThrows
 */
public class MethodReferenceTest {

    @Test
    public void throwsNPE_usingLambdaExpression() {
        @SuppressWarnings("OptionalAssignedToNull") Optional<String> nullOptional = null;
        assertThrows(NullPointerException.class, () -> nullOptional.isEmpty());
    }

    @Test
    public void noException_usingLambdaExpression() {
        @SuppressWarnings("OptionalAssignedToNull") Optional<String> stringOptional = Optional.of("notnull");
        assertFalse(stringOptional.isEmpty());
    }

    // This one does not appear to work as expected
    @Test
    public void throwsNPE_usingMethodReference() {
        @SuppressWarnings("OptionalAssignedToNull") Optional<String> nullOptional = null;
        assertThrows(NullPointerException.class, nullOptional::isEmpty);
    }

    // Testing Johannes Link's hypothesis that the object has to be initialized before the assertion.
    @Test
    public void throwsNPE_usingMethodReferenceWithinLambda() {
        assertThrows(NullPointerException.class, () -> {
            @SuppressWarnings("OptionalAssignedToNull") Optional<String> nullOptional = null;
            assertThrows(NullPointerException.class, nullOptional::isEmpty);
        });
    }

    @Test
    public void noException_usingMethodReference() {
        @SuppressWarnings("OptionalAssignedToNull") Optional<String> stringOptional = Optional.of("notnull");
        assertFalse(stringOptional::isEmpty);
    }

    @Test
    public void detectNPE_usingTryCatchWorkaround() {
        @SuppressWarnings("OptionalAssignedToNull") Optional<String> nullOptional = null;
        try {
            assertTrue(nullOptional::isEmpty);
        } catch (NullPointerException npe) {
            // PASS
        } catch (Exception e) {
            fail("Unexpected Exception thrown: " + e);
        }
    }

}
