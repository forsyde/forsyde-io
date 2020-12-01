
DROP VIEW IF EXISTS `refined_types`;
DROP VIEW IF EXISTS `sdf_actors`;
DROP VIEW IF EXISTS `sdf_channels`;
DROP VIEW IF EXISTS `sdf_topology_unsigned`;
DROP VIEW IF EXISTS `sdf_topology`;
DROP VIEW IF EXISTS `orderings`;
DROP VIEW IF EXISTS `tdma_mpsoc_processing_units`;
DROP VIEW IF EXISTS `tdma_mpsoc_bus`;
DROP VIEW IF EXISTS `tdma_mpsoc_bus_slots`;
DROP VIEW IF EXISTS `wcet`;
DROP VIEW IF EXISTS `signal_wcct`;

CREATE VIEW refined_types AS
WITH recursive
  refined_types_recur(type_name, refined_type_name) AS
  (SELECT type_name, refined_type_name FROM refined_types_base
   UNION
   SELECT a.type_name, b.refined_type_name FROM refined_types_base as a
   JOIN refined_types_recur as b ON a.refined_type_name = b.type_name)
SELECT * FROM refined_types_recur;

CREATE VIEW sdf_actors AS
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
  et.type_name = 'Construction' AND
  tt.type_name = 'Process' AND
  st.type_name = 'SDFComb';

CREATE VIEW sdf_channels AS
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

CREATE VIEW sdf_topology_unsigned AS
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

CREATE VIEW sdf_topology AS
SELECT `actor_id`,
       `channel_id`,
       CASE WHEN prop_id = 'consumption' THEN -value
       ELSE value
       END AS tokens
  FROM sdf_topology_unsigned;

CREATE VIEW `orderings` AS
SELECT DISTINCT v.`vertex_id`, v.`type_name`
  FROM vertexes AS v
  JOIN refined_types AS t ON v.type_name = t.refined_type_name
WHERE
  t.`type_name` = 'AbstractOrdering';

CREATE VIEW `tdma_mpsoc_processing_units` AS
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

CREATE VIEW `tdma_mpsoc_bus` AS
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

CREATE VIEW `tdma_mpsoc_bus_slots` AS
SELECT s.`vertex_id`, p.prop_value as `slots`
  FROM tdma_mpsoc_bus AS s
  JOIN properties AS p ON p.vertex_id = s.vertex_id
WHERE
  p.prop_id = 'slots';

CREATE VIEW `wcet` AS
SELECT DISTINCT app.`vertex_id` as app_id,
                plat.`vertex_id` as plat_id,
                wcetp.prop_value as wcet_time
  FROM vertexes AS wcet
  JOIN properties AS wcetp ON wcetp.vertex_id = wcet.vertex_id
  JOIN edges AS eapp ON eapp.source_vertex_id = wcet.vertex_id
  JOIN vertexes AS app ON eapp.target_vertex_id = app.vertex_id
  JOIN edges AS eplat ON eplat.source_vertex_id = wcet.vertex_id
  JOIN vertexes AS plat ON eplat.target_vertex_id = plat.vertex_id
  JOIN refined_types AS wcett ON wcet.type_name = wcett.refined_type_name
  JOIN refined_types AS appt ON app.type_name = appt.refined_type_name
  JOIN refined_types AS platt ON plat.type_name = platt.refined_type_name
WHERE
  appt.type_name = 'Process' AND
  platt.type_name = 'AbstractProcessingComponent' AND
  wcett.type_name = 'WCET';  
  
CREATE VIEW `signal_wcct` AS
SELECT DISTINCT sender.`vertex_id` as sender_id,
                reciever.`vertex_id` as reciever_id,
                interconnect.`vertex_id` as interconnect_id,
                signal.`vertex_id` as signal_id,
				wcctp.prop_id,
                wcctp.prop_value
  FROM vertexes AS wcct
  JOIN properties AS wcctp ON wcctp.vertex_id = wcct.vertex_id
  JOIN edges AS esignal ON esignal.source_vertex_id = wcct.vertex_id
  JOIN vertexes AS signal ON esignal.target_vertex_id = signal.vertex_id
  JOIN edges AS esender ON esender.source_vertex_id = wcct.vertex_id
  JOIN vertexes AS sender ON esender.target_vertex_id = sender.vertex_id
  JOIN edges AS ereciever ON ereciever.source_vertex_id = wcct.vertex_id
  JOIN vertexes AS reciever ON ereciever.target_vertex_id = reciever.vertex_id
  JOIN edges AS einterconnect ON einterconnect.source_vertex_id = wcct.vertex_id
  JOIN vertexes AS interconnect ON einterconnect.target_vertex_id = interconnect.vertex_id
  JOIN refined_types AS signalt ON signal.type_name = signalt.refined_type_name
  JOIN refined_types AS sendert ON sender.type_name = sendert.refined_type_name
  JOIN refined_types AS recievert ON reciever.type_name = recievert.refined_type_name
  JOIN refined_types AS interconnectt ON interconnect.type_name = interconnectt.refined_type_name
WHERE
  signalt.type_name = 'Signal' AND
  sendert.type_name = 'AbstractProcessingComponent' AND
  recievert.type_name = 'AbstractProcessingComponent' AND
  interconnectt.type_name = 'AbstractCommunicationComponent';  
  
