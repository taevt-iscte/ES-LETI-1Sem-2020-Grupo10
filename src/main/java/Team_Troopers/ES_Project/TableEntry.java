package Team_Troopers.ES_Project;

public class TableEntry {
	
	private String type;
	private int pmd_count;
	private int iPlasma_count;
	private int user_count;
	
	public TableEntry(String type, int pmd_count, int iPlasma_count, int user_count) {
		this.type = type;
		this.pmd_count = pmd_count;
		this.iPlasma_count = iPlasma_count;
		this.user_count = user_count;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPmd_count() {
		return pmd_count;
	}

	public void setPmd_count(int pmd_count) {
		this.pmd_count = pmd_count;
	}

	public int getIPlasma_count() {
		return iPlasma_count;
	}

	public void setIPlasma_count(int iPlasma_count) {
		this.iPlasma_count = iPlasma_count;
	}
	
	public int getUser_count() {
		return user_count;
	}

	public void setUser_count(int user_count) {
		this.user_count = user_count;
	}
}
