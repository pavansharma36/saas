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
    PRIMARY KEY(id)
);

CREATE INDEX IF NOT EXISTS config_classifier_index ON config (classifier, classifier_value);

CREATE TABLE IF NOT EXISTS galaxy (
    id VARCHAR(32) NOT NULL,
    name VARCHAR(128) NOT NULL,
    description TEXT NOT NULL,
    token VARCHAR(256) NOT NULL,
    url_scheme VARCHAR(16) NOT NULL,
    url_host VARCHAR(128) NOT NULL,
    url_port INTEGER NOT NULL,
    sub_domain VARCHAR(128) NOT NULL,
    created_by VARCHAR(64) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_by VARCHAR(64) NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY(id)
);

CREATE INDEX galaxy_token_index ON galaxy USING HASH (token);