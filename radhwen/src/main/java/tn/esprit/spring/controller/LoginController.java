package tn.esprit.spring.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.Membre;
import tn.esprit.spring.repository.MembreRepository;
@Controller(value = "login")
@ELBeanName(value = "login")
@Join(path = "/loginsignup", to = "/pages/signup.jsf")
@Named
@RequestScoped
@ManagedBean
public class LoginController {
private static String UPLOADED_FOLDER = System.getProperty("user.dir")+"/src/main/WebApp/uploads";
	
	@Autowired
	MembreRepository mp ;
	
	private Membre m ;
	
	private static int id ;
	
	public String password ;
	
	public String email ;
	
	
	public Membre getM() {
		return m;
	}



	public void setM(Membre m) {
		this.m = m;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}
	public String displayphoto(){
		Membre m=mp.findById(id).get();
		String photo="http://localhost:8081/SpringMVC/uploads/"+m.getImage();
		return photo;
		
	}
	public String displayname(){
		Membre m=mp.findById(id).get();
		String name=m.getNom();
		return name;}

	@PostMapping("/upload1") // //new annotation since 4.3
    public String  singleFileUpload(@RequestParam("file") MultipartFile file,
            @RequestParam("firstname") String firstname , @RequestParam("lastname") String lastname
            ,@RequestParam("password") String password
            ,@RequestParam("email") String email) {
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

//            redirectAttributes.addFlashAttribute("message",
//                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
     Membre m =new Membre();
     m.setNom(lastname);
     m.setPrenom(firstname);
     m.setImage(file.getOriginalFilename());
     m.setMail(email);
     m.setPassword(password);
     mp.save(m);
     

   return "redirect:/pages/signup.jsf";

        } catch (IOException e) {

            e.printStackTrace();
            return "redirect:/uploadStatus";            }

      //  return "redirect:/uploadStatus";
    }
	
	
	
	public String login(){
		List<Membre> membres = mp.findAll();
		//System.out.println(membres);
		
		for(Membre i:membres  ){
			System.out.println(i.getId());
			if(i.getMail().equals(email) ){
				if(i.getPassword().equals(password)){
					this.id =  i.getId();
					System.out.println(id);	
					return "/pages/test.xhtml?faces-redirect=ture";
						
				}	else {
					return "/pages/AddUser.xhtml?faces-redirect=ture" ;
				}
					
			}
		
		}
		return "/pages/AddUser.xhtml?faces-redirect=ture";
	}



	public static int getId() {
		return id;
	}



	public static String getUPLOADED_FOLDER() {
		return UPLOADED_FOLDER;
	}



	public static void setUPLOADED_FOLDER(String uPLOADED_FOLDER) {
		UPLOADED_FOLDER = uPLOADED_FOLDER;
	}



	public MembreRepository getMp() {
		return mp;
	}



	public void setMp(MembreRepository mp) {
		this.mp = mp;
	}



	public static void setId(int id) {
		LoginController.id = id;
	}



	
}
