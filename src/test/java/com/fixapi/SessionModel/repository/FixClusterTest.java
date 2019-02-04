package com.fixapi.SessionModel.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.fixapi.SessionModel.domain.FixCluster;
import com.fixapi.SessionModel.domain.FixFunction;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FixClusterTest {

    @Autowired
    private FixClusterRepository clusterRepository;
    
    @Autowired
    private FixFunctionRepository functionRepository;
    
    private String clusterName = "STANDARD1";
    private String otherClusterName = "VIP1";
    private String clusterDescription = "Description Cluster";
    private String otherClusterDescription = "Description Cluster 2";
    private Integer clusterPort = 10;
    private Integer otherClusterPort = 20;
    private String fixFunctionName = "OTC Market Data";
    private String otherFixFunctionName = "OTC Trading";
    
    private FixFunction fixFunction;
    
    @Before
    public void initialiseTest() {
        fixFunction = new FixFunction(fixFunctionName);
        functionRepository.saveAndFlush(fixFunction);
    }
    
    @Test
    public void addVanillaCluster() {
        FixCluster cluster = new FixCluster(clusterName, fixFunction);
        clusterRepository.saveAndFlush(cluster);
        
        List<FixCluster> clusters = clusterRepository.findAll();
        
        assertTrue(clusters.size() == 1);
        assertEquals(cluster, clusters.get(0));
    }

    @Test(expected=DataIntegrityViolationException.class)
    public void nameAndFunctionAreLogicalPrimaryKeysForCluster() {
        FixCluster cluster = new FixCluster(clusterName, fixFunction);
        cluster.setDescription(clusterDescription);
        cluster.setPort(clusterPort);
        clusterRepository.saveAndFlush(cluster);
        
        FixFunction otherFunction = new FixFunction(fixFunctionName);
        FixCluster otherCluster = new FixCluster(clusterName, otherFunction);
        otherCluster.setDescription(otherClusterDescription);
        otherCluster.setPort(otherClusterPort);
        clusterRepository.saveAndFlush(otherCluster);
    }
    
    @Test
    public void clusterCanBeUpdated() {
        FixCluster cluster = new FixCluster(clusterName, fixFunction);
        cluster.setDescription(clusterDescription);
        cluster.setPort(clusterPort);
        clusterRepository.saveAndFlush(cluster);
        
        cluster.setDescription(otherClusterDescription);
        cluster.setPort(otherClusterPort);

        List<FixCluster> clusters = clusterRepository.findAll();
        assertTrue(clusters.size() == 1);
        assertEquals(clusterName, clusters.get(0).getClusterName());
        assertEquals(fixFunction, clusters.get(0).getFixFunction());
        assertEquals(otherClusterDescription, clusters.get(0).getDescription());
        assertEquals(otherClusterPort, clusters.get(0).getPort());
    }
    
    @Test
    public void cascadePersistanceOfFixFunctionWorks() {
        FixFunction otherFixFunction = new FixFunction(otherFixFunctionName);
        FixCluster cluster = new FixCluster(otherClusterName, otherFixFunction);
        cluster.setDescription(clusterDescription);
        cluster.setPort(clusterPort);
        clusterRepository.saveAndFlush(cluster);
        
        List<FixCluster> clusters = clusterRepository.findAll();
        assertTrue(clusters.size() == 1);
        assertEquals(otherFixFunction, clusters.get(0).getFixFunction());
        assertTrue(functionRepository.findAll().size() == 2);
        assertTrue(functionRepository.findAll().contains(otherFixFunction));
    }
    
    @Test
    public void clusterCanBeDeleted() {
        FixCluster cluster = new FixCluster(clusterName, fixFunction);
        cluster.setDescription(clusterDescription);
        cluster.setPort(clusterPort);
        clusterRepository.saveAndFlush(cluster);
        List<FixCluster> clusters = clusterRepository.findAll();
        assertTrue(clusters.size() == 1);
        
        clusterRepository.delete(clusters.get(0));
        
        assertEquals(0, clusterRepository.findAll().size());
    }
}
