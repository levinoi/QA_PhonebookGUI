package com.phonebook.utils;

import com.phonebook.models.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDataProvider {
    @DataProvider
    public Iterator<Object[]> addNewContactFromCsvFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/ ContactData.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{new Contact().setName(split[0]).setLastName(split[1]).setPhone(split[2]).
                    setEmail(split[3]).setAddress(split[4]).setDescription(split[5])});
            line = reader.readLine();
        }

        return list.iterator();
    }


    @DataProvider
    public Iterator<Object[]> addNewContact() {
        List<Object[]> list = new ArrayList<>();
        list.add((new Object[]{"Oliver", "Kan", "1234567890", "kan@mail.com", "Berlin", "goalkeeper"}));
        list.add((new Object[]{"Oliver1", "Kan", "123452326790", "kan1@mail.com", "Berlin", "goalkeeper"}));
        list.add((new Object[]{"Oliver2", "Kan", "12345234246790", "kan2@mail.com", "Berlin", "goalkeeper"}));
        return list.iterator();
    }
}
