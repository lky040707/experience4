package com.example.second;

import java.util.List;

public interface ContactDAO {
    void addContact(Contact contact);
    boolean deleteContact(int id);
    boolean updateContact(Contact contact);
    List<Contact> getAllContacts();
}
