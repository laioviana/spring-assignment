package com.wccgroup.assignment.service;

import com.wccgroup.assignment.entity.Location;
import com.wccgroup.assignment.entity.dto.DistanceCalculationResponse;
import com.wccgroup.assignment.entity.dto.LocationDTO;
import com.wccgroup.assignment.mock.LocationMock;
import com.wccgroup.assignment.repository.LocationRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.nCopies;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Runs all tests for service layer of Location entity")
class LocationServiceTest {

    private LocationService service;
    private LocationRepository locationRepository;

    @BeforeEach
    void beforeEach() {
        locationRepository = Mockito.mock(LocationRepository.class);
        service = new LocationService(locationRepository);
    }

    @Test
    @DisplayName("should calculate distance between two valid inputs")
    void calculateDistanceOfTwoValidInputs() {
        Mockito.when(locationRepository.findLocationByPostcode("AB12 1AB")).thenReturn(LocationMock.getOne());
        Mockito.when(locationRepository.findLocationByPostcode("AC12 1AC")).thenReturn(LocationMock.getOther());

        Optional<DistanceCalculationResponse> distanceBetweenLocations = service.getDistanceBetweenLocations("AB12 1AB","AC12 1AC");

        assert(distanceBetweenLocations.isPresent());
        assertEquals(distanceBetweenLocations.get().getDistance(),112.69509540413358);

        Mockito.verify(locationRepository).findLocationByPostcode("AB12 1AB");
        Mockito.verify(locationRepository).findLocationByPostcode("AC12 1AC");
    }

    @Test
    @DisplayName("should return empty for one valid input (first)")
    void calculateDistanceOfInvalidInput() {
        Mockito.when(locationRepository.findLocationByPostcode("AB12 1AB")).thenReturn(Optional.empty());

        Optional<DistanceCalculationResponse> distanceBetweenLocations = service.getDistanceBetweenLocations("AB12 1AB","AC12 1AC");

        assert(distanceBetweenLocations.isEmpty());
        Mockito.verify(locationRepository).findLocationByPostcode("AB12 1AB");
    }

    @Test
    @DisplayName("should return empty for one valid input (second)")
    void calculateDistanceOfInvalidInputSecond() {
        Mockito.when(locationRepository.findLocationByPostcode("AB12 1AB")).thenReturn(LocationMock.getOne());
        Mockito.when(locationRepository.findLocationByPostcode("AC12 1AC")).thenReturn(Optional.empty());

        Optional<DistanceCalculationResponse> distanceBetweenLocations = service.getDistanceBetweenLocations("AB12 1AB","AC12 1AC");

        assert(distanceBetweenLocations.isEmpty());
        Mockito.verify(locationRepository).findLocationByPostcode("AB12 1AB");
        Mockito.verify(locationRepository).findLocationByPostcode("AC12 1AC");
    }

    @Test
    @DisplayName("should return distance 0 for the same post code)")
    void calculateDistanceSamePostCode() {
        Mockito.when(locationRepository.findLocationByPostcode("AB12 1AB")).thenReturn(LocationMock.getOne());

        Optional<DistanceCalculationResponse> distanceBetweenLocations = service.getDistanceBetweenLocations("AB12 1AB","AB12 1AB");

        assert(distanceBetweenLocations.isPresent());
        assertEquals(distanceBetweenLocations.get().getDistance(),0.0);
        Mockito.verify(locationRepository, Mockito.times(2)).findLocationByPostcode("AB12 1AB");
    }


    @Test
    @DisplayName("should update an location sucessfully")
    void updateLocationSucessfully() {
        Optional<Location> location = LocationMock.getOne();
        Mockito.when(locationRepository.findById(location.get().getId())).thenReturn(location);

        LocationDTO locationDTO = new LocationDTO(99.0000,-7.0000);
        location.get().setLatitude(locationDTO.latitude());
        location.get().setLongitude(locationDTO.longitude());
        Mockito.when(locationRepository.save(location.get())).thenReturn(location.get());

        Optional<Location> result = service.updateLocation(location.get().getId(), locationDTO);

        assertEquals(location, result);

        Mockito.verify(locationRepository).findById(location.get().getId());
        Mockito.verify(locationRepository).save(location.get());
    }
  /*  @Test
    @DisplayName("should find all stuffs with one result")
    void findAllWithOneResult() {
        Stuff stuff = StuffMock.getOne();

        Mockito.when(stuffRepository.findAll())
                .thenReturn(Lists.list(stuff));

        List<Stuff> stuffs = service.findAll();

        assertFalse(stuffs.isEmpty());

        stuffs.forEach(it -> {
            assertEquals(stuff.getId(), it.getId());
            assertEquals(stuff.getName(), it.getName());
        });

        Mockito.verify(stuffRepository).findAll();
    }

    @Test
    @DisplayName("should find all stuffs with three results")
    void findAllWithThreeResults() {
        List<Stuff> stuffs = nCopies(3, StuffMock.getOne());

        Mockito.when(stuffRepository.findAll())
                .thenReturn(stuffs);

        List<Stuff> result = service.findAll();

        assertFalse(result.isEmpty());

        for (int i = 0; i < stuffs.size(); i++) {
            assertEquals(stuffs.get(i).getId(), result.get(i).getId());
            assertEquals(stuffs.get(i).getName(), result.get(i).getName());
        }

        Mockito.verify(stuffRepository).findAll();
    }

    @Test
    @DisplayName("should find a stuff by id successfully")
    void findByIdSuccessfully() {
        Stuff stuff = StuffMock.getOne();

        Mockito.when(stuffRepository.findById(stuff.getId()))
                .thenReturn(Optional.of(stuff));

        Stuff result = service.findBy(stuff.getId());

        assertNotNull(result);
        assertEquals(stuff, result);

        Mockito.verify(stuffRepository).findById(stuff.getId());
    }

    @Test
    @DisplayName("should not find an stuff by id")
    void notFindById() {
        Stuff stuff = StuffMock.getOne();

        Mockito.when(stuffRepository.findById(stuff.getId())).thenReturn(Optional.empty());

        assertThrows(StuffNotFoundException.class, () -> service.findBy(stuff.getId()));

        Mockito.verify(stuffRepository).findById(stuff.getId());
    }

    @Test
    @DisplayName("should save a new stuff")
    void saveNewStuffSuccessfully() {
        Stuff stuff = StuffMock.getOne();
        Mockito.when(stuffRepository.save(stuff)).thenReturn(stuff);

        Stuff result = service.save(stuff);

        assertEquals(stuff, result);

        Mockito.verify(stuffRepository).save(stuff);
    }

    @Test
    @DisplayName("should update an stuff sucessfully")
    void updateStuffSucessfully() {
        Stuff stuff = StuffMock.getOne();

        Mockito.when(stuffRepository.findById(stuff.getId())).thenReturn(Optional.of(stuff));
        Mockito.when(stuffRepository.save(stuff)).thenReturn(stuff);

        Stuff result = service.update(stuff.getId(), stuff);

        assertEquals(stuff, result);

        Mockito.verify(stuffRepository).findById(stuff.getId());
        Mockito.verify(stuffRepository).save(stuff);
    }

    @Test
    @DisplayName("should not update an stuff when it does not exist")
    void notUpdateStuffWhenItDoesNotExist() {
        Stuff stuff = StuffMock.getOne();

        Mockito.when(stuffRepository.findById(stuff.getId())).thenReturn(Optional.empty());

        assertThrows(StuffNotFoundException.class, () -> service.update(stuff.getId(), stuff));

        Mockito.verify(stuffRepository).findById(stuff.getId());
        Mockito.verify(stuffRepository, Mockito.times(0)).save(stuff);
    }*/
}
