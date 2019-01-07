
IF OBJECT_ID ('dbo.ms_base_orgelements') IS NOT NULL
	DROP TABLE dbo.ms_base_orgelements
GO

CREATE TABLE dbo.ms_base_orgelements
	(
	pk_orgelements     	VARCHAR (30),
	code             		VARCHAR (64) DEFAULT ('~'),
	name           			VARCHAR (256) DEFAULT ('~'),
	parent_id           VARCHAR (30) DEFAULT ('~'),
	type        				SMALLINT NOT NULL,
	pk_corp             VARCHAR (30),
	pk_group            VARCHAR (30) DEFAULT ('~'),
	creator             VARCHAR (50),
	creationtime        VARCHAR (30),
	modifier            VARCHAR (30),
	modifiedtime        VARCHAR (30),
	billtype            VARCHAR (30),
	enabled    					SMALLINT,
	ts                  CHAR (19) DEFAULT (CONVERT([char](19),getdate(),(20))),
	dr                  SMALLINT DEFAULT ((0)),
	CONSTRAINT PK_ms_base_orgelements PRIMARY KEY (pk_orgelements)
	)
GO

INSERT INTO ms_base_orgelements VALUES('000001AAADREAM0000000000000A01','000001','真爱梦想',NULL,0,NULL,NULL,'admin','2018-09-19 15:10:25',NULL,NULL,NULL,0,'2018-09-19 15:10:25',0);
INSERT INTO ms_base_orgelements VALUES('000001AA0000000000000000000B01','100001','真爱梦想公益基金会','000001AAADREAM0000000000000A01',1,NULL,'000001AAADREAM0000000000000A01','admin','2018-09-19 15:10:25',NULL,NULL,NULL,0,'2018-09-19 15:10:25',0);
INSERT INTO ms_base_orgelements VALUES('000001AA0000000000000000000B02','200001','真爱梦想公益发展中心','000001AAADREAM0000000000000A01',1,NULL,'000001AAADREAM0000000000000A01','admin','2018-09-19 15:10:25',NULL,NULL,NULL,0,'2018-09-19 15:10:25',0);
