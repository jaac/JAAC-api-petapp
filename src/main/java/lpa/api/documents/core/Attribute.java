package lpa.api.documents.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Attribute {
    @Id
    private String id;

    private String name;

    @DBRef
    private AttributesCategory attributesCategory;

    public Attribute() {
    }

    public Attribute(String name, AttributesCategory attributesCategory) {
        this.name = name;
        this.attributesCategory = attributesCategory;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AttributesCategory getAttributesCategory() {
        return attributesCategory;
    }

    public void setAttributesCategory(AttributesCategory attributesCategory) {
        this.attributesCategory = attributesCategory;
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
        return (id.equals(((Attribute) obj).id));
    }


    @Override
    public String toString() {
        return "Attribute{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", attributesCategory=" + attributesCategory.getName() +
                '}';
    }

}
