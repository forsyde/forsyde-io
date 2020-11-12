CREATE VIEW IF NOT EXISTS super_types AS
WITH recursive
  super_types_recur(type_name, super_type_name) AS
  (SELECT type_name, super_type_name FROM super_types_base
   UNION
   SELECT a.type_name, b.super_type_name FROM super_types_base as a
   JOIN super_types_recur as b ON a.super_type_name = b.type_name)
SELECT * FROM super_types_recur;
