INSERT INTO categories (id, name)
VALUES (1, 'Fruits'),
       (2, 'Vegetables'),
       (3, 'Dairy Products'),
       (4, 'Chips and Namkeen'),
       (5, 'Tea Coffee and More'),
       (6, 'Cold Drinks and Juices');


INSERT INTO products (name, price, description, category_id)
VALUES ('Fresh Bananas', 0.58, 'Ripe, organic bananas. Perfect for snacking or smoothies.', 1),
       ('Red Apples', 1.29, 'Crisp and juicy red apples sourced from Washington.', 1),
       ('Spinach Bunch', 0.99, 'Fresh green spinach. Ideal for cooking and salads.', 2),
       ('Tomatoes', 1.79, 'Vine-ripened tomatoes rich in flavor.', 2),
       ('Whole Milk - 1 Gallon', 3.49, 'Pasteurized whole milk from grass-fed cows.', 3),
       ('Amul Butter - 500g', 2.99, 'Rich and creamy butter from Amul.', 3),
       ('Lays Classic Salted', 1.19, 'Classic salted potato chips in 100g pack.', 4),
       ('Haldiram’s Aloo Bhujia', 1.49, 'Spicy and crunchy Indian snack.', 4),
       ('Tata Tea Gold - 250g', 2.89, 'Premium quality strong Indian tea.', 5),
       ('Minute Maid Orange Juice - 1L', 3.25, 'Refreshing orange juice with no added sugar.', 6);
