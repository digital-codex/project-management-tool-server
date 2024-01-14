-- SQL

CREATE INDEX IF NOT EXISTS t_verification_token_token_idx ON s_forum.t_verification_token USING btree (token);
CREATE INDEX IF NOT EXISTS t_post_forum_id_idx ON s_forum.t_post USING btree (forum_id);

-- ROLLBACK

DROP INDEX IF EXISTS t_verification_token_token_idx;
DROP INDEX IF EXISTS t_post_forum_id_idx;