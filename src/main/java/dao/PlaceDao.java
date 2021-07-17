package dao;

import model.Place;

import java.util.List;

public interface PlaceDao {
    Place getPlace(long cityId);
    List<Place> getPlace(String cityName);
    void createPlace(Place place);
    void updatePlace(Place place);
    void deletePlace(long cityId);
}
