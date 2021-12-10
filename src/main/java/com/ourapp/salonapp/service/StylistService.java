package com.ourapp.salonapp.service;

import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ourapp.salonapp.Exception.BadMessageException;
import com.ourapp.salonapp.dto.Admindto;
import com.ourapp.salonapp.dto.Billdto;
import com.ourapp.salonapp.dto.Maildto;
import com.ourapp.salonapp.dto.Slotdto;
import com.ourapp.salonapp.dto.Stylistsubdto;
import com.ourapp.salonapp.entity.Customer;
import com.ourapp.salonapp.entity.SalonServiceDetail;
import com.ourapp.salonapp.entity.Slot;
import com.ourapp.salonapp.entity.Stylist;
import com.ourapp.salonapp.repository.StylistRepository;

@Service
public class StylistService {

	@Autowired
	private StylistRepository stylistRepository;

	@Autowired
	private SlotService slotService;

	@Autowired
	private MailService mailService;

	@Autowired
	private BasicServices basicServices;

	@Autowired
	private CustomerService customerService;

	public List<Stylistsubdto> getAllStylists() {
		List<Stylist> stylists = stylistRepository.findAll();
		List<Stylistsubdto> stylistsdto = new ArrayList<Stylistsubdto>();
		for (Stylist stylist : stylists) {
			Stylistsubdto stylistdto = new Stylistsubdto(stylist.getName(), stylist.getEmail());
			stylistsdto.add(stylistdto);
		}
		return stylistsdto;
	}

	public String isSlotAvailable(LocalDateTime slotStartDateTime, LocalDateTime slotEndDateTime, String stylist) {
		LocalDateTime today = LocalDateTime.now();
		String output;
		if (slotStartDateTime.isBefore(today)) {
			output = "NotAvailable";
		} else if (stylistRepository.isSlotAvailable(slotStartDateTime, slotEndDateTime, stylist) == null) {
			output = "Available";
		} else {
			output = "NotAvailable";
		}
		return output;
	}

	public String bookSlot(Slot slot, String email) {
		Customer customer = customerService.findByEmail(basicServices.getCurrentUser());
		slot.setCustomer(customer);
		Stylist stylist = stylistRepository.findByEmail(email);
		customer.setStylist(stylist);
		stylist.addCustomer(customer);
		stylist.addSlot(slot);
		Maildto mail = new Maildto();
		mail.setMailFrom("tresbeaux3@gmail.com");
		mail.setMailTo(basicServices.getCurrentUser());
		mail.setMailSubject("Appointment Confirmation Mail");
		mail.setMailContent("Your appointment is confirmed" + "\nStylist: " + stylist.getName() + "\nMobile: "
				+ stylist.getNumber() + "\nDate: " + slot.getSlotStartDateTime().getDayOfMonth() + "/"
				+ slot.getSlotStartDateTime().getMonthValue() + "/" + slot.getSlotStartDateTime().getYear()
				+ "\nStart Time:" + slot.getSlotStartDateTime().getHour() + ":"
				+ slot.getSlotStartDateTime().getMinute() + "\nEnd Time:" + slot.getSlotEndDateTime().getHour() + ":"
				+ slot.getSlotEndDateTime().getMinute());
		String output;
		try {
			mailService.sendEmail(mail);
			if (slotService.save(slot) != null) {
				output = "Booked";
			} else {
				output = "Not Booked";
			}
		} catch (UnknownHostException e) {
			output = "No Internet";
		} catch (BadMessageException e) {
			output = "No Internet";
		}
		return output;
	}

	public Stylistsubdto getStylist() {

		Stylist stylist = stylistRepository.findByEmail(basicServices.getCurrentUser());
		Stylistsubdto stylistsubdto = new Stylistsubdto(stylist.getName(), stylist.getEmail(), stylist.getNumber());

		return stylistsubdto;
	}

	public List<Slotdto> getSlots() {
		Stylist stylist = stylistRepository.findByEmail(basicServices.getCurrentUser());
		Set<Customer> customers = stylist.getCustomers();
		List<Slotdto> slotdtos = new ArrayList<Slotdto>();
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime today = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(),
				now.getHour() - 2, now.getSecond());
		for (Customer customer : customers) {
			Slot slot = slotService.getSlotbyCustomerId(customer.getId());
			if (slot.getSlotEndDateTime().isAfter(today)) {
				Slotdto slotdto = new Slotdto(customer.getName(), customer.getAddress(), customer.getNumber(),
						slot.getSlotStartDateTime(), slot.getSlotEndDateTime());
				slotdtos.add(slotdto);
			}
		}
		return slotdtos;
	}

	public String changeSlotStatus(Long number) {
		Customer customer = customerService.findByNumber(number);
		Slot slot = slotService.getSlotbyCustomerId(customer.getId());
		String output;
		if (slot.getStatus() == 1) {
			slot.setStatus(0);
			output = "successnotcompleted";
		} else {
			slot.setStatus(1);
			output = "successcompleted";
		}
		slotService.save(slot);
		return output;
	}

	public List<Billdto> getCustomerBill(Long number) {
		Customer customer = customerService.findByNumber(number);
		List<SalonServiceDetail> salondetail = customer.getSalonServiceDetail();
		Map<Long, Billdto> billdto = new HashMap<Long, Billdto>();
		for (SalonServiceDetail service : salondetail) {
			Billdto bill = new Billdto(service.getId(), service.getName(), 1L, service.getPrice(),
					service.getSalonServiceCategory());
			if (billdto.containsKey(bill.getId())) {
				Billdto newbill = billdto.get(bill.getId());
				bill.setCount(newbill.getCount() + 1);
				billdto.replace(bill.getId(), bill);
			} else {
				billdto.put(bill.getId(), bill);
			}
		}
		return new ArrayList<Billdto>(billdto.values());
	}

	public List<Admindto> getStylistDetails() {
		List<Admindto> adminsdto = new ArrayList<Admindto>();
		List<Stylist> stylists = stylistRepository.findAll();
		for (Stylist stylist : stylists) {
			Admindto admindto = new Admindto(stylist.getName(), stylist.getNumber(),
					stylistRepository.getCompleted(stylist.getEmail()),
					stylistRepository.getNotCompleted(stylist.getEmail()));
			adminsdto.add(admindto);
		}
		return adminsdto;
	}
}
