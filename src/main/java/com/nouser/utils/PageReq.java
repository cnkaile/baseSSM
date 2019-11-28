package com.nouser.utils;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageReq implements Pageable, Serializable {

	private static final long serialVersionUID = 8845067178916675512L;
	
	/** 默认第0页 */
	private static final int DEFAULT_PAGE = 0;
	
	/** 默认每页6条 */
	private static final int DEFAULT_SIZE = 6;
	
	private int page;
	private int size;
	private Sort sort;

	/**
	 * Creates a new {@link PageReq}. Pages are one indexed, thus providing 1 for {@code page} will return the first
	 * page.
	 * 
	 */
	public PageReq() {

		this(DEFAULT_PAGE,DEFAULT_SIZE,null);
	}
	
	/**
	 * Creates a new {@link PageReq}. Pages are one indexed, thus providing 1 for {@code page} will return the first
	 * page.
	 * 
	 * @param size
	 * @param page
	 */
	public PageReq(int page, int size) {
		
		this(page, size, null);
	}

	/**
	 * Creates a new {@link PageReq} with sort parameters applied.
	 * 
	 * @param page
	 * @param size
	 * @param direction
	 * @param properties
	 */
	public PageReq(int page, int size, Direction direction, String... properties) {

		this(page, size, new Sort(direction, properties));
	}

	/**
	 * Creates a new {@link PageReq} with sort parameters applied.
	 * 
	 * @param page
	 * @param size
	 * @param sort
	 */
	public PageReq(int page, int size, Sort sort) {

		if (0 > page) {
			throw new IllegalArgumentException("Page index must not be less than zero!");
		}

		if (0 >= size) {
			throw new IllegalArgumentException("Page size must not be less than or equal to zero!");
		}

		this.page = page;
		this.size = size;
		this.sort = sort;
	}

	public int getPageSize() {
		return size;
	}

	public int getPageNumber() {

		return page;
	}

	public int getOffset() {

		return page * size;
	}

	public Sort getSort() {

		return sort;
	}

	public void setPageSize(Integer size) {
		if (size == null || size < 1) {
			this.size = DEFAULT_SIZE;
		} else {
			this.size = size;
		}
	}

	public void setPageNumber(Integer page) {
		if (page == null || page < 0) {
			this.page = DEFAULT_PAGE;
		} else {
			this.page = page;
		}
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PageReq)) {
			return false;
		}

		PageReq that = (PageReq) obj;

		boolean pageEqual = this.page == that.page;
		boolean sizeEqual = this.size == that.size;

		boolean sortEqual = this.sort == null ? that.sort == null : this.sort.equals(that.sort);

		return pageEqual && sizeEqual && sortEqual;
	}

	@Override
	public int hashCode() {

		int result = 17;

		result = 31 * result + page;
		result = 31 * result + size;
		result = 31 * result + (null == sort ? 0 : sort.hashCode());

		return result;
	}

	public Pageable next() {
		// TODO Auto-generated method stub
		return null;
	}

	public Pageable previousOrFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	public Pageable first() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return false;
	}
}
