package lpa.api.dtos;

import lpa.api.documents.core.UserProfile;

public class UserProfileDto  {

    private String name;

    private String lastName;

    private String mobile;

    private String address1;

    private String address2;

    private String city;

    private String zipCode;

    private String country;

    private String image;

    private String description;


    public UserProfileDto() {
        //For the Framework
    }

    public UserProfileDto(String name, String image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public UserProfileDto(String name, String lastName, String mobile, String address1, String address2, String city, String zipCode, String country, String image, String description) {
        this.name = name;
        this.lastName = lastName;
        this.mobile = mobile;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
        this.image = image;
        this.description = description;
    }

    public UserProfileDto(UserProfile userProfile){
        this.name = userProfile.getName();
        this.lastName = userProfile.getLastName();
        this.mobile = userProfile.getMobile();
        this.address1 = userProfile.getAddress1();
        this.address2 = userProfile.getAddress2();
        this.city = userProfile.getCity();
        this.zipCode = userProfile.getZipCode();
        this.country = userProfile.getCountry();
        this.image = userProfile.getImage().getImageUrl();
        this.description = userProfile.getDescription();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "UserProfileDto{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
