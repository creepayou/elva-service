ALTER TABLE bpjsconsumer
ADD COLUMN vclaim_version VARCHAR(50) NOT NULL SET DEFAULT("1.1");

ALTER TABLE bpjsconsumer
ALTER COLUMN provider_code SET NOT NULL;