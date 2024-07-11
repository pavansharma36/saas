DROP TABLE IF EXISTS config;

CREATE TABLE config (
    id VARCHAR(32),
    classifier VARCHAR(64),
    classifier_value VARCHAR(128),
    config_name VARCHAR(128),
    config_value TEXT,
    created_by VARCHAR(64),
    created_at TIMESTAMP WITH TIME ZONE,
    updated_by VARCHAR(64),
    updated_at TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY(id)
);

CREATE INDEX IF NOT EXISTS config_classifier_index ON config (classifier, classifier_value);