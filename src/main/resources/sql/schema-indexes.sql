-- SQL
-- s_forum.t_verification_token indexes

CREATE INDEX IF NOT EXISTS t_verification_token_token_idx ON s_forum.t_verification_token USING btree (token);

-- s_forum.t_post indexes

CREATE INDEX IF NOT EXISTS t_post_forum_id_idx ON s_forum.t_post USING btree (forum_id);