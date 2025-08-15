package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.data.UserData;
import com.phonebook.models.Contact;
import com.phonebook.models.User;
import com.phonebook.utils.MyDataProvider;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddContactTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
        app.getUser().clickOnLoginLink();
        // enter email
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        // click on LOGIN button
        app.getUser().clickOnLoginButton();
    }

    @Test
    public void addContactPositiveTest() {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new Contact()
                .setName("Bob")
                .setLastName("Smith")
                .setPhone("12625634342")
                .setEmail("bob_smith@mai.com")
                .setAddress("Berlin")
                .setDescription("Goalkeeper"));
        app.getContact().clickOnSaveButton();

        //assert new Contact by string
        Assert.assertTrue(app.getContact().isContactCreated("Bob"));

    }

    @Test(dataProvider = "addNewContact", dataProviderClass = MyDataProvider.class)
    public void addContactFromDataProviderTest(String name, String lastName, String phone,
                                               String email, String address, String description) {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new Contact()
                .setName(name)
                .setLastName(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(description));
        app.getContact().clickOnSaveButton();

        //assert new Contact by string
        Assert.assertTrue(app.getContact().isContactCreated(name));

    }

    @Test(dataProvider = "addNewContactFromCsvFile", dataProviderClass = MyDataProvider.class)
    public void addContactFromDataProviderWithCsvFileTest(Contact contact) {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(contact);
        app.getContact().clickOnSaveButton();

        //assert new Contact by string
        Assert.assertTrue(app.getContact().isContactCreatedByPhone(contact.getPhone()));

    }

    @AfterMethod (enabled = true)
    public void postCondition() {
        while (!app.getContact().isContactListEmpty()) {
            app.getContact().removeContact();
        }
    }

}