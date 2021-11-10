package org.skife.waffles;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.junit.jupiter.api.Test;

import java.io.File;

public class ReallyExecutableJarMojoTest extends AbstractMojoTestCase {

    @Test
    public void testSomething()
            throws Exception {
        File pom = getTestFile("src/test/resources/unit/simple/pom.xml");
        assertNotNull(pom);
        assertTrue(pom.exists());

        ReallyExecutableJarMojo myMojo = (ReallyExecutableJarMojo) lookupMojo("really-executable-jar", pom);
        assertNotNull(myMojo);
        myMojo.execute();

    }
}
