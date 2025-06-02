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
