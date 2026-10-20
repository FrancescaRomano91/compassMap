package com.example.demo.service;

import java.util.Optional;
import java.util.UUID;
import com.example.demo.model.RoadMap;
import com.example.demo.repository.RoadMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoadMapService {

    @Autowired
    private RoadMapRepository roadMapRepository;


    public RoadMap createRoadMap (RoadMap roadMap){
        // SET ID roadMap to UUID
        roadMap.setId(UUID.randomUUID().toString());

        // save RoadMap in database
        RoadMap createdRoadMap = roadMapRepository.save(roadMap);
        System.out.println("Service created roadMap: " +  createdRoadMap);
        return createdRoadMap;
    }

    public Iterable<RoadMap> getAll() {
        return roadMapRepository.findAll();
    }

    public void deleteById (String id){
        roadMapRepository.deleteById(id);
    }

    public RoadMap getById(String id) {

        Optional<RoadMap> existing = roadMapRepository.findById(id);

        return existing.orElse(null);
    }


    public RoadMap updateById(RoadMap incoming) {
        if (incoming.getId() == null) {
            return null;
        }
        RoadMap existing = getById(incoming.getId());
        if (existing == null) {
            return null;
        }
        // merge only non-null fields from incoming (partial update semantics)
        if (incoming.getId() != null) {
            existing.setId(incoming.getId());
        }
        if (incoming.getName() != null) {
            existing.setName(incoming.getName());
        }
        if (incoming.getDescription() != null) {
            existing.setDescription(incoming.getDescription());
        }

        return roadMapRepository.save(existing);
    }







    // to do: createRoadMap, getAllRoadMaps, getRoadMapById,
    // to do: deleteRoadMapById, deleteAllRoadMaps, countRoadMaps


}
