package tp.ms.base.rest.resource.logic;

@FunctionalInterface
public interface Callback {

	public abstract Object invoke(Object object);
	
}
