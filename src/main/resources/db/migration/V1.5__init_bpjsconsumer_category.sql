CREATE TABLE IF NOT EXISTS "public"."bpjsconsumer_category" (
  "bpjsconsumer_category_id" serial4 PRIMARY KEY,
  "bpjsconsumer_id" int4 NOT NULL,
  "category" varchar(50) NOT NULL,
  "user_key" varchar(255) NOT NULL,
  "created_by" int4 NOT NULL,
  "created_datetime" timestamptz(6) NOT NULL,
  "last_updated_by" int4,
  "last_updated_datetime" timestamptz(6),
  "defunct_ind" char(1) NOT NULL DEFAULT 'N',
  UNIQUE ("bpjsconsumer_id", "category"),
  CONSTRAINT fk_bpjsconsumer_id
      FOREIGN KEY(bpjsconsumer_id) 
	  REFERENCES bpjsconsumer(bpjsconsumer_id)
	    ON DELETE CASCADE
);