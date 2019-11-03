package com.gkatzioura.springdatareadreplica.datasource;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

public class RoundRobinPolicy implements LoadBalancingPolicy {

    protected final AtomicInteger roundRobinIndex = new AtomicInteger(-1);
    /**
     * We use this since it is a thread safe class
     */
    protected final CopyOnWriteArrayList<DataSource> activeNodes = new CopyOnWriteArrayList<>();

    @Override
    public void init(Collection<DataSource> nodes) {
        for(DataSource node: nodes) {
            /**
             * Check if active hikari
             */
            activeNodes.add(node);
        }
    }

    @Override
    public DataSource next() {
        int nodeIndex = index()%activeNodes.size();
        return activeNodes.get(nodeIndex);
    }

    private int index() {
        int currentIndex;
        int newIndex;

        do {
            currentIndex = roundRobinIndex.get();
            newIndex = currentIndex< Integer.MAX_VALUE ? currentIndex+1: 0;
        } while (!roundRobinIndex.compareAndSet(currentIndex, newIndex));

        return newIndex%maxIndex();
    }

    private final int maxIndex(){
        return activeNodes.size();
    }

    @Override
    public void onUp(DataSource node) {

    }

    @Override
    public void onDown(DataSource node) {

    }

    @Override
    public void close() throws Exception {

    }

}
