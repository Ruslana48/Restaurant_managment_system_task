USE restaurant;

DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS receipt;

CREATE TABLE order2
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    client_id   INT NOT NULL REFERENCES client,
    status_id   INT NOT NULL DEFAULT 1,
    total       INT,
    manager_id  INT,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (manager_id) REFERENCES client (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (status_id) REFERENCES restaurant.category (id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE receipt_has_dish
(
    receipt_id INT NOT NULL,
    dish_id    INT NOT NULL,
    count      INT NOT NULL DEFAULT 1,
    price      INT,

    FOREIGN KEY (receipt_id) REFERENCES receipt (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (dish_id) REFERENCES dish (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE status (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(32) NOT NULL UNIQUE
);

DROP TABLE category;
CREATE TABLE category(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(32) NOT NULL UNIQUE
);

CREATE TABLE dish
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL UNIQUE,
    category_id INT          NOT NULL,
    price       INT          NOT NULL,
    weight      INT          NOT NULL,
    description VARCHAR(1100),

    FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE cart_has_dish
(
    client_id INT NOT NULL,
    dish_id   INT NOT NULL UNIQUE,
    count     INT NOT NULL DEFAULT 1,

    FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (dish_id) REFERENCES dish (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE cart_has_dish2
(
    client_id INT NOT NULL,
    dish_id INT NOT NULL,
    count INT NOT NULL DEFAULT 1,

    FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (dish_id) REFERENCES dish (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE cart_has_dish3
(
    client_id INT NOT NULL,
    dish_id INT NOT NULL,
    count INT NOT NULL DEFAULT 1,

    FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (dish_id) REFERENCES dish (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO dish (name, category_id, price, weight, description)
    VALUE ('Fish burger', 2, 229, 350, 'Delicate bun, Caesar sauce, grilled salmon fillet, fresh iceberg and lots of vegetables!');
INSERT INTO dish (name, category_id, price, weight, description)
    VALUE ('Cheese burger', 2, 209, 500, '- Where\'s the roll? - And there is no roll, but there is mozzarella in crispy panko breadcrumbs, fresh vegetables and the juiciest cutlet.');
INSERT INTO dish (name, category_id, price, weight, description)
    VALUE ('Black burger', 2, 189, 400, 'Brutal black bun, BBQ sauce, some more mustard brutality, grilled chopped steak, some onions and vegetables, Voila.');

INSERT INTO dish (name, category_id, price, weight, description)
    VALUE ('Borsch', 2, 109, 400, 'Real Ukrainian Borsch with juicy rib and sour cream.');

INSERT INTO dish (name, category_id, price, weight, description)
    VALUE ('Caesar salad', 4, 179, 300, 'Grilled chicken fillet with classic Caesar and iceberg sauce, crispy bacon and aged Parmesan.');
INSERT INTO dish (name, category_id, price, weight, description)
    VALUE ('Greek salad', 4, 110, 300, 'Tomato, cucumber, sweet pepper, olives, olives, red onion, mixed salad, feta or tofu (your choice). Dressed with olive oil and Italian herbs.');
INSERT INTO dish (name, category_id, price, weight, description)
    VALUE ('Salmon salad', 4, 199, 300, 'Mix of lettuce, slices of the finest salmon, feta cheese, cherry tomatoes and olive dressing.');

INSERT INTO dish (name, category_id, price, weight, description)
    VALUE ('Caramel donut', 5, 43, 100, 'Donut with salted caramel and nuts.');
INSERT INTO dish (name, category_id, price, weight, description)
    VALUE ('Tiramisu', 5, 50, 90,'Tiramisu with Mascarpone (Thermomix or Conventional)');
INSERT INTO dish (name, category_id, price, weight, description)
    VALUE ('Napoleon', 5, 80, 120, 'Napoleon Dessert (mille-feuille) includes flaky puff pastry layered with creamy vanilla pastry cream.');

INSERT INTO dish (name, category_id, price, weight, description)
    VALUE ('Steak Premium', 6, 1600, 40, 'Choice Premium Boneless Steak (USA) is a type of steak made from the carcass of selected young Angus bulls. Prime marbling.');
INSERT INTO dish (name, category_id, price, weight, description)
    VALUE ('Beef wellington', 6, 800,300,'Wrapped in golden, buttery puff pastry and filled with deeply savory mushroom duxelles.');


INSERT INTO category (name) VALUE ('new');
INSERT INTO category (name) VALUE ('approved');
INSERT INTO category (name) VALUE ('cooking');
INSERT INTO category (name) VALUE ('delivering');
INSERT INTO category (name) VALUE ('received');

INSERT INTO category (name) VALUE ('Burgers');
INSERT INTO category (name) VALUE ('Soups');
INSERT INTO category (name) VALUE ('Salads');
INSERT INTO category (name) VALUE ('Desserts');
INSERT INTO category (name) VALUE ('Meat');

-- ==========TRIGGERS==============
DROP TRIGGER IF EXISTS receipt_has_dish_set_price;

DELIMITER //
CREATE DEFINER = CURRENT_USER TRIGGER restaurant.receipt_has_dish_set_price BEFORE INSERT ON receipt_has_dish FOR EACH ROW
BEGIN
    SET NEW.price = (SELECT price FROM dish WHERE dish.id = NEW.dish_id);
    UPDATE receipt
    SET total = NEW.price * NEW.count + ifnull(total, 0)
    WHERE id = NEW.receipt_id;
END//
DELIMITER ;

