
IF OBJECT_ID ('dbo.ms_base_ref_info') IS NOT NULL
	DROP TABLE dbo.ms_base_ref_info
GO

CREATE TABLE dbo.ms_base_ref_info
	(
	pk_base_ref_info     	VARCHAR (30),
	ref_code     	VARCHAR (30),
	ref_name     	VARCHAR (30),
	ref_class     	VARCHAR (256) DEFAULT ('~'),
	ref_type    	INT  DEFAULT (0),
	modulename        	VARCHAR (30) DEFAULT ('~'),
	
	ts                  CHAR (19) DEFAULT (CONVERT([char](19),getdate(),(20))),
	dr                  SMALLINT DEFAULT (0),
	CONSTRAINT PK_ms_base_ref_info PRIMARY KEY (pk_base_ref_info)
	)
GO

-- https://blog.csdn.net/junloong_s/article/details/79083801
-- 查看约束
select * from information_schema.constraint_column_usage where TABLE_NAME = 'ms_base_ref_info';
-- 存在主键约束PK_ms_base_ref_info，则删除 
IF EXISTS(SELECT * FROM sysobjects WHERE name='PK_ms_base_ref_info' and xtype='PK')
Alter TABLE ms_base_ref_info
Drop Constraint PK_ms_base_ref_info
Go -- 重新添加主键约束PK_ms_base_ref_info
ALTER TABLE ms_base_ref_info ADD CONSTRAINT PK_ms_base_ref_info PRIMARY KEY (pk_base_ref_info)
Go

INSERT ms_base_ref_info (pk_base_ref_info, ref_code, ref_name, ref_class, modulename)
VALUES('100001ZAAC00000000000000XYHJ01','500STAFF201','工作人员','com.ms.base.rest.staff.translate.UserTranslate','base_staff')

INSERT ms_base_ref_info (pk_base_ref_info, ref_code, ref_name, ref_class, modulename)
VALUES('100001ZAAC00000000000000XYHJ02','100ORGS001','组织','com.ms.base.rest.orgs.translate.OrgsElementTranslate','base_orgs')

