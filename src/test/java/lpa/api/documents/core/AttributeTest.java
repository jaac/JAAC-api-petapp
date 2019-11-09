package lpa.api.documents.core;

import org.junit.Assert;
import org.junit.Test;


public class AttributeTest {
    @Test
    public void create() {
        Attribute att = new Attribute("Gris claro");
        Assert.assertEquals("Gris claro", att.getName());
    }
}
