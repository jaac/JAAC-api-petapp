package lpa.api.documents.core;

import org.junit.Assert;
import org.junit.Test;

public class AttributesCategoryTest {

    @Test
    public void create() {
        AttributesCategory petAtt = new AttributesCategory("Color de ojos");
        Assert.assertEquals("Color de ojos", petAtt.getName());
    }

}
