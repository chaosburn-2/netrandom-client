module com.chaostek.netrandomclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    //requires com.fasterxml.jackson.core;
    requires com.google.gson;

    opens com.chaostek.netrandomclient to javafx.fxml, com.google.gson;
    //opens com.chaostek.netrandomclient.randomRequest;
    
    exports com.chaostek.netrandomclient;
}
