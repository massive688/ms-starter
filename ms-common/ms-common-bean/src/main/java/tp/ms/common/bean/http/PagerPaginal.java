package tp.ms.common.bean.http;

import java.io.Serializable;
import java.util.Collection;


public class PagerPaginal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8972567266607663767L;
	
	private int paginalNumber = 0; 		//当前页数
	private int paginaltolNumber=1200;	//当前页面可显示总数
	private int fraction=0;				//当前页片数
	private int fractionNum=40;			//每页显示的页片数
	private int displayNum=15;			//每页片显示的条目数
	private int allEntries=0;			//所有总数
	
	@SuppressWarnings("unused")
	private Collection<FieldEQValue> where;            //字段条件集合


	public int getPaginalNumber() {
		return paginalNumber;
	}
	public void setPaginalNumber(int paginalNumber) {
		this.paginalNumber = paginalNumber;
	}
	public int getPaginaltolNumber() {
		return paginaltolNumber;
	}
	public void setPaginaltolNumber(int paginaltolNumber) {
		this.paginaltolNumber = paginaltolNumber;
	}

	public int getFraction() {
		return fraction;
	}
	public void setFraction(int fraction) {
		this.fraction = fraction;
	}
	public int getFractionNum() {
		return fractionNum;
	}
	public void setFractionNum(int fractionNum) {
		this.fractionNum = fractionNum;
	}
	public int getDisplayNum() {
		return displayNum;
	}
	public void setDisplayNum(int displayNum) {
		this.displayNum = displayNum;
	}
	public int getAllEntries() {
		return allEntries;
	}
	public void setAllEntries(int allEntries) {
		this.allEntries = allEntries;
	}
	
	
	
}
