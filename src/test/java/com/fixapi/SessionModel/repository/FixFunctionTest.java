package com.fixapi.SessionModel.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.fixapi.SessionModel.domain.FixFunction;
import com.fixapi.SessionModel.repository.FixFunctionRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FixFunctionTest {

    @Autowired
    private FixFunctionRepository functionRepository;
    
    private String functionName = "OTC Market Data";
    private String description = "Market Data Function";
    private String hostName = "Dummy Host";
    private String otherDescription = "Market Data Function 2";
    private String otherHost = "Dummy Host 2";
    
    
    @Test
    public void addVanillaFunction() {

        FixFunction function = new FixFunction(functionName);
        function.setDescription(description);
        function.setHostName(hostName);
        
        functionRepository.saveAndFlush(function);
        
        Optional<FixFunction> opFixFunction = functionRepository.findByFunctionName(functionName);
        assertTrue(opFixFunction.isPresent());
        assertEquals(function, opFixFunction.get());
    }
    
    @Test(expected=DataIntegrityViolationException.class)
    public void functionNameCannotBeDuplicated() {
        
        FixFunction function = new FixFunction(functionName);
        function.setDescription(description);
        function.setHostName(hostName);
        
        functionRepository.saveAndFlush(function);
        FixFunction otherFunction = new FixFunction(functionName);
        otherFunction.setDescription(otherDescription);
        otherFunction.setHostName(otherHost);
        functionRepository.saveAndFlush(otherFunction);
    }

}
