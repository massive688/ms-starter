IF OBJECT_ID ('dbo.ms_base_staff') IS NOT NULL
	DROP TABLE dbo.ms_base_staff
GO

CREATE TABLE dbo.ms_base_staff
	(
	pk_user     	VARCHAR (30),
	user_code      	VARCHAR (64) DEFAULT ('~'),
	nickname     	VARCHAR (64) DEFAULT ('~'),
	user_name    	VARCHAR (256) DEFAULT ('~'),
	head_photo    	VARCHAR (256) DEFAULT ('~'),
	userid        	VARCHAR (30) DEFAULT ('~'),
	roles           VARCHAR (30) DEFAULT ('~'),
	
	pk_corp             VARCHAR (30),
	pk_group            VARCHAR (30) DEFAULT ('~'),
	creator             VARCHAR (50),
	creationtime        VARCHAR (30),
	modifier            VARCHAR (30),
	modifiedtime        VARCHAR (30),
	billtype            VARCHAR (30),
	enabled    			SMALLINT DEFAULT (0),
	ts                  CHAR (19) DEFAULT (CONVERT([char](19),getdate(),(20))),
	dr                  SMALLINT DEFAULT (0),
	CONSTRAINT PK_my_adream_staff PRIMARY KEY (pk_user)
	)
GO


IF OBJECT_ID ('dbo.ms_base_staff_relation') IS NOT NULL
	DROP TABLE dbo.ms_base_staff_relation
GO

CREATE TABLE dbo.ms_base_staff_relation
	(
	pk_user     	VARCHAR (30),
	pk_post       VARCHAR (30),
	is_main       SMALLINT DEFAULT (0),
	higher_up     VARCHAR (30)
	)
GO