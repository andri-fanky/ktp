/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umy.ti.ktp;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import umy.ti.ktp.exceptions.NonexistentEntityException;

/**
 *
 * @author Fanky
 */
@Controller
public class DataController {
    
    DataJpaController dataJpa = new DataJpaController();
    List<Data> data = new ArrayList();
    
    @GetMapping("/")
    public String getDataKTP(Model model) {
        try {
            data = dataJpa.findDataEntities();
        } catch (Exception e) {
        }
        model.addAttribute("data", data);
        return "index";
    }
    
    @GetMapping("/create")
    public String create() {
        return "create";
    }
    
    @PostMapping(value = "/store", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RedirectView store(HttpServletRequest request, @RequestParam("foto") MultipartFile file) throws ParseException, IOException {
        Data data = new Data();
        
        String noKtp = request.getParameter("no_ktp");
        String nama = request.getParameter("nama");
        String tglLahir = request.getParameter("tgl_lahir");
        String jenisKelamin = request.getParameter("jenis_kelamin");
        String alamat = request.getParameter("alamat");
        String agama = request.getParameter("agama");
        String statusPerkawinan = request.getParameter("status_perkawinan");
        String pekerjaan = request.getParameter("pekerjaan");
        String kewarganegaraan = request.getParameter("kewarganegaraan");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tglLahir);
        
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        byte[] foto = file.getBytes();
        
        data.setNoKtp(noKtp);
        data.setNama(nama);
        data.setTglLahir(date);
        data.setJenisKelamin(jenisKelamin.equals("1") ? true : (jenisKelamin.equals("0") ? false : null));
        data.setAlamat(alamat);
        data.setAgama(agama);
        data.setStatusPerkawinan(statusPerkawinan.equals("1") ? true : (statusPerkawinan.equals("0") ? false : null));
        data.setPekerjaan(pekerjaan);
        data.setKewarganegaraan(kewarganegaraan);
        data.setBerlakuHingga("SEUMUR HIDUP");
        data.setFoto(foto);
        
        dataJpa.create(data);
        return new RedirectView("/");
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Data data = new Data();
        try {
            data = dataJpa.findData(Long.parseLong(id));
        } catch (Exception e) {
        }
        
        if (data.getFoto() != null) {
            byte[] photo = data.getFoto();
            String base64Image = Base64.getEncoder().encodeToString(photo);
            String imgLink = "data:image/jpg;base64,".concat(base64Image);
            model.addAttribute("photo", imgLink);
        } else {
            model.addAttribute("photo", "");
        }
        
        model.addAttribute("data", data);
        return "edit";
    }
    
    @PostMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RedirectView update(@PathVariable String id, HttpServletRequest request, @RequestParam("foto") MultipartFile file) throws ParseException, IOException, Exception {
        Data data = new Data();
        
        String noKtp = request.getParameter("no_ktp");
        String nama = request.getParameter("nama");
        String tglLahir = request.getParameter("tgl_lahir");
        String jenisKelamin = request.getParameter("jenisKelamin");
        String alamat = request.getParameter("alamat");
        String agama = request.getParameter("agama");
        String statusPerkawinan = request.getParameter("statusPerkawinan");
        String pekerjaan = request.getParameter("pekerjaan");
        String kewarganegaraan = request.getParameter("kewarganegaraan");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tglLahir);
        
        if (file.isEmpty()) {
            Data data2 = dataJpa.findData(Long.parseLong(id));
            data.setFoto(data2.getFoto());
        } else {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            byte[] foto = file.getBytes();
            data.setFoto(foto);
        }
        
        data.setId(Long.parseLong(id));
        data.setNoKtp(noKtp);
        data.setNama(nama);
        data.setTglLahir(date);
        data.setJenisKelamin(jenisKelamin.equals("1") ? true : (jenisKelamin.equals("0") ? false : null));
        data.setAlamat(alamat);
        data.setAgama(agama);
        data.setStatusPerkawinan(statusPerkawinan.equals("1") ? true : (statusPerkawinan.equals("0") ? false : null));
        data.setPekerjaan(pekerjaan);
        data.setKewarganegaraan(kewarganegaraan);
        data.setBerlakuHingga("SEUMUR HIDUP");
        
        dataJpa.edit(data);
        return new RedirectView("/");
    }
    
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Data data = dataJpa.findData(id);
        
        if (data.getFoto() != null) {
            byte[] photo = data.getFoto();
            String base64Image = Base64.getEncoder().encodeToString(photo);
            String imgLink = "data:image/jpg;base64,".concat(base64Image);
            model.addAttribute("photo", imgLink);
        } else {
            model.addAttribute("photo", "");
        }
        
        model.addAttribute("data", data);
        return "detail";
    }
    
    @GetMapping("/destroy/{id}")
    public RedirectView destroy(@PathVariable Long id) throws NonexistentEntityException {
        dataJpa.destroy(id);
        return new RedirectView("/");
    }
}
