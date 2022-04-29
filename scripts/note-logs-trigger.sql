DROP TRIGGER IF EXISTS after_insert_note;

DELIMITER $$
CREATE TRIGGER after_insert_note AFTER INSERT ON note
FOR EACH ROW BEGIN
    INSERT INTO note_history SET operation = 'INSERT', timestamp = NOW(),
    new_data = JSON_OBJECT(
        'note', NEW.note,
        'numetudiant', NEW.numetudiant,
        'nummatiere', NEW.nummatiere
    );
    UPDATE etudiant SET moyenne = (SELECT ROUND((SELECT sum(note.note) FROM note WHERE note.numetudiant = 2) / (SELECT SUM(matiere.coef) FROM matiere), 2) as moyenne)
    WHERE numetudiant = NEW.numetudiant;
END$$

DELIMITER ;

DROP TRIGGER IF EXISTS after_update_note;

DELIMITER $$
CREATE TRIGGER after_update_note AFTER UPDATE ON note
FOR EACH ROW BEGIN
    INSERT INTO note_history SET operation = 'UPDATE', timestamp = NOW(),
    old_data = JSON_OBJECT(
        'note', OLD.note,
        'numetudiant', OLD.numetudiant,
        'nummatiere', OLD.nummatiere
    ),
    new_data = JSON_OBJECT(
        'note', NEW.note,
        'numetudiant', NEW.numetudiant,
        'nummatiere', NEW.nummatiere
    );
    UPDATE etudiant SET moyenne = (SELECT ROUND((SELECT sum(note.note) FROM note WHERE note.numetudiant = 2) / (SELECT SUM(matiere.coef) FROM matiere), 2) as moyenne)
    WHERE numetudiant = OLD.numetudiant;
END$$

DELIMITER ;

DROP TRIGGER IF EXISTS after_delete_note;

DELIMITER $$
CREATE TRIGGER after_delete_note AFTER DELETE ON note
FOR EACH ROW BEGIN
    INSERT INTO note_history SET operation = 'DELETE', timestamp = NOW(),
    old_data = JSON_OBJECT(
        'note', OLD.note,
        'numetudiant', OLD.numetudiant,
        'nummatiere', OLD.nummatiere
    );
    UPDATE etudiant SET moyenne = (SELECT ROUND((SELECT sum(note.note) FROM note WHERE note.numetudiant = 2) / (SELECT SUM(matiere.coef) FROM matiere), 2) as moyenne)
    WHERE numetudiant = OLD.numetudiant;
END$$

DELIMITER ;