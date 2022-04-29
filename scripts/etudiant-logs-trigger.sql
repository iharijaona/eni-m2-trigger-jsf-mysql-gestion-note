DELIMITER $$
CREATE TRIGGER after_insert_etudiant AFTER INSERT ON etudiant
FOR EACH ROW BEGIN
    INSERT INTO etudiant_history SET operation = 'INSERT', timestamp = NOW(),
    new_data = JSON_OBJECT(
        'numetudiant', NEW.numetudiant,
        'nom', NEW.nom,
        'moyenne', NEW.moyenne
    );
END$$

DELIMITER ;

DELIMITER $$
CREATE TRIGGER after_update_etudiant AFTER UPDATE ON etudiant
FOR EACH ROW BEGIN
    INSERT INTO etudiant_history SET operation = 'UPDATE', timestamp = NOW(),
    old_data = JSON_OBJECT(
        'numetudiant', OLD.numetudiant,
        'nom', OLD.nom,
        'moyenne', OLD.moyenne
    ),
    new_data = JSON_OBJECT(
        'numetudiant', NEW.numetudiant,
        'nom', NEW.nom,
        'moyenne', NEW.moyenne
    );
END$$

DELIMITER ;


DELIMITER $$
CREATE TRIGGER after_delete_etudiant AFTER DELETE ON etudiant
FOR EACH ROW BEGIN
    INSERT INTO etudiant_history SET operation = 'DELETE', timestamp = NOW(),
    old_data = JSON_OBJECT(
        'numetudiant', OLD.numetudiant,
        'nom', OLD.nom,
        'moyenne', OLD.moyenne
    );
END$$

DELIMITER ;