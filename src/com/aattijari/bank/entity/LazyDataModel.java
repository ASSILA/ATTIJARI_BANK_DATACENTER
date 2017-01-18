package com.aattijari.bank.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;



import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.model.DataModel;

import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

public abstract class LazyDataModel<T> extends DataModel<T> implements SelectableDataModel<T>, Serializable {
		 
		
		 
		 public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
		        throw new UnsupportedOperationException("Lazy loading is not implemented.");
		    }
		 
		    public List<T> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String,String> filters) {
		        throw new UnsupportedOperationException("Lazy loading is not implemented.");
		    }
		 
		    public T getRowData(String rowKey) {
		        throw new UnsupportedOperationException("getRowData(String rowKey) must be implemented when basic rowKey algorithm is not used.");
		    }
		 
		    public Object getRowKey(T object) {
		        throw new UnsupportedOperationException("getRowKey(T object) must be implemented when basic rowKey algorithm is not used.");
		    }
		
		
	public LazyDataModel() {
		// TODO Auto-generated constructor stub
	}

}
