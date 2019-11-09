package lpa.api.documents.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PetAttributes {
    @Id
    private String id;
    @DBRef
    private AttributesCategory attributesCategory;
    @DBRef
    private Attribute attribute;
    private String petId;

    public PetAttributes() {
        //For Framework
    }

    public PetAttributes(AttributesCategory attributesCategory, Attribute attribute, String petId) {
        this.attributesCategory = attributesCategory;
        this.attribute = attribute;
        this.petId = petId;
    }


    public String getId() {
        return id;
    }

    public AttributesCategory getAttributesCategory() {
        return attributesCategory;
    }

    public void setAttributesCategory(AttributesCategory attributesCategory) {
        this.attributesCategory = attributesCategory;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return (id.equals(((PetAttributes) obj).id));
    }

    @Override
    public String toString() {
        return "PetAttributes{" +
                "id='" + id + '\'' +
                ", attributesCategory=" + attributesCategory +
                ", attribute=" + attribute +
                ", petId='" + petId + '\'' +
                '}';
    }
}
