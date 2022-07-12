package enumrank;

public enum Rank {
	KEM("KEM"), YEU("YEU"), TRUNG_BINH("TRUNG BINH"), KHA("KHA"), GIOI("GIOI"), XUAT_SAC("XUAT SAC");

	private final String value;

	Rank(String value) {
		this.value = value;
	}

	public String rank() {
		return this.value;

	}

}