package com.opelt.magaluchallenge;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        System.out.println("Enter the file path or directory: ");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        Path filePath = Paths.get(path);
        if (filePath.toFile().isDirectory()) {
            for (File file : filePath.toFile().listFiles()) {
                processFile(file.toPath());
            }
        } else {
            processFile(filePath);
        }
    }

    private static void processFile(Path filePath) {
        Charset charset = StandardCharsets.UTF_8;
        try {
            List<String> lines = Files.readAllLines(filePath, charset);
            List<User> users = new ArrayList<>();
            for (String line : lines) {
                long userId = Long.parseLong(line.substring(0, 10));
                String name = line.substring(10, 55).trim();
                long orderId = Long.parseLong(line.substring(56, 66));
                long productId = Long.parseLong(line.substring(67, 75));
                double value = Double.parseDouble(line.substring(76, 87));
                Date date = new SimpleDateFormat("yyyyMMdd").parse(line.substring(88, 95));
                Product product = new Product(productId, value);
                boolean foundUser = false;
                for (User user : users) {
                    if (user.getUserId() == userId) {
                        foundUser = true;
                        boolean foundOrder = false;
                        for (Order order : user.getOrders()) {
                            if (order.getOrderId() == orderId) {
                                foundOrder = true;
                                order.getProducts().add(product);
                                break;
                            }
                        }
                        if (!foundOrder) {
                            Order order = new Order(orderId, 0, date);
                            order.getProducts().add(product);
                            user.getOrders().add(order);
                        }
                    }
                }
                if (!foundUser) {
                    Order order = new Order(orderId, 0, date);
                    order.getProducts().add(product);
                    User user = new User(userId, name);
                    user.getOrders().add(order);
                    users.add(user);

                }
            }

            for (User user : users) {
                for (Order order : user.getOrders()) {
                    double total = 0;
                    for (Product product : order.getProducts()) {
                        total = total + product.getValue();
                    }
                    order.setTotal(total);
                }

            }
            String filename = System.currentTimeMillis() + ".json";
            File output = new File("./" + filename);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(output, users);
            System.out.println("File [" + filename + "] generated");
        } catch (IOException | ParseException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
    }
}

