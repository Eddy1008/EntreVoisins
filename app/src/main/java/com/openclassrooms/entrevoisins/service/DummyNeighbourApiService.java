package com.openclassrooms.entrevoisins.service;

import android.util.Log;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    // Eddy 02/11 update neighbour with isFavorite
    @Override
    public void updateNeighbour(Neighbour neighbour) {
        int indexNeighbour = neighbours.indexOf(neighbour);
        neighbours.set(indexNeighbour, neighbour);
    }

    // Eddy 03/11 Create a list with Neighbour isFavorite true
    @Override
    public List<Neighbour> getFavoritesNeighbours() {
        List<Neighbour> favoriteNeighbours = new ArrayList<>();
        for (int i=0; i<neighbours.size(); i++)
        {
            if (neighbours.get(i).isFavorite() ) {
                favoriteNeighbours.add(neighbours.get(i));
            }
        }
        return favoriteNeighbours;
    }
}
