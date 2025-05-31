CREATE TABLE IF NOT EXISTS config (
    id VARCHAR(32) NOT NULL,
    classifier VARCHAR(64) NOT NULL,
    classifier_value VARCHAR(128) NOT NULL,
    config_name VARCHAR(128) NOT NULL,
    config_value TEXT NOT NULL,
    created_by VARCHAR(64) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_by VARCHAR(64) NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(classifier, classifier_value, config_name)
);

CREATE INDEX IF NOT EXISTS config_classifier_idx ON config (classifier, classifier_value);

CREATE TABLE IF NOT EXISTS tenant (
    id VARCHAR(32) NOT NULL,
    name VARCHAR(127) NOT NULL,
    description TEXT NOT NULL,
    code VARCHAR(6) NOT NULL,
    incremental_id SERIAL,
    created_by VARCHAR(64) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_by VARCHAR(64) NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(name),
    UNIQUE(code)
);

CREATE INDEX IF NOT EXISTS tenant_updated_at_idx ON tenant (updated_at DESC);

CREATE TABLE IF NOT EXISTS user_info (
    id VARCHAR(32) NOT NULL,
    tenant_id VARCHAR(32) REFERENCES tenant (id),
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64) NOT NULL,
    enabled BOOLEAN NOT NULL,
    created_by VARCHAR(64) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_by VARCHAR(64) NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY(id)
);

CREATE INDEX IF NOT EXISTS user_info_tenant_id_idx ON user_info (tenant_id ASC, updated_at DESC);