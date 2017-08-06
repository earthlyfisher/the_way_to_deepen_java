package com.earthlyfish.spring.stereotype;

import java.util.Map;

@SuppressWarnings("rawtypes")
public class Bean {

    /**
     * �����
     */
    private Class cls;

    private String classpath;

    private String beanName;

    /**
     * beanName,obj.
     */
    private Map<String, Object> instances;

    private Map<String, Bean> fieldMap;

    /**
     * url:methodName
     */
    private Map<String, String> urlMap;

    private boolean primary;

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    public String getClasspath() {
        return classpath;
    }

    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Map<String, Object> getInstances() {
        return instances;
    }

    public void setInstances(Map<String, Object> instances) {
        this.instances = instances;
    }

    public Map<String, Bean> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, Bean> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public Map<String, String> getUrlMap() {
        return urlMap;
    }

    public void setUrlMap(Map<String, String> urlMap) {
        this.urlMap = urlMap;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Bean) {
            Bean obj2 = (Bean) obj;
            if (this.getCls() == obj2.getCls() && this.getClasspath() == obj2.getClasspath()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getClasspath().hashCode();
    }

    @Override
    public String toString() {
        return "Bean [cls=" + cls + ", classpath=" + classpath + ", beanName=" + beanName + ", instances=" + instances
                + ", fieldLst=" + fieldMap + ", urlLst=" + urlMap + "]";
    }
}
