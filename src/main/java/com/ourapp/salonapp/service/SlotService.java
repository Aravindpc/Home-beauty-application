package com.ourapp.salonapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ourapp.salonapp.entity.Slot;
import com.ourapp.salonapp.repository.SlotRepository;

@Service
public class SlotService {

	    @Autowired
	    private SlotRepository slotRepository;

		public Slot save(Slot slot) {
			return slotRepository.saveAndFlush(slot);
		}
		public Slot getSlotbyCustomerId(Long customerid)
		{
			return slotRepository.getbyCustomerId(customerid);
		}
}
