package tp.ms.common.bean.http;

public enum Symbol {
	
	between(0), 
	greater_than(1), 
	less_than(2), 
	equal(3),
	greater_than_equal(4), 
	less_than_equal(5), 
	unequal(6), 
	like(7), 
	leftLike(8), 
	rightLike(9);
	

	int code;
	
	Symbol(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
}
