package TP4;

public class Trans<T> {
	private String name;
	private T value;

	public Trans(String name, T value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public T getValue() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == this)
			return true;
		if (obj instanceof Trans) {
			return this.name.equals(((Trans<?>) obj).name) && this.value.equals(((Trans<?>) obj).value);
		}
		return false;

	}

	@Override
	    public int hashCode() {
	        int hash = 5;
	        hash =   hash + (this.name != null ? this.name.hashCode() : 0);
	        hash =   hash + this.value.hashCode();
	        return hash;
	    }

}
