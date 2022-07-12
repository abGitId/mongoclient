package com.example.mongoclient.repository;

import com.example.mongoclient.domain.GroupByOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public class FlightRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    /*public List<GroupByOut> getCountOfFlightType(){
        Aggregation agg = newAggregation(
                match(Criteria.where("_id").lt(10)),
                group("hosting").count().as("total"),
                project("total").and("hosting").previousOperation(),
                sort(Sort.Direction.DESC, "total")

        );

        //Convert the aggregation result into a List
        AggregationResults<HostingCount> groupResults
                = mongoTemplate.aggregate(agg, Domain.class, HostingCount.class);
        List<HostingCount> result = groupResults.getMappedResults();
    }*/
}
