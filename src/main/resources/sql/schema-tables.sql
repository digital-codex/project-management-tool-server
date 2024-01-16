-- SQL
-- s_forum.t_user definition

CREATE TABLE IF NOT EXISTS s_forum.t_user (
	id int8 NOT NULL,
	username varchar(63) NOT NULL,
	"password" varchar(63) NOT NULL,
	email varchar(255) NOT NULL,
	enabled bool NOT NULL DEFAULT false,
    inserted_by int8 NULL,
	inserted_at timestamp NULL,
	CONSTRAINT t_user_email_unq UNIQUE (email),
	constraint t_user_username_unq UNIQUE (username),
	CONSTRAINT t_user_pk PRIMARY KEY (id)
);

-- s_forum.t_verification_token definition

CREATE TABLE IF NOT EXISTS s_forum.t_verification_token (
	id int8 NOT NULL,
	"token" varchar(511) NOT NULL,
    expires_at timestamp NOT NULL,
	inserted_by int8 NULL,
	inserted_at timestamp NULL,
	CONSTRAINT t_verification_token_pk PRIMARY KEY (id)
);

-- s_forum.t_verification_token foreign keys

ALTER TABLE IF EXISTS s_forum.t_verification_token ADD CONSTRAINT t_verification_token_inserted_by_fk FOREIGN KEY (inserted_by) REFERENCES s_forum.t_user(id);

-- s_forum.t_refresh_token definition

CREATE TABLE IF NOT EXISTS s_forum.t_refresh_token (
    id int8 NOT NULL,
    "token" varchar(511) NOT NULL,
    inserted_by int8 NULL,
    inserted_at timestamp NULL,
    CONSTRAINT t_refresh_token_pk PRIMARY KEY (id)
);

-- s_forum.t_verification_token foreign keys

ALTER TABLE IF EXISTS s_forum.t_refresh_token ADD CONSTRAINT t_refresh_token_inserted_by_fk FOREIGN KEY (inserted_by) REFERENCES s_forum.t_user(id);

-- s_forum.t_forum definition

CREATE TABLE IF NOT EXISTS s_forum.t_forum (
	id int8 NOT NULL,
	"name" varchar(31) NOT NULL,
	description varchar(511) NOT NULL,
	inserted_by int8 NULL,
	inserted_at timestamp NULL,
	CONSTRAINT t_forum_pk PRIMARY KEY (id)
);

-- s_forum.t_forum foreign keys

ALTER TABLE IF EXISTS s_forum.t_forum ADD CONSTRAINT t_forum_inserted_by_fk FOREIGN KEY (inserted_by) REFERENCES s_forum.t_user(id);

-- s_forum.t_post definition

CREATE TABLE IF NOT EXISTS s_forum.t_post (
	id int8 NOT NULL,
    forum_id int8 NOT NULL,
	title varchar(255) NOT NULL,
	url varchar(2047) NOT NULL,
	inserted_by int8 NULL,
	inserted_at timestamp NULL,
    CONSTRAINT t_post_url_unq UNIQUE (url),
	CONSTRAINT t_post_pk PRIMARY KEY (id)
);

-- s_forum.t_post foreign keys

ALTER TABLE IF EXISTS s_forum.t_post ADD CONSTRAINT t_post_forum_id_fk FOREIGN KEY (forum_id) REFERENCES s_forum.t_forum(id);
ALTER TABLE IF EXISTS s_forum.t_post ADD CONSTRAINT t_post_inserted_by_fk FOREIGN KEY (inserted_by) REFERENCES s_forum.t_user(id);

-- s_forum.t_post_description definition

CREATE TABLE IF NOT EXISTS s_forum.t_post_description (
	shared_id int8 NOT NULL,
	description text NULL,
	inserted_by int8 NULL,
	inserted_at timestamp NULL,
	CONSTRAINT t_post_description_pk PRIMARY KEY (shared_id)
);


-- s_forum.t_post_description foreign keys

ALTER TABLE IF EXISTS s_forum.t_post_description ADD CONSTRAINT t_post_description_shared_id_fk FOREIGN KEY (shared_id) REFERENCES s_forum.t_post(id);
ALTER TABLE IF EXISTS s_forum.t_post_description ADD CONSTRAINT t_post_description_inserted_by_fk FOREIGN KEY (inserted_by) REFERENCES s_forum.t_user(id);

-- s_forum.t_vote definition

CREATE TABLE IF NOT EXISTS s_forum.t_vote (
	id int8 NOT NULL,
    post_id int8 NOT NULL,
	vote_type int4 NOT NULL,
	inserted_by int8 NULL,
	inserted_at timestamp NULL,
	CONSTRAINT t_vote_pk PRIMARY KEY (id)
);


-- s_forum.t_vote foreign keys

ALTER TABLE IF EXISTS s_forum.t_vote ADD CONSTRAINT t_vote_post_id_fk FOREIGN KEY (post_id) REFERENCES s_forum.t_post(id);
ALTER TABLE IF EXISTS s_forum.t_vote ADD CONSTRAINT t_vote_inserted_by_fk FOREIGN KEY (inserted_by) REFERENCES s_forum.t_user(id);
ALTER TABLE IF EXISTS s_forum.t_vote ADD CONSTRAINT t_vote_post_id_vote_type_inserted_by_unq UNIQUE (post_id, vote_type, inserted_by);

-- s_forum.t_comment definition

CREATE TABLE IF NOT EXISTS s_forum.t_comment (
	id int8 NOT NULL,
	description varchar(511) NOT NULL,
	post_id int8 NOT NULL,
	inserted_by int8 NULL,
	inserted_at timestamp NULL,
	CONSTRAINT t_comment_pk PRIMARY KEY (id)
);


-- s_forum.t_comment foreign keys

ALTER TABLE IF EXISTS s_forum.t_comment ADD CONSTRAINT t_comment_post_id_fk FOREIGN KEY (post_id) REFERENCES s_forum.t_post(id);
ALTER TABLE IF EXISTS s_forum.t_comment ADD CONSTRAINT t_comment_inserted_by_fk FOREIGN KEY (inserted_by) REFERENCES s_forum.t_user(id);