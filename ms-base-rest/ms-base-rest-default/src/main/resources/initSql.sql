
IF OBJECT_ID ('dbo.ms_base_billtype') IS NOT NULL
	DROP TABLE dbo.ms_base_billtype
GO

CREATE TABLE dbo.ms_base_billtype
	(
	pk_billtype  VARCHAR (30) NOT NULL,
	code         VARCHAR (64) DEFAULT ('~'),
	name         VARCHAR (256) DEFAULT ('~'),
	module_name  VARCHAR (128),
	package_name  VARCHAR (128),
	component    VARCHAR (30),
	component_type INT ,
	ui_path       VARCHAR (256) DEFAULT ('~'),

	--主表必须的字段
	pk_corp             VARCHAR (30),
	pk_group            VARCHAR (30) DEFAULT ('~'),
	creator             VARCHAR (50),
	creationtime        VARCHAR (30),
	modifier            VARCHAR (30),
	modifiedtime        VARCHAR (30),
--	billtype            VARCHAR (30),
	enabled    			SMALLINT DEFAULT (0),
	--审核必须要的字段
	approve_status      SMALLINT DEFAULT (-1),
	bill_status      SMALLINT DEFAULT (-1),
	process_id   				VARCHAR (64),
	
	--所有实体类都必须要的标识字段
	ts           CHAR (19) DEFAULT (CONVERT([char](19),getdate(),(20))),
	dr           SMALLINT DEFAULT ((0)),
	CONSTRAINT PK_ms_base_billtype PRIMARY KEY (pk_billtype)
	)
GO


-- billtype template 单据类型版本字段详情明细表

IF OBJECT_ID ('dbo.ms_base_bill_template') IS NOT NULL
	DROP TABLE dbo.ms_base_bill_template
GO

CREATE TABLE dbo.ms_base_bill_template
	(
	pk_base_bill_template     	VARCHAR (30),
	pk_billtype  VARCHAR (30) NOT NULL,
	column_module_name     			VARCHAR (30),
	column_class_qualified_name    VARCHAR (256),
	column_class_name_abbreviation VARCHAR (64) DEFAULT ('~'),
	name        	VARCHAR (64) DEFAULT ('~'),
	type        	VARCHAR (30) DEFAULT ('~'),
	describe        	VARCHAR (64) DEFAULT ('~'),
	placeholder        	VARCHAR (64) DEFAULT ('~'),
	required        	VARCHAR (64) DEFAULT ('~'),
	empty_hint        	VARCHAR (64) DEFAULT ('~'),
	readonly        	VARCHAR (64) DEFAULT ('~'),
	min_length        	INT DEFAULT (0),
	max_length        	INT DEFAULT (0),
	field_type        	INT DEFAULT (0),
	ref_translate        	VARCHAR (64) DEFAULT ('~'),
	ref_show        	INT DEFAULT (0),
	reorder        	INT DEFAULT (0),
	ref_formula        	VARCHAR (256) DEFAULT ('~'),
	ref_url        	VARCHAR (256) DEFAULT ('~'),
	data_translate_name VARCHAR (64) DEFAULT ('~'),
	prev_generator_name VARCHAR (64) DEFAULT ('~'),
	table_name VARCHAR (64) DEFAULT ('~'),

	ts                  CHAR (19) DEFAULT (CONVERT([char](19),getdate(),(20))),
	dr                  SMALLINT DEFAULT (0),
	CONSTRAINT PK_ms_base_bill_template PRIMARY KEY (pk_base_bill_template)
	)
GO


