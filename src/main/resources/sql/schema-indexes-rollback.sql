-- ROLLBACK
-- s_forum.t_verification_token indexes rollback

DROP INDEX IF EXISTS t_post_forum_id_idx;

-- s_forum.t_verification_token indexes rollback

DROP INDEX IF EXISTS t_verification_token_token_idx;

