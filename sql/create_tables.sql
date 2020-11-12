CREATE TABLE IF NOT EXISTS allowed_types (
  type_name TEXT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS super_types_base (
  type_name TEXT,
  super_type_name TEXT,
  FOREIGN KEY (type_name)
    REFERENCES allowed_types (type_name),
  FOREIGN KEY (super_type_name)
    REFERENCES allowed_types (type_name),
  PRIMARY KEY (type_name, super_type_name)
);

CREATE TABLE IF NOT EXISTS vertexes (
  vertex_id TEXT PRIMARY KEY,
  type_name TEXT NOT NULL,
  FOREIGN KEY (type_name)
    REFERENCES allowed_types (type_name)
);

CREATE TABLE IF NOT EXISTS ports (
  port_id TEXT NOT NULL,
  vertex_id TEXT NOT NULL,
  type_name TEXT NOT NULL,
  PRIMARY KEY (vertex_id, port_id),
  FOREIGN KEY (vertex_id)
    REFERENCES vertexes (vertex_id),
  FOREIGN KEY (type_name)
    REFERENCES allowed_types (type_name)
);

CREATE TABLE IF NOT EXISTS properties (
  prop_id TEXT NOT NULL,
  vertex_id TEXT NOT NULL,
  prop_value JSON NOT NULL,
  PRIMARY KEY (vertex_id, prop_id),
  FOREIGN KEY (vertex_id)
    REFERENCES vertexes (vertex_id)
);

CREATE TABLE IF NOT EXISTS edges (
  source_vertex_id TEXT NOT NULL,
  target_vertex_id TEXT NOT NULL,
  source_vertex_port_id TEXT,
  target_vertex_port_id TEXT,
  type_name TEXT NOT NULL,
  FOREIGN KEY (source_vertex_id)
    REFERENCES vertexes (vertex_id),
  FOREIGN KEY (target_vertex_id)
    REFERENCES vertexes (vertex_id),
  FOREIGN KEY (type_name)
    REFERENCES allowed_types (type_name),
  PRIMARY KEY (source_vertex_id, target_vertex_id, source_vertex_port_id, target_vertex_port_id)
);
