package tp.ms.common.bean.http;


import com.alibaba.fastjson.JSON;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldEQValue {
	
	String field;
	Symbol symbol;
	String value;
	
	Long lk = 90l;
	
	public FieldEQValue() {

	}
	
	public FieldEQValue(String field, Symbol symbol, String value) {
		this.field = field;
		this.symbol = symbol;
		this.value = value;
	}

	public void main() {
		
		Symbol sf = JSON.parseObject("3", Symbol.class);
		
		symbol = sf;
		switch (symbol) {
		case between:
			println(symbol);
			break;
		case greater_than:
			println(symbol);
			break;
		case less_than:
			println(symbol);
			break;
		case equal:
			println(symbol);
			break;
		case unequal:
			println(symbol);
			break;
		case greater_than_equal:
			println(symbol);
			break;
		case less_than_equal:
			println(symbol);
			break;
		case like:
			println(symbol);
			break;
		case leftLike:
			println(symbol);
			break;
		case rightLike:
			println(symbol);
			break;
		default:
			System.out.println("nothing");
			break;
		}
	}
	private void println(Symbol symbol) {
		System.out.println(symbol);
		System.out.println(symbol.code);
		
	}
	
}
