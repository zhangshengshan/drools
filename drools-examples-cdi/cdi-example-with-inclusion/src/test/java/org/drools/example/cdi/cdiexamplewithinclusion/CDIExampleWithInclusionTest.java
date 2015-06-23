/*
 * Copyright 2015 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package org.drools.example.cdi.cdiexamplewithinclusion;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class CDIExampleWithInclusionTest {

    private static final String NL = System.getProperty("line.separator");

    @Test
    public void testGo() {
        Weld w = new Weld();
        WeldContainer wc = w.initialize();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);

        CDIExampleWithInclusion bean = wc.instance().select(CDIExampleWithInclusion.class).get();
        bean.go(ps);

        ps.close();

        String actual = new String(baos.toByteArray());
        String expected = "" +
                          "Dave: Hello, HAL. Do you read me, HAL?" + NL +
                          "HAL: Dave. I read you." + NL +
                          "Dave: Open the pod bay doors, HAL." + NL +
                          "HAL: I'm sorry, Dave. I'm afraid I can't do that." + NL;
        assertEquals(expected, actual);

        w.shutdown();
    }
}
