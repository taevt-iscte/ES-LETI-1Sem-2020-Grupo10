package Backend;

import Team_Troopers.ES_Project.ExcelController;
import Team_Troopers.ES_Project.PrimaryController;

/**
 * Classe auxiliar responsável por agrupar linhas de excel em elementos singulares, com informações repartidas, a fim de serem tratados e representados numa tabela.
 * 
 * @see      PrimaryController
 * @see      ExcelController
 * @author   Tiago Torres
 */

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
	private EvalType eval_pmd;
	private EvalType eval_iPlasma;
	
	/**
	  * Permite construir um objeto da classe.
	  * 
	  * @param    id     			valor identificativo do objeto.
	  * @param	  package_			valor identificativo do pacote respetivo do objeto.
	  * @param	  class_			valor identificativo da classe à qual o objeto pertence.
	  * @param	  method			valor provindo do excel referente ao método em estudo.
	  * @param	  loc				valor provindo do excel referente ao loc do método em estudo.
	  * @param	  cyclo				valor provindo do excel referente ao cyclo do método em estudo.
	  * @param	  atfd				valor provindo do excel referente ao atfd do método em estudo.
	  * @param	  laa				valor provindo do excel referente ao laa do método em estudo.
	  * @param	  is_long_method	valor provindo do excel referente à avaliação "long method" do método em estudo.
	  * @param	  iPlasma			valor provindo do excel referente à identificação da avaliação "iPlasma" do método em estudo.
	  * @param	  pMD				valor provindo do excel referente à identificação da avaliação "PMD" do método em estudo.
	  * @param	  is_feature_envy	valor provindo do excel referente à avaliação "feature envy" do método em estudo.
	  * 
	  * @see	  ExcelController
	  * @author   Tiago Torres
	  */

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
		evaluate();
	}
	
	/**
	 * Método auxiliar da classe responsável por gerar uma avaliação final de acordo com a informação adquirida da tabela, utilizando os valores default de avaliação, 
	 * gerando um valor final por objeto aquando da sua criação.
	 * 
	 * @author   Tiago Torres
	 */

	private void evaluate() {
        if (is_long_method) {
            if (iPlasma)
                eval_iPlasma = EvalType.PLASMA_DCI;
            else
                eval_iPlasma = EvalType.PLASMA_DII;
            if (pmd)
                eval_pmd = EvalType.PMD_DCI;
            else
                eval_pmd = EvalType.PMD_DII;
        } else {
            if (!iPlasma)
                eval_iPlasma = EvalType.PLASMA_ADCI;
            else
                eval_iPlasma = EvalType.PLASMA_ADII;
            if (!pmd)
                eval_pmd = EvalType.PMD_ADCI;
            else
                eval_pmd = EvalType.PMD_ADII;
        }
    }
	
	/**
	 * Método auxiliar da classe responsável por gerar um array que contemple todos os valores das avaliações geradas a partir das
	 * normas default de avaliação, devolvendo o mesmo para uso futuro.
	 * 
	 * @return	 evals			Array gerado que contem todas as avaliações geradas após a leitura do ficheiro excel em questão.
	 * @author   Tiago Torres
	 */

	public EvalType[] getEval() {
		EvalType[] evals = { eval_iPlasma, eval_pmd };
		return evals;
	}
	
	/**
	 * Método auxiliar da classe responsável por gerar uma avaliação final de acordo com a informação adquirida da tabela, 
	 * utilizando os valores de avaliação definidos pelo utilizador, gerando um valor final por objeto aquando da sua criação.
	 * 
	 * @param	 userThink		Valor de controlo referente ao uso de regras definidas pelo utilizador.
	 * @return	 EvalType	 	Valor do tipo EvalType referente ao tipo de avaliação que o dado em questão adquiriu.
	 * @author   Tiago Torres
	 */

	public EvalType userEval(boolean userThink) {
		if (is_long_method) {
			if (userThink)
				return EvalType.USER_DCI;
			else
				return EvalType.USER_DII;
		}
		else {
			if (!userThink)
				return EvalType.USER_ADCI;
			else
				return EvalType.USER_ADII;
		}
	}
	
	/**
	 * Método auxiliar da classe responsável por devolver o id de dado objeto da classe
	 * 
	 * @return	 id	 			Valor id do objeto da classe a ser lido.
	 * @author   Tiago Torres
	 */
	
	public int getId() {
		return id;
	}
	
	/**
	 * Método auxiliar da classe responsável por reescrever o id de dado objeto da classe
	 * 
	 * @param	 id	 			Valor id do objeto da classe a ser registado.
	 * @author   Tiago Torres
	 */

	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Método auxiliar da classe responsável adquirir o nome do package do objeto da classe.
	 * 
	 * @return	 package_ 		Nome do package do objeto a ser avaliado.
	 * @author   Tiago Torres
	 */

	public String getPackage_() {
		return package_;
	}
	
	/**
	 * Método auxiliar da classe responsável por reescrever o nome da package do objeto.
	 * 
	 * @param	 package_ 		Nome do package do objeto a ser rescrito.
	 * @author   Tiago Torres
	 */

	public void setPackage_(String package_) {
		this.package_ = package_;
	}
	
	/**
	 * Método auxiliar da classe responsável por devolver a classe de dado método a ser avaliado.
	 * 
	 * @return	 class_ 		Nome da classe do objeto a ser avaliado.
	 * @author   Tiago Torres
	 */

	public String getClass_() {
		return class_;
	}

	/**
	 * Método auxiliar da classe responsável por reescrever a classe do método a ser avaliado.
	 * 
	 * @param	 class_ 		Nome da classe do objeto a ser rescrito.
	 * @author   Tiago Torres
	 */
	
	public void setClass_(String class_) {
		this.class_ = class_;
	}
	
	/**
	 * Método auxiliar da classe responsável por devolver o nome do método a ser avaliado.
	 * 
	 * @return	 method 		Nome do método do objeto a ser avaliado.
	 * @author   Tiago Torres
	 */

	public String getMethod() {
		return method;
	}
	
	/**
	 * Método auxiliar da classe responsável por alterar o nome do método a ser avaliado.
	 * 
	 * @param	 method 		Nome do método do objeto a ser escrito.
	 * @author   Tiago Torres
	 */

	public void setMethod(String method) {
		this.method = method;
	}
	
	/**
	 * Método auxiliar da classe responsável por devolver o valor loc do método a ser avaliado.
	 * 
	 * @return	 loc 			Valor loc do método do objeto a ser avaliado.
	 * @author   Tiago Torres
	 */

	public int getLoc() {
		return loc;
	}
	
	/**
	 * Método auxiliar da classe responsável por alterar o valor loc do método a ser avaliado.
	 * 
	 * @param	 loc 			Valor loc do método do objeto a ser escrito.
	 * @author   Tiago Torres
	 */

	public void setLoc(int loc) {
		this.loc = loc;
	}
	
	/**
	 * Método auxiliar da classe responsável por devolver o valor cyclo do método a ser avaliado.
	 * 
	 * @return	 cyclo 			Valor cyclo do método do objeto a ser avaliado.
	 * @author   Tiago Torres
	 */

	public int getCyclo() {
		return cyclo;
	}
	
	/**
	 * Método auxiliar da classe responsável por alterar o valor cyclo do método a ser avaliado.
	 * 
	 * @param	 cyclo 			Valor cyclo do método do objeto a ser alterado.
	 * @author   Tiago Torres
	 */

	public void setCyclo(int cyclo) {
		this.cyclo = cyclo;
	}
	
	/**
	 * Método auxiliar da classe responsável por devolver o valor atfd do método a ser avaliado.
	 * 
	 * @return	 atfd 			Valor atfd do método do objeto a ser avaliado.
	 * @author   Tiago Torres
	 */

	public int getAtfd() {
		return atfd;
	}

	/**
	 * Método auxiliar da classe responsável por alterar o valor atfd do método a ser avaliado.
	 * 
	 * @param	 atfd 			Valor atfd do método do objeto a ser alterado.
	 * @author   Tiago Torres
	 */
	
	public void setAtfd(int atfd) {
		this.atfd = atfd;
	}
	
	/**
	 * Método auxiliar da classe responsável por devolver o valor laa do método a ser avaliado.
	 * 
	 * @return	 laa 			Valor laa do método do objeto a ser avaliado.
	 * @author   Tiago Torres
	 */

	public double getLaa() {
		return laa;
	}

	/**
	 * Método auxiliar da classe responsável por alterar o valor laa do método a ser avaliado.
	 * 
	 * @param	 laa 			Valor laa do método do objeto a ser alterado.
	 * @author   Tiago Torres
	 */
	
	public void setLaa(double laa) {
		this.laa = laa;
	}
	
	/**
	 * Método auxiliar da classe responsável por devolver se o método a ser avaliado é long method.
	 * 
	 * @return	 is_long_method 	Valor booleano relativa à veracidade do método do objeto ser long method.
	 * @author   Tiago Torres
	 */

	public boolean isIs_long_method() {
		return is_long_method;
	}

	/**
	 * Método auxiliar da classe responsável por alterar se o método a ser avaliado é long method.
	 * 
	 * @param	 is_long_method 	Valor booleano relativa à veracidade do método do objeto ser long method.
	 * @author   Tiago Torres
	 */
	
	public void setIs_long_method(boolean is_long_method) {
		this.is_long_method = is_long_method;
	}
	
	/**
	 * Método auxiliar da classe responsável por verificar o valor iPlasma do método a ser avaliado.
	 * 
	 * @return	 iPlasma 		Valor booleano relativa à veracidade do método do objeto ser avaliado pela ferramenta iPlasma.
	 * @author   Tiago Torres
	 */

	public boolean isIPlasma() {
		return iPlasma;
	}

	/**
	 * Método auxiliar da classe responsável por alterar o valor iPlasma do método a ser avaliado.
	 * 
	 * @param	 iPlasma 		Valor booleano relativa à veracidade do método do objeto ser avaliado pela ferramenta iPlasma.
	 * @author   Tiago Torres
	 */
	
	public void setIPlasma(boolean iPlasma) {
		this.iPlasma = iPlasma;
	}
	
	/**
	 * Método auxiliar da classe responsável por verificar o valor PMD do método a ser avaliado.
	 * 
	 * @return	 pMD 			Valor booleano relativa à veracidade do método do objeto ser avaliado pela ferramenta PMD.
	 * @author   Tiago Torres
	 */

	public boolean isPmd() {
		return pmd;
	}

	/**
	 * Método auxiliar da classe responsável por alterar o valor PMD do método a ser avaliado.
	 * 
	 * @param	 pMD 			Valor booleano relativa à veracidade do método do objeto ser avaliado pela ferramenta PMD.
	 * @author   Tiago Torres
	 */
	
	public void setPmd(boolean pMD) {
		this.pmd = pMD;
	}
	
	/**
	 * Método auxiliar da classe responsável por devolver se o método a ser avaliado é feature envy.
	 * 
	 * @return	 is_feature_envy 	Valor booleano relativa à veracidade do método do objeto ser feature envy.
	 * @author   Tiago Torres
	 */

	public boolean isIs_feature_envy() {
		return is_feature_envy;
	}

	/**
	 * Método auxiliar da classe responsável por alterar se o método a ser avaliado é feature envy.
	 * 
	 * @param	 is_feature_envy 	Valor booleano relativa à veracidade do método do objeto ser feature envy.
	 * @author   Tiago Torres
	 */
	
	public void setIs_feature_envy(boolean is_feature_envy) {
		this.is_feature_envy = is_feature_envy;
	}

}