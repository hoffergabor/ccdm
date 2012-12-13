package hu.iboard.coandco.datamagic.model.dmvsorsz;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DMVSORSZ")
public class DmvSorsz implements Serializable {

	private static final long serialVersionUID = 7141740150829848908L;
	
	private Integer rekazon;
	private Integer szall;
	private Integer szallcsop;
	private Integer partner;
	private Integer vevocsop;
	private Integer vevo2csop;
	private Integer erteskod;
	private Integer felhkod;
	private Integer afakod;
	private Integer aru;
	private Integer raktar;
	private Integer cikkcs;
	private Integer szall2csop;
	private Integer morepart1;
	private Integer morepart2;
	private Integer bankmozg;
	private Integer pukod;
	private Integer partszalid;
	private Integer vkartya;
	private Integer morepart3;
	private Integer morepart4;
	private Integer morepart5;
	private Integer nextcikksz;
	private Integer lakosvevo;
	private Integer szlaprofil;
	
	@Id
	@Column(name = "REKAZON", unique = true, nullable = false, length = 11)
	public Integer getRekazon() {
		return rekazon;
	}
	public void setRekazon(Integer rekazon) {
		this.rekazon = rekazon;
	}
	
	@Column(name = "SZALL")
	public Integer getSzall() {
		return szall;
	}
	public void setSzall(Integer szall) {
		this.szall = szall;
	}
	
	@Column(name = "SZALLCSOP")
	public Integer getSzallcsop() {
		return szallcsop;
	}
	public void setSzallcsop(Integer szallcsop) {
		this.szallcsop = szallcsop;
	}
	
	@Column(name = "PARTNER")
	public Integer getPartner() {
		return partner;
	}
	public void setPartner(Integer partner) {
		this.partner = partner;
	}
	
	@Column(name = "VEVOCSOP")
	public Integer getVevocsop() {
		return vevocsop;
	}
	public void setVevocsop(Integer vevocsop) {
		this.vevocsop = vevocsop;
	}
	
	@Column(name = "VEVO2CSOP")
	public Integer getVevo2csop() {
		return vevo2csop;
	}
	public void setVevo2csop(Integer vevo2csop) {
		this.vevo2csop = vevo2csop;
	}
	
	@Column(name = "ERTESKOD")
	public Integer getErteskod() {
		return erteskod;
	}
	public void setErteskod(Integer erteskod) {
		this.erteskod = erteskod;
	}
	
	@Column(name = "FELHKOD")
	public Integer getFelhkod() {
		return felhkod;
	}
	public void setFelhkod(Integer felhkod) {
		this.felhkod = felhkod;
	}
	
	@Column(name = "AFAKOD")
	public Integer getAfakod() {
		return afakod;
	}
	public void setAfakod(Integer afakod) {
		this.afakod = afakod;
	}
	
	@Column(name = "ARU")
	public Integer getAru() {
		return aru;
	}
	public void setAru(Integer aru) {
		this.aru = aru;
	}
	
	@Column(name = "RAKTAR")
	public Integer getRaktar() {
		return raktar;
	}
	public void setRaktar(Integer raktar) {
		this.raktar = raktar;
	}
	
	@Column(name = "CIKKCS")
	public Integer getCikkcs() {
		return cikkcs;
	}
	public void setCikkcs(Integer cikkcs) {
		this.cikkcs = cikkcs;
	}
	
	@Column(name = "SZALL2CSOP")
	public Integer getSzall2csop() {
		return szall2csop;
	}
	public void setSzall2csop(Integer szall2csop) {
		this.szall2csop = szall2csop;
	}
	
	@Column(name = "MOREPART1")
	public Integer getMorepart1() {
		return morepart1;
	}
	public void setMorepart1(Integer morepart1) {
		this.morepart1 = morepart1;
	}
	
	@Column(name = "MOREPART2")
	public Integer getMorepart2() {
		return morepart2;
	}
	public void setMorepart2(Integer morepart2) {
		this.morepart2 = morepart2;
	}
	
	@Column(name = "BANKMOZG")
	public Integer getBankmozg() {
		return bankmozg;
	}
	public void setBankmozg(Integer bankmozg) {
		this.bankmozg = bankmozg;
	}
	
	@Column(name = "PUKOD")
	public Integer getPukod() {
		return pukod;
	}
	public void setPukod(Integer pukod) {
		this.pukod = pukod;
	}
	
	@Column(name = "PARTSZALID")
	public Integer getPartszalid() {
		return partszalid;
	}
	public void setPartszalid(Integer partszalid) {
		this.partszalid = partszalid;
	}
	
	@Column(name = "VKARTYA")
	public Integer getVkartya() {
		return vkartya;
	}
	public void setVkartya(Integer vkartya) {
		this.vkartya = vkartya;
	}
	
	@Column(name = "MOREPART3")
	public Integer getMorepart3() {
		return morepart3;
	}
	public void setMorepart3(Integer morepart3) {
		this.morepart3 = morepart3;
	}
	
	@Column(name = "MOREPART4")
	public Integer getMorepart4() {
		return morepart4;
	}
	public void setMorepart4(Integer morepart4) {
		this.morepart4 = morepart4;
	}
	
	@Column(name = "MOREPART5")
	public Integer getMorepart5() {
		return morepart5;
	}
	public void setMorepart5(Integer morepart5) {
		this.morepart5 = morepart5;
	}
	
	@Column(name = "NEXTCIKKSZ")
	public Integer getNextcikksz() {
		return nextcikksz;
	}
	public void setNextcikksz(Integer nextcikksz) {
		this.nextcikksz = nextcikksz;
	}
	
	@Column(name = "LAKOSVEVO")
	public Integer getLakosvevo() {
		return lakosvevo;
	}
	public void setLakosvevo(Integer lakosvevo) {
		this.lakosvevo = lakosvevo;
	}
	
	@Column(name = "SZLAPROFIL")
	public Integer getSzlaprofil() {
		return szlaprofil;
	}
	public void setSzlaprofil(Integer szlaprofil) {
		this.szlaprofil = szlaprofil;
	}
	

}
