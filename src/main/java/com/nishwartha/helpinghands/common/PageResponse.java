package com.nishwartha.helpinghands.common;

import java.util.List;

public class PageResponse<T> {
	public Long totalProperty;
	public List<T> rootProperty;

	public PageResponse() {

	}

	public PageResponse(Long totalProperty, List<T> rootProperty) {
		this.totalProperty = totalProperty;
		this.rootProperty = rootProperty;
	}

	/**
	 * @return the totalProperty
	 */
	public Long getTotalProperty() {
		return totalProperty;
	}

	/**
	 * @param totalProperty
	 *            the totalProperty to set
	 */
	public void setTotalProperty(Long totalProperty) {
		this.totalProperty = totalProperty;
	}

	/**
	 * @return the rootProperty
	 */
	public List<T> getRootProperty() {
		return rootProperty;
	}

	/**
	 * @param rootProperty
	 *            the rootProperty to set
	 */
	public void setRootProperty(List<T> rootProperty) {
		this.rootProperty = rootProperty;
	}

}
