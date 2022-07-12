package com.example.mongoclient.repository;

import com.example.mongoclient.domain.Flights;
import com.example.mongoclient.domain.GroupByOut;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface FlightsReository extends MongoRepository<Flights, String> {
    List<Flights> findByDelayed(boolean b);

    List<Flights> findByDurationMinLessThan(int minTime);

    @Query(value = "{'departure.code':'?0'}", sort = "{'departureDate':1}")
    List<Flights> findByDepartureCity(String cityCode);


    @Aggregation("{ '$group' : { '_id' : null, 'total' : { $sum: 1 } } }")
    long getFlightsCount();

    //@Aggregation("[ { '$group': { '_id': '$type', 'count': { '$sum': 1 } } }, { '$sort': { 'count': 1 } } ]")
    @Aggregation("{ '$group': { '_id': '$type', 'count': { '$sum': 1 } } }")
    List<GroupByOut> getCountOfFlightType();


}
