DELIMITER $$
CREATE TRIGGER after_insert_matiere AFTER INSERT ON matiere
FOR EACH ROW BEGIN
    INSERT INTO matiere_history SET operation = 'INSERT', timestamp = NOW(),
    new_data = JSON_OBJECT(
        'nummatiere', NEW.nummatiere,
        'design', NEW.design,
        'coef', NEW.coef
    );
END$$

DELIMITER ;

DELIMITER $$
CREATE TRIGGER after_update_matiere AFTER UPDATE ON matiere
FOR EACH ROW BEGIN
    INSERT INTO matiere_history SET operation = 'UPDATE', timestamp = NOW(),
    old_data = JSON_OBJECT(
        'nummatiere', OLD.nummatiere,
        'design', OLD.design,
        'coef', OLD.coef
    ),
    new_data = JSON_OBJECT(
        'nummatiere', NEW.nummatiere,
        'design', NEW.design,
        'coef', NEW.coef
    );
END$$

DELIMITER ;


DELIMITER $$
CREATE TRIGGER after_delete_matiere AFTER DELETE ON matiere
FOR EACH ROW BEGIN
    INSERT INTO matiere_history SET operation = 'DELETE', timestamp = NOW(),
    old_data = JSON_OBJECT(
        'nummatiere', OLD.nummatiere,
        'design', OLD.design,
        'coef', OLD.coef
    );
END$$

DELIMITER ;