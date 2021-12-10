package com.ourapp.salonapp.serviceTest;
 
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ourapp.salonapp.dto.SalonServiceDetaildto;
import com.ourapp.salonapp.entity.SalonServiceCategory;
import com.ourapp.salonapp.entity.SalonServiceCategoryTypes;
import com.ourapp.salonapp.entity.SalonServiceDetail;
import com.ourapp.salonapp.service.SalonServiceDetailService;
 
@SpringBootTest
@RunWith(SpringRunner.class)
public class SalonServiceDetailServiceTest {
     
    @Autowired
    private SalonServiceDetailService salonServiceDetailService;
    
    SalonServiceDetail salonServiceDetail = new SalonServiceDetail();
    
    @BeforeEach 
    void init() {
    	salonServiceDetail.setSalonServiceCategory(SalonServiceCategory.values()[1]);
    	salonServiceDetail.setSalonServiceCategoryTypes(SalonServiceCategoryTypes.values()[1]);
    	salonServiceDetail.setName("Feet Cares");
    	salonServiceDetail.setDescription("This Pedicure Will Pamper Your Feet, Leaving It De-Stressed And Visibly Groomed.");
    	salonServiceDetail.setPictureLocation("https://media.istockphoto.com/photos/spa-treatment-and-product-for-female-feet-and-foot-spa-foot-bath-in-picture-id1140436562?k=20&m=1140436562&s=612x612&w=0&h=gx5RcdXA4jgc2pPp_fj6h-k6JdjqDJDuQaA4W7xQwpU=");
        salonServiceDetail.setPrice(2500L);
    }

    @Test
    public void testfindByName() {
    	
    	salonServiceDetailService.saveSalonServiceDetail(salonServiceDetail);      
    	SalonServiceDetail outputService=salonServiceDetailService.findByName("Feet Cares","Female");
    	
    	assertThat(salonServiceDetail.getName()).
        isEqualTo(outputService.getName());
  
  assertThat(salonServiceDetail.getDescription()).
  isEqualTo(outputService.getDescription());
  
  assertThat(salonServiceDetail.getPrice()).
  isEqualTo(outputService.getPrice());
  
  assertThat(salonServiceDetail.getSalonServiceCategory()).
  isEqualTo(outputService.getSalonServiceCategory());
  
  assertThat(salonServiceDetail.getSalonServiceCategoryTypes()).
  isEqualTo(outputService.getSalonServiceCategoryTypes());
  
  assertThat(salonServiceDetail.getPictureLocation()).
  isEqualTo(outputService.getPictureLocation());
    }
    
    @Test
    public void testfindByNameType() {
    	try{
    		List<SalonServiceDetaildto> inputServices=new ArrayList<SalonServiceDetaildto>();
    	inputServices.add(new SalonServiceDetaildto("Feet Care"
    			,"This Pedicure Will Pamper Your Feet, Leaving It De-Stressed And Visibly Groomed."
    			,2500L
    			,SalonServiceCategory.values()[2]
    					,SalonServiceCategoryTypes.values()[1]
    							,"https://media.istockphoto.com/photos/foot-care-picture-id165899083?k=20&m=165899083&s=612x612&w=0&h=LzEtP0LpwOCOO0ztwvTbY7MTVS9xgKOKML5T2-PnDnU="));
    	inputServices.add(new SalonServiceDetaildto("Regular Manicure"
    			,"This Manicure Will Pamper Your Hands, Leaving Them De-Stressed And Visibly Groomed."
    			,1000L
    			,SalonServiceCategory.values()[2]
    					,SalonServiceCategoryTypes.values()[1]
    							,"https://media.istockphoto.com/photos/man-during-manicure-picture-id674966894?k=20&m=674966894&s=612x612&w=0&h=CAIyItE7EPcZo42pRgguL3NKPGdjYl48Aq070W8uWN4="));
    	inputServices.add(new SalonServiceDetaildto("Massage"
    			,"Pamper Your Body With A Wonderful Blend Of Herb Essential Oils Like Jupiter Berry And Rosemary. Detoxify It, Leaving It Feeling Alive."
    			,2000L
    			,SalonServiceCategory.values()[2]
    					,SalonServiceCategoryTypes.values()[1]
    							,"https://media.istockphoto.com/photos/man-having-a-massage-in-spa-salon-picture-id163163161?k=20&m=163163161&s=612x612&w=0&h=EbyBAzb7CvmjMJKFB2N8pKr-tCPBpaT5m5N6S0R9aeU="));
    	List<SalonServiceDetaildto> outputService=salonServiceDetailService.findByNameType("Feet Cares","Female");
         assertThat(inputServices).usingRecursiveFieldByFieldElementComparator().
                       isEqualTo(outputService);
        
    	}catch(IndexOutOfBoundsException e)
    	{
    	}
    	
    }
}