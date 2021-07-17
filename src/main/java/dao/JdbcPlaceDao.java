package dao;

import model.Place;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPlaceDao implements PlaceDao {
    private final JdbcTemplate jdbcTemplate;
    //do i need to add person and place

    public JdbcPlaceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override //Not sure I need to search by city id?
    public Place getPlace(long cityId) {
        Place place = null;
        String sql = "SELECT city_id, city_name, person_id, must_see, must_do, meal, dont_forget, notes, person_first, person_last" +
                "FROM place " +
                "INNER JOIN person ON place.person_id = person.person_id " +
                "WHERE cityId = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cityId);
        if (results.next()) {
            place = mapRowToPlace(results);
        }

        return place;
    }

    //Should be able to search by city name
    public Place getPlaceByCityName(String cityName) {
        Place place = null;
        String sql = "SELECT city_id, city_name, person_id, must_see, must_do, meal, dont_forget, notes, person_first, person_last" +
                "FROM place " +
                "INNER JOIN person ON place.person_id = person.person_id " +
                "WHERE cityName ILIKE ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cityName);
        if (results.next()) {
            place = mapRowToPlace(results);
        }
        return place;
    }

    //Should be able to see the list of recommendations by city name (ie if there are multiple recommendations for 1 city)
    @Override
    public List<Place> getPlace(String cityName) {
        List<Place> city = new ArrayList<>();
        String sql = "SELECT city_id, city_name, person_id, must_see, must_do, meal, dont_forget, notes " +
                "FROM place WHERE city_name ILIKE ?;";
        String searchCity = "%" + cityName + "%";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, searchCity);
        while (results.next()) {
        }
        return city;
    }

    //Should be able to see full list of city recommendations
    public List<Place> getAllPlaces() {
        List<Place> city = new ArrayList<>();
        String sql = "SELECT city_id, city_name, person_id, must_see, must_do, meal, dont_forget, notes " +
                "FROM place; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
        }
        return city;
    }

    //Should be able to create a place
    @Override
    public void createPlace(Place place) {
        String sql = "INSERT INTO place(city_name, must_see, must_do, meal, dont_forget, notes, person_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) ;";
        jdbcTemplate.update(sql, place.getCityName(), place.getMustSee(), place.getMustDo(), place.getMeal(), place.getDontForget(), place.getNotes(), place.getPersonId());
    }

    //Should be able to update place
    @Override
    public void updatePlace(Place place) {
        String sql = "UPDATE place " +
                "SET (city_name, must_see, must_do, meal, dont_forget, notes) = (?, ?, ?, ?, ?, ?, ) " +
                "WHERE city_name ILIKE ?;";
        String updateCity = "%" + place + "%";
        jdbcTemplate.update(sql, place.getCityName(), place.getMustSee(), place.getMustDo(), place.getMeal(), place.getDontForget(), place.getNotes());
    }

    //Should be able to delete place
    //Okay to delete by id?
    @Override
    public void deletePlace(long cityId) {
        //should this be by city id or name?
        String sql = "DELETE FROM place WHERE city_id = ? ;";
        jdbcTemplate.update(sql, cityId);

    }

    private Place mapRowToPlace(SqlRowSet rowSet) {
        Place place = new Place();
        place.setCityId(rowSet.getLong("city_id"));
        place.setCityName(rowSet.getString("city_name"));
        place.setMustSee(rowSet.getString("must_see"));
        place.setMustDo(rowSet.getString("must_do"));
        place.setMeal(rowSet.getString("meal"));
        place.setDontForget(rowSet.getString("dont_forget"));
        place.setNotes(rowSet.getString("notes"));
        place.setPersonId(rowSet.getLong("person_id"));
        return place;
    }

}

