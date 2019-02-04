package com.fixapi.SessionModel.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.logging.log4j.util.Strings;

import com.google.common.base.Preconditions;

@Entity
public class FixFunction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String functionName;
    private String description;
    private String hostName;
    
    @SuppressWarnings("unused")
    private FixFunction() {
        
    }
    
    public FixFunction(String functionName) {
        super();
        Preconditions.checkArgument(Strings.isNotBlank(functionName));
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return functionName;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
    
    public String getHostName() {
        return hostName;
    }
    
    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((functionName == null) ? 0 : functionName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FixFunction other = (FixFunction) obj;
        if (functionName == null) {
            if (other.functionName != null)
                return false;
        } else if (!functionName.equals(other.functionName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FixFunction [functionName=" + functionName + ", description=" + description + ", hostName=" + hostName
                + "]";
    }
}
