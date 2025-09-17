package com.AiFitness.activityService.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.AiFitness.activityService.models.Activity;

@Repository
public interface ActivityRespository extends MongoRepository<Activity, String> {


    Activity save(Activity activity);
}
