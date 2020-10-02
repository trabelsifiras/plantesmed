package tn.esprit.spring.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tn.esprit.spring.entities.Membre;
import tn.esprit.spring.repository.MembreRepository;
@Controller(value = "login")
@ELBeanName(value = "login")
@Join(path = "/loginsignup", to = "/pages/signup.jsf")
@SessionScoped


public class LoginController implements Serializable {
    private static final long serialVersionUID = 1L;

private static String UPLOADED_FOLDER = System.getProperty("user.dir")+"/src/main/WebApp/uploads";
	private Boolean islogin=false;
	@Autowired
	MembreRepository mp ;
	private  HttpSession session;
	private  HttpSession session1;
	HttpServletRequest hr;
	public static final String HOME_PAGE_REDIRECT =
		      "/test.xhtml?faces-redirect=true";
	/*@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
*/
	public  HttpSession getSession() {
		return session;
	}


	public  void setSession(HttpSession session) {
		this.session = session;
	}


	 private Membre currentUser;
	private Membre m ;
	
	private int id ;
	private List<Integer> ides;
	
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
	/*public String displayphoto(){

		Membre m=mp.findById(id).get();
		String photo="http://localhost:8081/SpringMVC/uploads/"+m.getImage();
		return photo;
		
	}
	public String displayname(){
		Membre m=mp.findById(id).get();
		String name=m.getNom();
		return name;}
*/
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
   /*  m.setPassword(bCryptPasswordEncoder.encode(password));*/
     mp.save(m);
     

   return "redirect:/pages/signup.jsf";

        } catch (IOException e) {

            e.printStackTrace();
            return "redirect:/uploadStatus";            }

      //  return "redirect:/uploadStatus";
    }
	
	
	/*
	public void login(){
		List<Membre> membres = mp.findAll();
		//System.out.println(membres);
		for(Membre i:membres  ){
			
			
			if(i.getMail().equals(email) ){
				
				if(i.getPassword().equals(password)){
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", i);
		          	this.id=i.getId();
					this.islogin=true;
				
					doRedirect("/SpringMVC/chartanalyse1");
					}	
				else {
					 this.session1=null;
					doRedirect ("/SpringMVC/loginsignup") ;
				}
					
			}
		
		}

	}
	
	*/
	
	
	public void login(){
		Membre mb;
		
		try {
			 
			mb=mp.findByEmailandpassword(email, password);
			System.out.println(mb.getId());
			if(mb!=null){
				
				this.islogin=true;
				
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", mb);
				 FacesContext context = FacesContext.getCurrentInstance();
				  session = (HttpSession) context.getExternalContext().getSession(true);
				  session.setAttribute("id", mb.getId());
				  session.setAttribute("username", mb.getMail());
				  session.setAttribute("photo", mb.getImage());
				System.out.println(session.getAttribute("id"));

		
				doRedirect("/SpringMVC/chartanalyse1?faces-redirect=true");			
				
			}
			else {
				doRedirect("/SpringMVC/loginsignup?faces-redirect=true");	
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		
		
	}
	
	
	
	
	
	
	@RequestMapping(value="/loginsession",method = RequestMethod.POST)
    public String addNote(@RequestParam("email") String email,@RequestParam("password") String password, HttpServletRequest request) throws ServletException, IOException {
        //get the notes from request session
		List<Membre> membres = mp.findAll();
		List<String> notes = (List<String>) request.getSession().getAttribute("NOTES_SESSION");
        //check if notes is present in session or not
		
		
		for(Membre i:membres  ){
		if (i.getMail().equals(email)) {
			if(i.getPassword().equals(password)){
				System.out.println(i.getPassword());
				this.id=i.getId();
				  notes = new ArrayList<>();
		            // if notes object is not present in session, set notes in the request session
		            request.getSession().setAttribute("NOTES_SESSION", notes);
		            this.session=request.getSession();
		            this.session1=session;
		            System.out.println(session);
		           currentUser=mp.findById(id).get();
		            return "redirect:/secured/test.jsf?"+session+"";
		            }
			else {
				
				return "redirect:/pages/signup.jsf?faces-redirect=ture" ;
						 
			}
        }
		}		
        request.getSession().setAttribute("NOTES_SESSION", notes);
        currentUser=null;
		return null ;
    }
	public boolean isLoggedIn() {
	    return currentUser != null;
	  }
	public String dologin() throws ServletException, IOException{
		 ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
 		RequestDispatcher dispatcher=((ServletRequest) context.getRequest()).getRequestDispatcher("/servlet/loginsession");
 		dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
 		FacesContext.getCurrentInstance().responseComplete();
		return null;
		
		
	}
	@RequestMapping("/login")
	public ModelAndView login1() {
		return new ModelAndView("/pages/loginsignup.jsp");
	}
	@PostMapping("/invalidate/session")
    public String destroySession(HttpServletRequest request) {
        //invalidate the session , this will clear the data from configured database (Mysql/redis/hazelcast)
        request.getSession().invalidate();
        return "redirect:/pages/loginsignup.jsf?faces-redirect=ture" ;
    }


	public int getId() {
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



	public  void setId(int id) {
		this.id = id;
	}
	  private static final Logger LOGGER =
		      LoggerFactory.getLogger(LoginController.class);

	public Membre getCurrentUser() {
		return currentUser;
	}


	public void isLoggedInForwardHome() {
		
		if (!this.islogin)
			
	    	doRedirect("/SpringMVC/loginsignup");
	}
	public void doRedirect(String url){
		
		
		try{
			
			FacesContext context=FacesContext.getCurrentInstance();
			context.getExternalContext().redirect(url);
					
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		
		
	}


	public HttpSession getSession1() {
		return session1;
	}


	public void setSession1(HttpSession session1) {
		this.session1 = session1;
	}
	
	
	
	
	}
