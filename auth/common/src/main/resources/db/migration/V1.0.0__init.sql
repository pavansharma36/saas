CREATE TABLE IF NOT EXISTS user_account (
    id VARCHAR(32) NOT NULL,
    username VARCHAR(64) NOT NULL,
    password VARCHAR(256) NOT NULL,
    account_non_expired BOOLEAN NOT NULL,
    account_non_locked BOOLEAN NOT NULL,
    admin BOOLEAN NOT NULL,
    credentials_expire_at TIMESTAMP WITH TIME ZONE NOT NULL,
    created_by VARCHAR(64) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_by VARCHAR(64) NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    attempt_id VARCHAR(32) DEFAULT NULL,
    PRIMARY KEY(id),
    UNIQUE (username)
);

CREATE INDEX IF NOT EXISTS user_account_attempt_id_idx ON user_account (attempt_id) WHERE attempt_id IS NOT NULL;

CREATE TABLE IF NOT EXISTS resource_config (
    id VARCHAR(32) NOT NULL,
    name VARCHAR(127) NOT NULL,
    description VARCHAR(512) NOT NULL,
    supported_actions VARCHAR(64)[] NOT NULL,
    created_by VARCHAR(64) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_by VARCHAR(64) NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY(id),
    UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS user_group (
    id VARCHAR(32) NOT NULL,
    tenant_id VARCHAR(32),
    name VARCHAR(127) NOT NULL,
    description VARCHAR(512) NOT NULL,
    created_by VARCHAR(64) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_by VARCHAR(64) NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY(id),
    UNIQUE (tenant_id, name)
);

CREATE TABLE IF NOT EXISTS user_group_resources (
    id VARCHAR(32) NOT NULL,
    user_group_id VARCHAR(32) NOT NULL REFERENCES user_group(id),
    resource_name VARCHAR(127) NOT NULL,
    allowed_actions VARCHAR(64)[] NOT NULL,
    created_by VARCHAR(64) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_by VARCHAR(64) NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(user_group_id, resource_name)
);

CREATE TABLE IF NOT EXISTS user_group_map (
    id VARCHAR(32) NOT NULL,
    user_id VARCHAR(32) NOT NULL REFERENCES user_account(id),
    user_group_id VARCHAR(32) NOT NULL REFERENCES user_group(id),
    created_by VARCHAR(64) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(user_id, user_group_id)
);