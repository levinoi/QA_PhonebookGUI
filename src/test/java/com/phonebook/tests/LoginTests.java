package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.phonebook.core.ApplicationManager.softAssert;

public class LoginTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition (){
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    @Parameters({"email", "password"})
    @Test (priority = 1)
    public void loginPositiveTest(String email,String password){

        app.getUser().clickOnLoginLink();
        // enter email
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(email)
                .setPassword(password));
        app.getUser().clickOnLoginButton();
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }


    @Test (priority = 2, groups = "empty")
    public void loginNegativeWithoutEmailTest(){

        app.getUser().clickOnLoginLink();
        app.getUser().clearEmailField();
        // enter email
        app.getUser().fillLoginRegisterForm(new User()
                .setPassword("Qay123$ddd"));
        app.getUser().clearEmailField();
        app.getUser().clickOnLoginButton();
        softAssert.assertTrue(app.getUser().isAlertPresent());
        softAssert.assertTrue(app.getUser().isErrorMessagePresent());
        softAssert.assertAll();

    }

    @Test (priority = 3, groups = "empty")
    public void loginNegativeWithoutPasswordTest(){

        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail("enigma2025@mail.com"));
        app.getUser().clearPasswordField();
        app.getUser().clickOnLoginButton();
        softAssert.assertTrue(app.getUser().isAlertPresent());
        softAssert.assertTrue(app.getUser().isErrorMessagePresent());
        softAssert.assertAll();
        app.getUser().clearEmailField();
    }


}
