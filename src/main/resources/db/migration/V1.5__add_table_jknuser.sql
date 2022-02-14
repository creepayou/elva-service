CREATE TABLE IF NOT EXISTS "public"."jknuser" (
  "jknuser_id" serial4 PRIMARY KEY,
  "username" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "entity_code" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "created_by" int4 NOT NULL,
  "created_datetime" timestamptz(6) NOT NULL,
  "last_updated_by" int4,
  "last_updated_datetime" timestamptz(6),
  "defunct_ind" char(1) COLLATE "pg_catalog"."default" NOT NULL,
  UNIQUE ("entity_code")
);