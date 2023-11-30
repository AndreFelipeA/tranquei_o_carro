package controllers;

public class VerificaLocalizacao {
    private double latitude;
    private double longitude;

    public VerificaLocalizacao(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void atualizarLocalizacao(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String obterLocalizacao() {
        if(latitude == 0.0 & longitude == 0.0)
        {
            return "Carro não tem Localização cadastrada";
        }
        return "Latitude: " + latitude + " Longitude: " + longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}