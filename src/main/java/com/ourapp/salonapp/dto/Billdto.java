package com.ourapp.salonapp.dto;

import com.ourapp.salonapp.entity.SalonServiceCategory;

public class Billdto {


	public Billdto() {
		super();
	}
	public Billdto(Long id, String name, Long count, Long price,
			SalonServiceCategory salonServiceCategory) {
		super();
		this.id = id;
		this.name = name;
		this.count = count;
		this.price = price;
		this.total_price = count*price;
		this.salonServiceCategory = salonServiceCategory;
	}
	private Long id;
	private String name;
	private Long count;
	private Long price;
	private Long total_price;
	private SalonServiceCategory salonServiceCategory;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
		this.total_price=count*this.price;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getTotal_price() {
		return total_price;
	}
	public void setTotal_price(Long total_price) {
		this.total_price = total_price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	 @Override
	  public boolean equals(Object v) {
	        boolean retVal = false;

	        if (v instanceof Billdto){
	            Billdto ptr = (Billdto) v;
	            retVal = ptr.id.longValue() == this.id;
	        }

	     return retVal;
	  }

	    @Override
	    public int hashCode() {
	        int hash = 7;
	        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
	        return hash;
	    }

		@Override
		public String toString() {
			return "Billdto [id=" + id + ", name=" + name + ", count=" + count + ", price=" + price + ", total_price="
					+ total_price + "]";
		}
		public SalonServiceCategory getSalonServiceCategory() {
			return salonServiceCategory;
		}
		public void setSalonServiceCategory(SalonServiceCategory salonServiceCategory) {
			this.salonServiceCategory = salonServiceCategory;
		}
}
