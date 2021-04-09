--liquibase formatted sql
--changeset nwaszkowiak:1
CREATE TABLE post (
  id VARCHAR(60) PRIMARY KEY,
  title VARCHAR(400) NOT NULL,
  content VARCHAR(2000) NULL,
  created_at timestamp
);

--changeset nwaszkowiak:2
CREATE TABLE comment (
                         id VARCHAR(60) PRIMARY KEY,
                         post_id VARCHAR(60) NOT NULL,
                         content VARCHAR(2000) NULL,
                         created_at timestamp
);

--changeset nwaszkowiak:3
ALTER TABLE comment
    ADD CONSTRAINT comment_post_id
        FOREIGN KEY (post_id) REFERENCES post(id)
