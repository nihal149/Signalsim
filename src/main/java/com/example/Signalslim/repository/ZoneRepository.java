package com.example.Signalslim.repository;

import com.example.Signalslim.model.Zone;
import com.example.Signalslim.model.ZoneCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {

    @Query("SELECT z FROM Zone z WHERE " +
            "(z.name LIKE %:#{#criteria.name}% OR :#{#criteria.name} IS NULL) AND " +
            "(z.latitude >= :#{#criteria.minLatitude} OR :#{#criteria.minLatitude} IS NULL) AND " +
            "(z.latitude <= :#{#criteria.maxLatitude} OR :#{#criteria.maxLatitude} IS NULL) AND " +
            "(z.longitude >= :#{#criteria.minLongitude} OR :#{#criteria.minLongitude} IS NULL) AND " +
            "(z.longitude <= :#{#criteria.maxLongitude} OR :#{#criteria.maxLongitude} IS NULL) AND " +
            "(z.type LIKE %:#{#criteria.type}% OR :#{#criteria.type} IS NULL)")
    List<Zone> findByCriteria(ZoneCriteria criteria);
}
