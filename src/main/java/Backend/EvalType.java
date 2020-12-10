package Backend;

import Team_Troopers.ES_Project.PrimaryController;

/**
 * Enumerado desenvolvido para assistir os dados do hashmap, garantindo uma representação de cada um deles.
 * 
 * @see      PrimaryController
 * @see      ExcelRecord
 * @author   Tiago Torres, José Raposo
 */

public enum EvalType {
	
	PLASMA_DCI, PLASMA_DII, PLASMA_ADCI, PLASMA_ADII,
	PMD_DCI, PMD_DII, PMD_ADCI, PMD_ADII,
	USER_DCI, USER_DII, USER_ADCI, USER_ADII
}