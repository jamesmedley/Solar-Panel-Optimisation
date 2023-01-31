package model;

public class PanelProperties {

    private int panelTilt; //Holds an integer value for panel tilt

    private int panelAzimuth; //Holds an integer value for panel azimuth

    private double longitude; //Holds a double value for a longitude

    private double latitude; //Holds a double value for a latitude

    public void setPanelTilt(int tilt) {
        this.panelTilt = tilt;
    }

    public void setPanelAzimuth(int azimuth) {
        this.panelAzimuth = azimuth;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getPanelTilt() {
        return this.panelTilt;
    }

    public int getPanelAzimuth() {
        return this.panelAzimuth;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }
}
