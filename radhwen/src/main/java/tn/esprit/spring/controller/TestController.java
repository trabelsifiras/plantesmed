package tn.esprit.spring.controller;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import org.aspectj.weaver.Utils;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.RateEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tn.esprit.spring.entities.Membre;
import tn.esprit.spring.entities.Plante;
import tn.esprit.spring.repository.PlanteRepository;
import tn.esprit.spring.services.PlanteService;

@Controller(value = "chart")
@ELBeanName(value = "chart")
@Join(path = "/chartanalyse1", to = "/pages/test.jsf")
@Named
@RequestScoped
@ManagedBean
public class TestController {

	 @Autowired
	 PlanteService ps;
	 private Plante plentity;
	 private Membre mementity;
	@Autowired
	 PlanteRepository pr;
	   private String description ;			
		private String photos ;
		
		 private Integer rating3;   

		private Part uploadedFile;
		private String folder = "c:\\work";

		public Part getUploadedFile() {
			return uploadedFile;
		}

		public void setUploadedFile(Part uploadedFile) {
			this.uploadedFile = uploadedFile;
		}

		
		public void addPL(int idmmbre){

		
			ps.AjouterPlanteByIdUser(new Plante(description,photos),idmmbre);
	
			
		}
		
		private static String UPLOADED_FOLDER = System.getProperty("user.dir")+"/src/main/WebApp/uploads";

	    @GetMapping("/")
	    public void index() {
	       // return "upload";
	    }

	    @PostMapping("/upload") // //new annotation since 4.3
	    public String  singleFileUpload(@RequestParam("file") MultipartFile file,
	    		@RequestParam("description") String description) {
	    	String navigateTo ="/pages/test2.xhtml?faces-redirect=true";
	        if (file.isEmpty()) {
	        	System.out.println("ssssssssssssssssssssssssssssss");
	        	
	        	
	        
	        	
	           // redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
	           // return "redirect:uploadStatus";
	        }

	        try {
	        	System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	            // Get the file and save it somewhere
	        	File dir = new File( UPLOADED_FOLDER);
				
	        	if (!dir.exists())
					dir.mkdirs();
	            byte[] bytes = file.getBytes();
	      
	            Path path = Paths.get(UPLOADED_FOLDER +File.separator+ file.getOriginalFilename());
	            Files.write(path, bytes);

//	            redirectAttributes.addFlashAttribute("message",
//	                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
	     Plante p =new Plante();
	     p.setDescription(description);
	      p.setPhotos(file.getOriginalFilename());
	     pr.save(p);
	           
	   return "redirect:/pages/test.jsf";

	        } catch (IOException e) {
	        	
	            e.printStackTrace();
	            return "redirect:/uploadStatus";	        }

	      //  return "redirect:/uploadStatus";
	    }
	    
	    public List<Plante> getall(){
			return pr.findAll();
	    	
	    	
	    }
		public PlanteService getPs() {
			return ps;
		}
		public void setPs(PlanteService ps) {
			this.ps = ps;
		}
		public Plante getPlentity() {
			return plentity;
		}
		public void setPlentity(Plante plentity) {
			this.plentity = plentity;
		}
		public Membre getMementity() {
			return mementity;
		}
		public void setMementity(Membre mementity) {
			this.mementity = mementity;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getPhotos() {
			return photos;
		}
		public void setPhotos(String photos) {
			this.photos = photos;
		}

		public String getFolder() {
			return folder;
		}

		public void setFolder(String folder) {
			this.folder = folder;
		}

		public Integer getrating3() {
			return rating3;
		}

		public void setrating3(Integer rating3) {
			this.rating3 = rating3;
		}
		  public void onrate(RateEvent rateEvent) {
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + rateEvent.getRating());
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		     
		    public void oncancel() {
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancel Event", "Rate Reset");
		        FacesContext.getCurrentInstance().addMessage(null, message);
		    }
		
	
	
	
}
