DROP TABLE IF EXISTS `test`.`product`;
CREATE TABLE `test`.`product`
(
  `idProduct` INT         NOT NULL AUTO_INCREMENT,
  `product`   VARCHAR(45) NOT NULL DEFAULT 'new_product',
  `isNeeded`  BIT         NOT NULL DEFAULT 0,
  `amount`    VARCHAR(45) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idProduct`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
INSERT INTO product (product, isNeeded, amount)
VALUES ('Монитор', 1, 33),
       ('Мышка', 1, 10),
       ('Камера', 0, 8),
       ('Жесткий диск HDD', 0, 9),
       ('Жесткий диск SSD', 1, 5),
       ('Оперативная память 2Гб', 1, 3),
       ('Оперативная память 4Гб', 0, 1),
       ('Оперативная память 8Гб', 0, 5),
       ('Клавиатура', 1, 6),
       ('Звуковая карта', 0, 7),
       ('Дисковод', 0, 1),
       ('Блок питания', 1, 6),
       ('Куллер ЦП', 1, 8),
       ('Процессор', 1, 12),
       ('Сетевая карта', 0, 12),
       ('Колонки 2.0', 0, 2),
       ('Колонки 5.1', 0, 1),
       ('Наушники', 0, 20),
       ('Корпус', 1, 14),
       ('USB карта', 0, 7),
       ('Кейса для SSD', 0, 21),
       ('Видеокарта', 0, 5);