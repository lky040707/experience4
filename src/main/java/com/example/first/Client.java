package com.example.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.println("\nPersonal Address Book");
                System.out.println("1. Add Contact");
                System.out.println("2. Delete Contact");
                System.out.println("3. Update Contact");
                System.out.println("4. View Contacts");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");

                int option = scanner.nextInt();
                scanner.nextLine(); // 消耗掉nextInt后的换行符

                switch (option) {
                    case 1:
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter address: ");
                        String address = scanner.nextLine();
                        System.out.print("Enter phone number: ");
                        String phone = scanner.nextLine();
                        out.println("ADD " + name + " " + address + " " + phone);
                        System.out.println(in.readLine());
                        break;
                    case 2:
                        System.out.print("Enter id to delete: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // 消耗掉nextInt后的换行符
                        out.println("DELETE " + id);
                        System.out.println(in.readLine());
                        break;
                    case 3:
                        System.out.print("Enter id to update: ");
                        id = scanner.nextInt();
                        scanner.nextLine(); // 消耗掉nextInt后的换行符
                        System.out.print("Enter new name: ");
                        name = scanner.nextLine();
                        System.out.print("Enter new address: ");
                        address = scanner.nextLine();
                        System.out.print("Enter new phone number: ");
                        phone = scanner.nextLine();
                        out.println("UPDATE " + id + " " + name + " " + address + " " + phone);
                        System.out.println(in.readLine());
                        break;
                    case 4:
                        out.println("VIEW");
                        System.out.println("Contacts:");
                        String line;
                        while ((line = in.readLine()) != null) {
                            if (line.equals("END_OF_CONTACTS")) {
                                break; // 如果接收到结束标志，则跳出循环
                            }
                            System.out.println(line);
                        }
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}