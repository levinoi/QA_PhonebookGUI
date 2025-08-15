package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import com.phonebook.models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends BaseHelper {


    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void clickOnSaveButton() {
        click(By.cssSelector(".add_form__2rsm2 button"));
    }

    public void fillContactForm(Contact contact) {
        //enter name
        type(By.xpath("//input[1]"), contact.getName());
//enter lastname
        type(By.xpath("//input[2]"), contact.getLastName());
//enter phone
        type(By.xpath("//input[3]"), contact.getPhone());
//enter email
        type(By.xpath("//input[4]"), contact.getEmail());
//enter address
        type(By.xpath("//input[5]"), contact.getAddress());
//enter description
        type(By.xpath("//input[6]"), contact.getDescription());
    }

    public void clickOnAddLink() {
        click(By.cssSelector("[href='/add']"));
    }

    public boolean isContactCreated(String text) {
        if (verifyText(text, By.cssSelector("h2"))) return true;
        return false;
    }

    public void removeContact() {
        // click on card
        click(By.cssSelector(".contact-item_card__2SOIM"));
        // click on remove button
        click(By.xpath("//button[.='Remove']"));
    }

    public int sizeOfContacts(){
        if (isElementPresent(By.cssSelector(".contact-item_card__2SOIM"))) {
            return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
        }
            return 0;
    }

    public boolean isContactListEmpty() {
        return isElementPresent(By.xpath("//h1[.=' No Contacts here!']"));
    }

    public boolean isContactCreatedByPhone(String phone) {
        if(verifyText(phone,By.cssSelector("h3")))
        return true;
        return false;
    }
}


