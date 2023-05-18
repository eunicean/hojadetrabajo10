public class Connection {

    private String OriginCity;
    private String EndCity;
    private int NormalTravelTime;
    private int RainTravelTime;
    private int SnowTravelTime;
    private int StormTravelTime;
    private int CurrentTravelTime;


    public Connection(String start, String end, int nTime, int rTime, int snTime, int stTime){
        this.OriginCity = start;
        this.EndCity = end;
        this.NormalTravelTime = nTime;
        this.RainTravelTime = rTime;
        this.SnowTravelTime = snTime;
        this.StormTravelTime = stTime;

        this.CurrentTravelTime = nTime; // The initial travel time is set to the normal travel time by default
    }

    public String getOriginCity() {
        return OriginCity;
    }

    public void setOriginCity(String originCity) {
        OriginCity = originCity;
    }

    public String getEndCity() {
        return EndCity;
    }

    public void setEndCity(String endCity) {
        EndCity = endCity;
    }

    public int getNormalTravelTime() {
        return NormalTravelTime;
    }

    public void setNormalTravelTime(int normalTravelTime) {
        NormalTravelTime = normalTravelTime;
    }

    public int getRainTravelTime() {
        return RainTravelTime;
    }

    public void setRainTravelTime(int rainTravelTime) {
        RainTravelTime = rainTravelTime;
    }

    public int getSnowTravelTime() {
        return SnowTravelTime;
    }

    public void setSnowTravelTime(int snowTravelTime) {
        SnowTravelTime = snowTravelTime;
    }

    public int getStormTravelTime() {
        return StormTravelTime;
    }

    public void setStormTravelTime(int stormTravelTime) {
        StormTravelTime = stormTravelTime;
    }

    public int getCurrentTravelTime() {
        return CurrentTravelTime;
    }

    public void setCurrentTravelTime(int currentTravelTime) {
        CurrentTravelTime = currentTravelTime;
    }

    public void updateCurrentTravelTime(String climateCondition) {
        switch (climateCondition) {
            case "Normal":
                CurrentTravelTime = NormalTravelTime;
                break;
            case "Rain":
                CurrentTravelTime = RainTravelTime;
                break;
            case "Snow":
                CurrentTravelTime = SnowTravelTime;
                break;
            case "Storm":
                CurrentTravelTime = StormTravelTime;
                break;
            default:
                throw new IllegalArgumentException("Invalid climate condition");
        }
    }
}
