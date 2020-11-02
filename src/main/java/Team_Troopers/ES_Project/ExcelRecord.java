package Team_Troopers.ES_Project;

public class ExcelRecord {
	
	private int id;
	private String package_;
	private String class_;
	private String method;
	private int loc;
	private int cyclo;
	private int atfd;
	private double laa;
	private boolean is_long_method;
	private boolean iPlasma;
	private boolean pmd;
	private boolean is_feature_envy;
	
	
	public ExcelRecord(int id, String package_, String class_, String method, int loc, int cyclo, int atfd, double laa,
			boolean is_long_method, boolean iPlasma, boolean pMD, boolean is_feature_envy) {
		super();
		this.id = id;
		this.package_ = package_;
		this.class_ = class_;
		this.method = method;
		this.loc = loc;
		this.cyclo = cyclo;
		this.atfd = atfd;
		this.laa = laa;
		this.is_long_method = is_long_method;
		this.iPlasma = iPlasma;
		this.pmd = pMD;
		this.is_feature_envy = is_feature_envy;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getPackage_() {
		return package_;
	}


	public void setPackage_(String package_) {
		this.package_ = package_;
	}


	public String getClass_() {
		return class_;
	}


	public void setClass_(String class_) {
		this.class_ = class_;
	}


	public String getMethod() {
		return method;
	}


	public void setMethod(String method) {
		this.method = method;
	}


	public int getLoc() {
		return loc;
	}


	public void setLoc(int loc) {
		this.loc = loc;
	}


	public int getCyclo() {
		return cyclo;
	}


	public void setCyclo(int cyclo) {
		this.cyclo = cyclo;
	}


	public int getAtfd() {
		return atfd;
	}


	public void setAtfd(int atfd) {
		this.atfd = atfd;
	}


	public double getLaa() {
		return laa;
	}


	public void setLaa(double laa) {
		this.laa = laa;
	}


	public boolean isIs_long_method() {
		return is_long_method;
	}


	public void setIs_long_method(boolean is_long_method) {
		this.is_long_method = is_long_method;
	}


	public boolean isIPlasma() {
		return iPlasma;
	}


	public void setIPlasma(boolean iPlasma) {
		this.iPlasma = iPlasma;
	}


	public boolean isPmd() {
		return pmd;
	}


	public void setPmd(boolean pMD) {
		this.pmd = pMD;
	}


	public boolean isIs_feature_envy() {
		return is_feature_envy;
	}


	public void setIs_feature_envy(boolean is_feature_envy) {
		this.is_feature_envy = is_feature_envy;
	}
	
	

}
