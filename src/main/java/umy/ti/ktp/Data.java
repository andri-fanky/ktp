/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umy.ti.ktp;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fanky
 */
@Entity
@Table(name = "data")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Data.findAll", query = "SELECT d FROM Data d"),
    @NamedQuery(name = "Data.findById", query = "SELECT d FROM Data d WHERE d.id = :id"),
    @NamedQuery(name = "Data.findByNoKtp", query = "SELECT d FROM Data d WHERE d.noKtp = :noKtp"),
    @NamedQuery(name = "Data.findByNama", query = "SELECT d FROM Data d WHERE d.nama = :nama"),
    @NamedQuery(name = "Data.findByTglLahir", query = "SELECT d FROM Data d WHERE d.tglLahir = :tglLahir"),
    @NamedQuery(name = "Data.findByJenisKelamin", query = "SELECT d FROM Data d WHERE d.jenisKelamin = :jenisKelamin"),
    @NamedQuery(name = "Data.findByAgama", query = "SELECT d FROM Data d WHERE d.agama = :agama"),
    @NamedQuery(name = "Data.findByStatusPerkawinan", query = "SELECT d FROM Data d WHERE d.statusPerkawinan = :statusPerkawinan"),
    @NamedQuery(name = "Data.findByPekerjaan", query = "SELECT d FROM Data d WHERE d.pekerjaan = :pekerjaan"),
    @NamedQuery(name = "Data.findByKewarganegaraan", query = "SELECT d FROM Data d WHERE d.kewarganegaraan = :kewarganegaraan"),
    @NamedQuery(name = "Data.findByBerlakuHingga", query = "SELECT d FROM Data d WHERE d.berlakuHingga = :berlakuHingga")})
public class Data implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "no_ktp")
    private String noKtp;
    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;
    @Basic(optional = false)
    @Column(name = "tgl_lahir")
    @Temporal(TemporalType.DATE)
    private Date tglLahir;
    @Basic(optional = false)
    @Column(name = "jenis_kelamin")
    private boolean jenisKelamin;
    @Basic(optional = false)
    @Lob
    @Column(name = "alamat")
    private String alamat;
    @Basic(optional = false)
    @Column(name = "agama")
    private String agama;
    @Basic(optional = false)
    @Column(name = "status_perkawinan")
    private boolean statusPerkawinan;
    @Column(name = "pekerjaan")
    private String pekerjaan;
    @Basic(optional = false)
    @Column(name = "kewarganegaraan")
    private String kewarganegaraan;
    @Column(name = "berlaku_hingga")
    private String berlakuHingga;
    @Lob
    @Column(name = "foto")
    private byte[] foto;

    public Data() {
    }

    public Data(Long id) {
        this.id = id;
    }

    public Data(Long id, String noKtp, String nama, Date tglLahir, boolean jenisKelamin, String alamat, String agama, boolean statusPerkawinan, String kewarganegaraan) {
        this.id = id;
        this.noKtp = noKtp;
        this.nama = nama;
        this.tglLahir = tglLahir;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
        this.agama = agama;
        this.statusPerkawinan = statusPerkawinan;
        this.kewarganegaraan = kewarganegaraan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }

    public boolean getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(boolean jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public boolean getStatusPerkawinan() {
        return statusPerkawinan;
    }

    public void setStatusPerkawinan(boolean statusPerkawinan) {
        this.statusPerkawinan = statusPerkawinan;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getKewarganegaraan() {
        return kewarganegaraan;
    }

    public void setKewarganegaraan(String kewarganegaraan) {
        this.kewarganegaraan = kewarganegaraan;
    }

    public String getBerlakuHingga() {
        return berlakuHingga;
    }

    public void setBerlakuHingga(String berlakuHingga) {
        this.berlakuHingga = berlakuHingga;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Data)) {
            return false;
        }
        Data other = (Data) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "umy.ti.ktp.Data[ id=" + id + " ]";
    }
    
}
