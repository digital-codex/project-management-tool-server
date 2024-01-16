-- ROLLBACK
-- s_forum.t_comment indexes

DROP INDEX IF EXISTS t_comment_inserted_by_idx;
DROP INDEX IF EXISTS t_comment_post_id_idx;

-- s_forum.t_vote indexes

DROP INDEX IF EXISTS t_vote_inserted_by_idx;
DROP INDEX IF EXISTS t_vote_post_id_idx;

-- s_forum.t_post indexes rollback

DROP INDEX IF EXISTS t_post_inserted_by_id_idx;
DROP INDEX IF EXISTS t_post_forum_id_idx;

-- s_forum.t_verification_token indexes rollback

DROP INDEX IF EXISTS t_verification_token_token_idx;