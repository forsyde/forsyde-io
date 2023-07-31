CREATE VIEW IF NOT EXISTS `tasks` AS
SELECT `vertex_id`, v.`type_name`, t.`super_type_name` FROM _vertexes AS v
  JOIN super_types AS t ON v.type_name = t.super_type_name
WHERE
  v.`type_name` = t.`type_name` AND
  t.`super_type_name` = 'AbstractOrdering'
