package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void createNeighbourWithSuccess() {
        service.getNeighbours().clear();
        Neighbour neighbour1 = new Neighbour(
                13,
                "Eddy",
                "https://i.pravatar.cc/150?u=a042581f4e29026704d",
                "Saint-Pierre-du-Mont ; 5km",
                "+33 6 86 57 90 14",
                "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..",
                false);
        service.createNeighbour(neighbour1);
        assertEquals(1, service.getNeighbours().size());
    }

    @Test
    public void updateNeighbourWithSuccess() {
        Neighbour neighbourToUpdate = service.getNeighbours().get(0);
        assertFalse("Updated name".equals(service.getNeighbours().get(0).getName()));
        neighbourToUpdate.setName("Updated name");
        service.updateNeighbour(neighbourToUpdate);
        assertEquals("Updated name", service.getNeighbours().get(0).getName());
    }

    @Test
    public void getFavoritesNeighboursWithSuccess() {
        assertEquals(0, service.getFavoritesNeighbours().size());
        Neighbour neighbourToAddToFavoriteList = new Neighbour(
                14,
                "Harry Potter",
                "https://i.pravatar.cc/150?u=a042581f4e29026704d",
                "Saint-Pierre-du-Mont ; 5km",
                "+33 6 86 57 90 14",
                "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..",
                true);
        service.createNeighbour(neighbourToAddToFavoriteList);
        Neighbour neighbourToNotAddFavoriteList = new Neighbour(
                15,
                "Draco Malfoy",
                "https://i.pravatar.cc/150?u=a042581f4e29026704d",
                "Poudlard chambre commune des Serpentard",
                "+33 6 86 57 90 14",
                "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..",
                false);
        service.createNeighbour(neighbourToNotAddFavoriteList);
        List<Neighbour> favoritesNeighbours = service.getFavoritesNeighbours();
        assertEquals(1, favoritesNeighbours.size());
    }
}
