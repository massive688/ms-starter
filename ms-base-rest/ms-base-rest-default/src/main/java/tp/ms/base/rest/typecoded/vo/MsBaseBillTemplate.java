package tp.ms.base.rest.typecoded.vo;

import java.io.Serializable;

import tp.ms.base.rest.resource.vo.ChildBaseVO;

public class MsBaseBillTemplate extends ChildBaseVO implements Serializable {
	
    private String pkBaseBillTemplate;

    private String pkBilltype;

    private String columnModuleName;

    private String columnClassQualifiedName;

    private String columnClassNameAbbreviation;

    private String name;

    private String type;

    private String describe;

    private String placeholder;

    private String required;

    private String emptyHint;

    private String readonly;

    private Integer minLength;

    private Integer maxLength;

    private Integer fieldType;

    private String refTranslate;

    private Integer refShow;

    private Integer reorder;

    private String refFormula;

    private String refUrl;

    private String dataTranslateName;

    private String prevGeneratorName;

    private String refQueryCondition;

    private String tableName;

    private static final long serialVersionUID = 1L;

    public String getPkBaseBillTemplate() {
        return pkBaseBillTemplate;
    }

    public void setPkBaseBillTemplate(String pkBaseBillTemplate) {
        this.pkBaseBillTemplate = pkBaseBillTemplate == null ? null : pkBaseBillTemplate.trim();
    }

    public String getPkBilltype() {
        return pkBilltype;
    }

    public void setPkBilltype(String pkBilltype) {
        this.pkBilltype = pkBilltype == null ? null : pkBilltype.trim();
    }

    public String getColumnModuleName() {
        return columnModuleName;
    }

    public void setColumnModuleName(String columnModuleName) {
        this.columnModuleName = columnModuleName == null ? null : columnModuleName.trim();
    }

    public String getColumnClassQualifiedName() {
        return columnClassQualifiedName;
    }

    public void setColumnClassQualifiedName(String columnClassQualifiedName) {
        this.columnClassQualifiedName = columnClassQualifiedName == null ? null : columnClassQualifiedName.trim();
    }

    public String getColumnClassNameAbbreviation() {
        return columnClassNameAbbreviation;
    }

    public void setColumnClassNameAbbreviation(String columnClassNameAbbreviation) {
        this.columnClassNameAbbreviation = columnClassNameAbbreviation == null ? null : columnClassNameAbbreviation.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder == null ? null : placeholder.trim();
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required == null ? null : required.trim();
    }

    public String getEmptyHint() {
        return emptyHint;
    }

    public void setEmptyHint(String emptyHint) {
        this.emptyHint = emptyHint == null ? null : emptyHint.trim();
    }

    public String getReadonly() {
        return readonly;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly == null ? null : readonly.trim();
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public Integer getFieldType() {
        return fieldType;
    }

    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
    }

    public String getRefTranslate() {
        return refTranslate;
    }

    public void setRefTranslate(String refTranslate) {
        this.refTranslate = refTranslate == null ? null : refTranslate.trim();
    }

    public Integer getRefShow() {
        return refShow;
    }

    public void setRefShow(Integer refShow) {
        this.refShow = refShow;
    }

    public Integer getReorder() {
        return reorder;
    }

    public void setReorder(Integer reorder) {
        this.reorder = reorder;
    }

    public String getRefFormula() {
        return refFormula;
    }

    public void setRefFormula(String refFormula) {
        this.refFormula = refFormula == null ? null : refFormula.trim();
    }

    public String getRefUrl() {
        return refUrl;
    }

    public void setRefUrl(String refUrl) {
        this.refUrl = refUrl == null ? null : refUrl.trim();
    }

    public String getDataTranslateName() {
        return dataTranslateName;
    }

    public void setDataTranslateName(String dataTranslateName) {
        this.dataTranslateName = dataTranslateName == null ? null : dataTranslateName.trim();
    }

    public String getPrevGeneratorName() {
        return prevGeneratorName;
    }

    public void setPrevGeneratorName(String prevGeneratorName) {
        this.prevGeneratorName = prevGeneratorName == null ? null : prevGeneratorName.trim();
    }

    public String getRefQueryCondition() {
        return refQueryCondition;
    }

    public void setRefQueryCondition(String refQueryCondition) {
        this.refQueryCondition = refQueryCondition == null ? null : refQueryCondition.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }



	@Override
	public String getParentKey() {
		return this.pkBilltype;
	}

	@Override
	public void setParentKey(String parentKey) {
		this.pkBilltype = parentKey;
	}

	@Override
	public String getTable() {
		return "ms_base_bill_template";
	}

	@Override
	public void setPrimaryKey(String key) {
		this.pkBaseBillTemplate = key;
	}

	@Override
	public String getPrimaryKey() {
		return this.pkBaseBillTemplate;
	}
}