package com.example.second;

import java.util.List;
import java.util.Scanner;

public class AddressBookUI {
    private Scanner scanner;
    private ContactService contactService;

    public AddressBookUI(ContactService contactService) {
        this.contactService = contactService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\nPersonal Address Book");
            System.out.println("1. Add Contact");
            System.out.println("2. Delete Contact");
            System.out.println("3. Update Contact");
            System.out.println("4. View Contacts");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // 消耗掉换行符

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    deleteContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    viewContacts();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addContact() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        Contact contact = new Contact(name, address, phone);
        contactService.addContact(contact);
        System.out.println("Contact added successfully.");
    }

    private void deleteContact() {
        System.out.print("Enter contact ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // 消耗掉换行符

        boolean result = contactService.deleteContact(id);
        if (result) {
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    private void updateContact() {
        System.out.print("Enter contact ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // 消耗掉换行符

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new address: ");
        String address = scanner.nextLine();
        System.out.print("Enter new phone number: ");
        String phone = scanner.nextLine();

        Contact contact = new Contact(name, address, phone);
        contact.setId(id);

        boolean result = contactService.updateContact(contact);
        if (result) {
            System.out.println("Contact updated successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    private void viewContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
        } else {
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    public static void main(String[] args) {
        ContactService contactService = new ContactServiceImpl(new ContactDAOImpl());
        AddressBookUI ui = new AddressBookUI(contactService);
        ui.run();
    }
}
