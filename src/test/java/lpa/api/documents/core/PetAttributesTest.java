package lpa.api.documents.core;

import org.junit.Assert;
import org.junit.Test;

public class PetAttributesTest {
    @Test
    public void create() {
        AttributesCategory attributesCategory = new AttributesCategory("Color de pelo");
        Attribute attribute = new Attribute("Marron");
        PetAttributes petAttributes = new PetAttributes(attributesCategory, attribute, "atwk4h5");

        Assert.assertEquals("PetAttributes{id='null', " +
                "attributesCategory=AttributesCategory{id='null', " +
                "name='Color de pelo'}, attribute=Attribute{id='null', name='Marron'}, " +
                "petId='atwk4h5'}", petAttributes.toString());
    }

    @Test
    public void getPetId() {
        AttributesCategory attributesCategory = new AttributesCategory("Color de pelo");
        Attribute attribute = new Attribute("Marron");
        PetAttributes petAttributes = new PetAttributes(attributesCategory, attribute, "atwk4h5");
        Assert.assertEquals("atwk4h5", petAttributes.getPetId());
    }

    @Test
    public void setPetId() {
        PetAttributes petAttributes = new PetAttributes();
        petAttributes.setPetId("atwk4h5");
        Assert.assertEquals("atwk4h5", petAttributes.getPetId());
    }

    @Test
    public void getAttribute() {
        AttributesCategory attributesCategory = new AttributesCategory("Color de pelo");
        Attribute attribute = new Attribute("Marron");
        PetAttributes petAttributes = new PetAttributes(attributesCategory, attribute, "atwk4h5");
        Assert.assertEquals("Marron", petAttributes.getAttribute().getName());
    }

    @Test
    public void setAttribute() {
        PetAttributes petAttributes = new PetAttributes();
        petAttributes.setAttribute(new Attribute("Marron"));
        Assert.assertEquals("Marron", petAttributes.getAttribute().getName());
    }

    @Test
    public void getAttributeCategory() {
        AttributesCategory attributesCategory = new AttributesCategory("Color de pelo");
        Attribute attribute = new Attribute("Marron");
        PetAttributes petAttributes = new PetAttributes(attributesCategory, attribute, "atwk4h5");
        Assert.assertEquals("Color de pelo", petAttributes.getAttributesCategory().getName());
    }

    @Test
    public void setAttributeCategory() {
        PetAttributes petAttributes = new PetAttributes();
        petAttributes.setAttributesCategory(new AttributesCategory("Color de pelo"));
        Assert.assertEquals("Color de pelo", petAttributes.getAttributesCategory().getName());
    }

}