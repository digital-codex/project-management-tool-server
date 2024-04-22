CREATE TABLE public.t_project_category (
    id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT t_project_category_pk PRIMARY KEY (id),
        CONSTRAINT t_project_category_name_unique UNIQUE (name)
);

CREATE TABLE public.t_project_category_description (
    shared_id BIGINT NOT NULL,
    description TEXT,
    CONSTRAINT t_project_category_description_pk PRIMARY KEY (shared_id)
);

ALTER TABLE public.t_project_category_description ADD CONSTRAINT t_project_category_description_to_t_project_category_fk FOREIGN KEY (shared_id) REFERENCES public.t_project_category (id);

CREATE TABLE public.t_project (
    id BIGINT NOT NULL,
    name VARCHAR(80) NOT NULL,
    key VARCHAR(10) NOT NULL,
    project_category_id BIGINT,
    CONSTRAINT t_project_pk PRIMARY KEY (id),
    CONSTRAINT t_project_name_unique UNIQUE (name),
    CONSTRAINT t_project_key_unique UNIQUE (key)
);

ALTER TABLE public.t_project ADD CONSTRAINT t_project_project_category_to_t_project_category_fk FOREIGN KEY (project_category_id) REFERENCES public.t_project_category (id);