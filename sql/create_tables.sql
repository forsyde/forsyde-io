-- the reason for the underscore in these tables is to
-- conform to souffle datalog conventions so that it may be
-- used one day.

CREATE TABLE IF NOT EXISTS _allowed_types (
  type_name TEXT PRIMARY KEY
);

CREATE VIEW IF NOT EXISTS allowed_types AS
SELECT * FROM _allowed_types;

CREATE TABLE IF NOT EXISTS _refined_types_base (
  type_name TEXT,
  refined_type_name TEXT,
  FOREIGN KEY (type_name)
    REFERENCES _allowed_types (type_name),
  FOREIGN KEY (refined_type_name)
    REFERENCES _allowed_types (type_name),
  PRIMARY KEY (type_name, refined_type_name)
);

CREATE VIEW IF NOT EXISTS refined_types_base AS
SELECT * FROM _refined_types_base;

CREATE TABLE IF NOT EXISTS _vertexes (
  vertex_id TEXT PRIMARY KEY,
  type_name TEXT NOT NULL,
  FOREIGN KEY (type_name)
    REFERENCES _allowed_types (type_name)
);

CREATE VIEW IF NOT EXISTS vertexes AS
SELECT * FROM _vertexes;

CREATE TABLE IF NOT EXISTS _ports (
  port_id TEXT NOT NULL,
  vertex_id TEXT NOT NULL,
  type_name TEXT NOT NULL,
  PRIMARY KEY (vertex_id, port_id),
  FOREIGN KEY (vertex_id)
    REFERENCES _vertexes (vertex_id),
  FOREIGN KEY (type_name)
    REFERENCES _allowed_types (type_name)
);

CREATE VIEW IF NOT EXISTS ports AS
SELECT * FROM _ports;

CREATE TABLE IF NOT EXISTS _properties (
  prop_id TEXT NOT NULL,
  vertex_id TEXT NOT NULL,
  prop_value JSON NOT NULL,
  PRIMARY KEY (vertex_id, prop_id),
  FOREIGN KEY (vertex_id)
    REFERENCES _vertexes (vertex_id)
);

CREATE VIEW IF NOT EXISTS properties AS
SELECT * FROM _properties;

CREATE TABLE IF NOT EXISTS _edges (
  source_vertex_id TEXT NOT NULL,
  target_vertex_id TEXT NOT NULL,
  source_vertex_port_id TEXT,
  target_vertex_port_id TEXT,
  type_name TEXT NOT NULL,
  FOREIGN KEY (source_vertex_id)
    REFERENCES _vertexes (vertex_id),
  FOREIGN KEY (target_vertex_id)
    REFERENCES _vertexes (vertex_id),
  FOREIGN KEY (type_name)
    REFERENCES _allowed_types (type_name),
  PRIMARY KEY (source_vertex_id, target_vertex_id, source_vertex_port_id, target_vertex_port_id)
);

CREATE VIEW IF NOT EXISTS edges AS
SELECT * FROM _edges;

