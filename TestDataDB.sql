DROP TABLE IF EXISTS `test`.`part`;
CREATE TABLE `test`.`part`
(
  `idPart` INT         NOT NULL AUTO_INCREMENT,
  `part`   VARCHAR(45) UNIQUE   DEFAULT 'new_part',
  `isNeeded`  BOOLEAN     NOT NULL DEFAULT false,
  `amount`    VARCHAR(45) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idPart`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
INSERT INTO part (part, isNeeded, amount)
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
       ('Кейс для SSD', 0, 21),
       ('Видеокарта', 0, 5);