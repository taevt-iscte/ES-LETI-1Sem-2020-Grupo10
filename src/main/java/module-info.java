module Team_Troopers.ES_Project {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires poi;
	requires javafx.base;

    opens Team_Troopers.ES_Project to javafx.fxml;
    opens Backend to javafx.fxml;
    exports Team_Troopers.ES_Project;
    exports Backend;
}