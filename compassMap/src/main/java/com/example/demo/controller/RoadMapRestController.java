package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.RoadMap;
import com.example.demo.service.RoadMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roadmaps")
public class RoadMapRestController {

    @Autowired
    private RoadMapService roadMapService;

    //----------------------------- CREATE -----------------------------

    @PostMapping("/create")
    public RoadMap createRoadMap(@RequestBody RoadMap roadMap) {
        RoadMap createdRoadMap = roadMapService.createRoadMap(roadMap);
        System.out.println("Service createRoadMap: " + createdRoadMap);
        return createdRoadMap;
    }

    //----------------------------- GET -----------------------------
    @GetMapping("/all")
    public Iterable<RoadMap> getAll(){
        Iterable<RoadMap> roadMaps =  roadMapService.getAll();
        System.out.println("Service getAllRoadMaps: " + roadMaps);
        return roadMaps;

    }

    //----------------------------- DELETE -----------------------------
    @DeleteMapping("/byId")
    public RoadMap deleteById(@RequestParam String id){

        RoadMap foundRoadMap = roadMapService.getById(id);
        if (foundRoadMap == null) return null;
        else {
            roadMapService.deleteById(id);
            return foundRoadMap;
            }


    }
    //----------------------------- UPDATE -----------------------------
    @PutMapping("/update")
    public RoadMap updateById(@RequestBody RoadMap roadMap){

        if (roadMap.getId() == null || roadMapService.getById(roadMap.getId()) == null) {
            return null;
        }
            RoadMap updatedRoadMap = roadMapService.updateById(roadMap);
            System.out.println("Service updateCustomer: " + updatedRoadMap);
            return updatedRoadMap;
    }
}

