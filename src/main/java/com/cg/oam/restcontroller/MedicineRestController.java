package com.cg.oam.restcontroller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cg.oam.bean.MedicineBean;
import com.cg.oam.entity.Medicine;
import com.cg.oam.service.MedicineService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/medicine")
public class MedicineRestController {
	@Autowired
	private MedicineService medicineService;
	
	@GetMapping("/allMedicines")
	public ResponseEntity<List<MedicineBean>> getAllMedicines(){
		List<MedicineBean> medBeans = medicineService.getAllMedicines();
		return ResponseEntity.ok(medBeans);
	}
	
	@PostMapping("/addMedicine")
	public ResponseEntity<String> addMedicine(@RequestBody Medicine medicine)
	{
		medicineService.createMedicine(medicine);
		return ResponseEntity.ok("Medicine Saved");
	}
	@PostMapping("/uploadImage/{medicineId}")
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,@PathVariable Integer medicineId){
		medicineService.uploadMedicineImage(file, medicineId);
		return ResponseEntity.ok("image uploaded successfully");
	}
	
	@PutMapping("/updateMedicine")
	public ResponseEntity<String> updateMedicine(@RequestBody Medicine med)
	{
		medicineService.updateMedicine(med);
		return ResponseEntity.ok("Medicine updated");
	}
	
	@DeleteMapping("/deleteMedicine/{medicineid}")
	public ResponseEntity<Void> deleteMedicine(@PathVariable("medicineid") int medicineid)
	{
		medicineService.deleteMedicine(medicineid);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	//@GetMapping("/getAllMedicines")
	//public ResponseEntity<List<MedicineBean>> findAllMedicines()
	//{
	//	List<MedicineBean> medicines=medicineService.findAllAvailableMedicines();
	//	return ResponseEntity.ok(medicines);
	//}
	
	@GetMapping("/getMedicineById/{medicineid}")
	public ResponseEntity<MedicineBean> findMedicineById(@PathVariable("medicineid") Integer medicineid) {
		MedicineBean medicine = medicineService.findMedicineById(medicineid);
		return ResponseEntity.ok(medicine);
	}
	
	@GetMapping("/getByMedicineName/{medicinename}")
	public ResponseEntity<MedicineBean> findByMedicineName(@PathVariable("medicinename") String medicineName)
{
		MedicineBean medicine=medicineService.findByMedicineName(medicineName);
		return ResponseEntity.ok(medicine);
	}
	
	@GetMapping("/getByMedicineCategory/{categoryname}")
	public ResponseEntity<List<MedicineBean>> findAllMedicinesByCategory(@PathVariable("categoryname") String categoryName)
	{
		List<MedicineBean> medicines=medicineService.findByMedicineCategory(categoryName);
		return ResponseEntity.ok(medicines);
	}
	
	@GetMapping("/getMedicinebyCompanyName/{companyname}")
	public ResponseEntity<List<MedicineBean>> findAllMedicinesByCompany(@PathVariable("companyname") String companyName)
{
		List<MedicineBean> medicines=medicineService.findByCompany(companyName);
		return ResponseEntity.ok(medicines);
	}
}
