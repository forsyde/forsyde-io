CREATE VIEW IF NOT EXISTS sdf_actors AS
SELECT DISTINCT targets.vertex_id as 'actor_id',
       targets.type_name as 'actor_type',
       sources.vertex_id as 'constructor_id',
       sources.type_name as 'constructor_type'
  FROM vertexes AS targets
  JOIN edges ON targets.vertex_id = edges.target_vertex_id
  JOIN vertexes AS sources ON sources.vertex_id = edges.source_vertex_id
WHERE
  edges.source_vertex_port_id = 'constructed' AND
  edges.target_vertex_port_id = 'constructor' AND
  edges.type_name = 'Constructs' AND
  targets.type_name = 'Process' AND
  sources.type_name = 'SDFComb';

CREATE VIEW IF NOT EXISTS sdf_channels AS
SELECT DISTINCT targets.vertex_id as 'channel_id',
       targets.type_name as 'channel_type'
  FROM vertexes AS targets
  JOIN edges ON targets.vertex_id = edges.target_vertex_id
  JOIN sdf_actors AS sources ON sources.actor_id = edges.source_vertex_id
WHERE
  (edges.type_name = 'Writes' OR edges.type_name = 'Reads') AND
  targets.type_name = 'Signal';

CREATE VIEW IF NOT EXISTS sdf_topology_unsigned AS
SELECT DISTINCT a.actor_id, c.channel_id, apropj.value, aprop.prop_id
  FROM properties AS aprop JOIN json_each(aprop.prop_value) AS apropj 
  JOIN sdf_actors AS a ON a.constructor_id = aprop.vertex_id 
  JOIN edges AS e ON a.actor_id = e.source_vertex_id 
  JOIN sdf_channels AS c ON c.channel_id = e.target_vertex_id
  WHERE e.source_Vertex_port_id = apropj.key;

CREATE VIEW IF NOT EXISTS sdf_topology AS
SELECT actor_id,
       channel_id,
       CASE WHEN prop_id = 'consumption' THEN -value
       ELSE value
       END AS tokens
  FROM sdf_topology_unsigned;
