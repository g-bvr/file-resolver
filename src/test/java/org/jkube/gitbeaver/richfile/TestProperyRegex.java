package org.jkube.gitbeaver.richfile;

import org.jkube.gitbeaver.util.VariableSubstitution;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

public class TestProperyRegex {

    public static final String DIR = "src/test/resources/richfile";

    @Test
    public void test() {
        Matcher m1 = VariableSubstitution.VAR_EXPRESSION.matcher("AB${CD }E");
        assertFalse(m1.matches());

        Matcher m2 = VariableSubstitution.VAR_EXPRESSION.matcher("AB${CD");
        assertFalse(m2.matches());

        Matcher m3 = VariableSubstitution.VAR_EXPRESSION.matcher("AB${CD}E");
        assertTrue(m3.matches());
        assertEquals("CD", m3.group(1));

        Matcher m4 = VariableSubstitution.VAR_EXPRESSION.matcher("AB${CD${EF}}");
        assertTrue(m4.matches());
        assertEquals("EF", m4.group(1));

    }

}
