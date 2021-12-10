package com.ourapp.salonapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ourapp.salonapp.dto.Billdto;
import com.ourapp.salonapp.dto.SalonServiceDetaildto;
import com.ourapp.salonapp.dto.SalonServiceNamedto;
import com.ourapp.salonapp.entity.Customer;
import com.ourapp.salonapp.entity.SalonServiceDetail;
import com.ourapp.salonapp.repository.SalonServiceDetailRepository;

@Service
public class SalonServiceDetailService {

	    @Autowired
	    private SalonServiceDetailRepository salonServiceDetailRepository;
	    
	    @Autowired
	    private CustomerService customerService;
	    
	    @Autowired
	    private BasicServices basicServices;

        public SalonServiceDetail saveSalonServiceDetail(SalonServiceDetail salonServiceDetail) {
			return salonServiceDetailRepository.saveAndFlush(salonServiceDetail);
		}
        
		public SalonServiceDetail findByName(String name,String category) {
			
			return salonServiceDetailRepository.findByCategoryandName(name,category);
		}
		
		public List<SalonServiceDetaildto> findByNameType(String type, String category) {
			List<SalonServiceDetail> salonServices=salonServiceDetailRepository.findByCategoryNameandType(type,category);
			List<SalonServiceDetaildto> servicedto=new ArrayList<SalonServiceDetaildto>();
			for(SalonServiceDetail service:salonServices)
			{
				SalonServiceDetaildto dto=new SalonServiceDetaildto(service.getName(),service.getDescription(),service.getPrice(),service.getSalonServiceCategory(),service.getSalonServiceCategoryTypes(),service.getPictureLocation());
			    servicedto.add(dto);
			}
			return servicedto;
		}
		
		public String addSalonServiceDetail(SalonServiceNamedto salonServiceNamedto) {
			
        	Customer customer=customerService.findByEmail(basicServices.getCurrentUser());
			List<String> names=salonServiceNamedto.getNames();
			
			if(0==salonServiceNamedto.getType().compareTo("Male"))
			{
				for(String name:names)
				{
					SalonServiceDetail service=findByName(name,"Male");
					customer.addSalonservice(service);
					salonServiceDetailRepository.save(service);
				}
				
			}else
			{
				for(String name:names)
				{
					SalonServiceDetail service=findByName(name,"Female");
					customer.addSalonservice(service);
					salonServiceDetailRepository.save(service);
				}
				
			}
			   return "Added";			
		}
		public List<Billdto> getBill() {
        	Customer customer=customerService.findByEmail(basicServices.getCurrentUser());
        	List<SalonServiceDetail> salondetail=customer.getSalonServiceDetail();
        	Map<Long,Billdto> billdto=new HashMap<Long,Billdto>();
        	for(SalonServiceDetail service:salondetail)
        	{
        		Billdto bill=new Billdto(service.getId(),service.getName(),1L,service.getPrice(),service.getSalonServiceCategory());
        		if(billdto.containsKey(bill.getId()))
        		{
        			Billdto newbill=billdto.get(bill.getId());
        			bill.setCount(newbill.getCount()+1);
        			billdto.replace(bill.getId(), bill);
        		}
        		else
        		{
        			billdto.put(bill.getId(), bill);
        		}
        	}
			return new ArrayList<Billdto>(billdto.values());
		}
			
}
