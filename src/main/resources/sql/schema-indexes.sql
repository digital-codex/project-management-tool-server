-- SQL
-- s_forum.t_verification_token indexes

CREATE INDEX IF NOT EXISTS t_verification_token_token_idx ON s_forum.t_verification_token USING btree (token);

-- s_forum.t_post indexes

CREATE INDEX IF NOT EXISTS t_post_forum_id_idx ON s_forum.t_post USING btree (forum_id);
CREATE INDEX IF NOT EXISTS t_post_inserted_by_id_idx ON s_forum.t_post USING btree (inserted_by);

-- s_forum.t_vote indexes

CREATE INDEX IF NOT EXISTS t_vote_post_id_idx ON s_forum.t_vote USING btree (post_id);

-- s_forum.t_comment indexes

CREATE INDEX IF NOT EXISTS t_comment_post_id_idx ON s_forum.t_comment USING btree (post_id);