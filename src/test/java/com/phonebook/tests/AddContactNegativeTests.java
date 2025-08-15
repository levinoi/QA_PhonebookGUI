package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.models.Contact;
import com.phonebook.models.User;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactNegativeTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail("manuel@gm.com")
                .setPassword("Manuel1234$"));
        app.getUser().clickOnLoginButton();
    }

    @Test
    public void addContactWithInvalidPhoneTest() {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new Contact()
                .setName("Karl")
                .setLastName("Adam")
                .setPhone("123")
                .setEmail("karl@gm.com")
                .setAddress("Berlin")
                .setDescription("goalkeeper"));
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isAlertPresent());
    }
    @Test
    public void addContactWithInvalidEmailTest() {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new Contact()
                .setName("Karl")
                .setLastName("Adam")
                .setPhone("1234567890")
                .setEmail("karlgm.com")
                .setAddress("Berlin")
                .setDescription("goalkeeper"));
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isAlertPresent());
    }
}
