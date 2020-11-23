CREATE VIEW IF NOT EXISTS refined_types AS
WITH recursive
  refined_types_recur(type_name, refined_type_name) AS
  (SELECT type_name, refined_type_name FROM refined_types_base
   UNION
   SELECT a.type_name, b.refined_type_name FROM refined_types_base as a
   JOIN refined_types_recur as b ON a.refined_type_name = b.type_name)
SELECT * FROM refined_types_recur;

CREATE VIEW IF NOT EXISTS sdf_actors AS
SELECT DISTINCT targets.vertex_id,
       targets.type_name,
       sources.vertex_id as `constructor_vertex_id`,
       sources.type_name as `constructor_type_name`
  FROM vertexes AS targets
  JOIN edges ON targets.vertex_id = edges.target_vertex_id
  JOIN vertexes AS sources ON sources.vertex_id = edges.source_vertex_id
  JOIN refined_types AS et ON edges.type_name = et.refined_type_name
  JOIN refined_types AS st ON sources.type_name = st.refined_type_name
  JOIN refined_types AS tt ON targets.type_name = tt.refined_type_name
WHERE
  edges.source_vertex_port_id = 'constructed' AND
  edges.target_vertex_port_id = 'constructor' AND
  et.type_name = 'Constructs' AND
  tt.type_name = 'Process' AND
  st.type_name = 'SDFComb';

CREATE VIEW IF NOT EXISTS sdf_channels AS
SELECT DISTINCT targets.vertex_id,
       targets.type_name
  FROM vertexes AS targets
  JOIN edges ON targets.vertex_id = edges.target_vertex_id
  JOIN sdf_actors AS sources ON sources.vertex_id = edges.source_vertex_id
  JOIN refined_types AS et ON edges.type_name = et.refined_type_name
  JOIN refined_types AS st ON sources.type_name = st.refined_type_name
  JOIN refined_types AS tt ON targets.type_name = tt.refined_type_name
WHERE
  (et.type_name = 'Writes' OR et.type_name = 'Reads') AND
  tt.type_name = 'Signal';

CREATE VIEW IF NOT EXISTS sdf_topology_unsigned AS
SELECT DISTINCT 
    a.vertex_id as `actor_id`,
    c.vertex_id as `channel_id`,
    apropj.value,
    aprop.prop_id
  FROM properties AS aprop 
  JOIN json_each(aprop.prop_value) AS apropj 
  JOIN sdf_actors AS a ON a.`constructor_vertex_id` = aprop.vertex_id 
  JOIN edges AS e ON a.vertex_id = e.source_vertex_id 
  JOIN sdf_channels AS c ON c.vertex_id = e.target_vertex_id
  WHERE e.source_Vertex_port_id = apropj.key;

CREATE VIEW IF NOT EXISTS sdf_topology AS
SELECT `actor_id`,
       `channel_id`,
       CASE WHEN prop_id = 'consumption' THEN -value
       ELSE value
       END AS tokens
  FROM sdf_topology_unsigned;

CREATE VIEW IF NOT EXISTS `orderings` AS
SELECT DISTINCT v.`vertex_id`, v.`type_name`
  FROM vertexes AS v
  JOIN refined_types AS t ON v.type_name = t.refined_type_name
WHERE
  t.`type_name` = 'AbstractOrdering';

CREATE VIEW IF NOT EXISTS `tdma_mpsoc_processing_units` AS
SELECT DISTINCT s.`vertex_id`, s.`type_name`
  FROM vertexes AS s
  JOIN refined_types AS st ON s.type_name = st.refined_type_name
  JOIN edges AS e ON s.vertex_id = e.source_vertex_id
  JOIN refined_types AS et ON e.type_name = et.refined_type_name
  JOIN vertexes AS t ON t.vertex_id = e.target_vertex_id
  JOIN refined_types AS tt ON t.type_name = tt.refined_type_name
WHERE
  st.`type_name` = 'AbstractProcessingComponent' AND
  et.`type_name` = 'AbstractPhysicalConnection' AND
  tt.`type_name` = 'TimeDivisionMultiplexer';

CREATE VIEW IF NOT EXISTS `tdma_mpsoc_bus` AS
SELECT DISTINCT t.`vertex_id`, t.`type_name`
  FROM vertexes AS s
  JOIN refined_types AS st ON s.type_name = st.refined_type_name
  JOIN edges AS e ON s.vertex_id = e.source_vertex_id
  JOIN refined_types AS et ON e.type_name = et.refined_type_name
  JOIN vertexes AS t ON t.vertex_id = e.target_vertex_id
  JOIN refined_types AS tt ON t.type_name = tt.refined_type_name
WHERE
  st.`type_name` = 'AbstractProcessingComponent' AND
  et.`type_name` = 'AbstractPhysicalConnection' AND
  tt.`type_name` = 'TimeDivisionMultiplexer';

CREATE VIEW IF NOT EXISTS `tdma_mpsoc_bus_slots` AS
SELECT s.`vertex_id`, p.prop_value as `slots`
  FROM tdma_mpsoc_bus AS s
  JOIN properties AS p ON p.vertex_id = s.vertex_id
WHERE
  p.prop_id = 'slots';
