ALTER TABLE bpjsconsumer
ADD COLUMN provider_code VARCHAR(50);

UPDATE bpjsconsumer SET provider_code = '0038R091' WHERE entity_code = 'MTMH';