package com.fixapi.SessionModel.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.logging.log4j.util.Strings;

import com.google.common.base.Preconditions;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"clusterName" , "fix_function_id"})})
public class FixCluster {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clusterName;
    private String description;
    private Integer port;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private FixFunction fixFunction;
    
    @SuppressWarnings("unused")
    private FixCluster() {
        
    }
    
    public FixCluster(String clusterName, FixFunction fixFunction) {
        super();
        Preconditions.checkArgument(Strings.isNotBlank(clusterName));
        Preconditions.checkNotNull(fixFunction);
        
        this.clusterName = clusterName;
        this.fixFunction = fixFunction;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getClusterName() {
        return clusterName;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setPort(Integer port) {
        this.port = port;
    }
    
    public Integer getPort() {
        return port;
    }
    
    public FixFunction getFixFunction() {
        return this.fixFunction;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((clusterName == null) ? 0 : clusterName.hashCode());
        result = prime * result + ((fixFunction == null) ? 0 : fixFunction.hashCode());
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
        FixCluster other = (FixCluster) obj;
        if (clusterName == null) {
            if (other.clusterName != null)
                return false;
        } else if (!clusterName.equals(other.clusterName))
            return false;
        if (fixFunction == null) {
            if (other.fixFunction != null)
                return false;
        } else if (!fixFunction.equals(other.fixFunction))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FixCluster [id=" + id + ", clusterName=" + clusterName + ", description=" + description + ", port="
                + port + ", fixFunction=" + fixFunction + "]";
    }

    
}
