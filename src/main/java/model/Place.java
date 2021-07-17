package model;

public class Place {
    private long cityId;
    private String cityName;
    private String mustSee;
    private String mustDo;
    private String meal;
    private String dontForget;
    private String notes;
    private long personId;

    //not sure that i need this constructor
    public Place(long cityId, String cityName, String mustSee, String mustDo, String meal, String dontForget, String notes, long personId) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.mustSee = mustSee;
        this.mustDo = mustDo;
        this.meal = meal;
        this.dontForget = dontForget;
        this.notes = notes;
        this.personId = personId;
    }

    public Place() {

    }

    public long getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getMustSee() {
        return mustSee;
    }

    public String getMustDo() {
        return mustDo;
    }

    public String getMeal() {
        return meal;
    }

    public String getDontForget() {
        return dontForget;
    }

    public String getNotes() {
        return notes;
    }

    public long getPersonId() {
        return personId;
    }


    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setMustSee(String mustSee) {
        this.mustSee = mustSee;
    }

    public void setMustDo(String mustDo) {
        this.mustDo = mustDo;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public void setDontForget(String dontForget) {
        this.dontForget = dontForget;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }



}

